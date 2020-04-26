package com.infumia.t3sl4.sandikspawner.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.jetbrains.annotations.NotNull;

public class NearbySpawner implements Scalar<List<CreatureSpawner>> {
   @NotNull
   private final World world;
   @NotNull
   private final Axis axis;

   private NearbySpawner(@NotNull World world, @NotNull Axis axis) {
      this.world = world;
      this.axis = axis;
   }

   private NearbySpawner(@NotNull World world, @NotNull BoundingBox boundingBox) {
      this(world, new Axis(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ(), boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ()));
   }

   private NearbySpawner(@NotNull Location location, int radius) {
      this((World)Objects.requireNonNull(location.getWorld()), BoundingBox.of(location, (double)radius, (double)radius, (double)radius));
   }

   public NearbySpawner(@NotNull Block block, int radius) {
      this(block.getLocation(), radius);
   }

   @NotNull
   public List<CreatureSpawner> value() {
      List<CreatureSpawner> list = new ArrayList();
      int i = (new Floored((this.axis.getMinX() - 2.0D) / 16.0D)).value();
      int j = (new Floored((this.axis.getMaxX() + 2.0D) / 16.0D)).value();
      int k = (new Floored((this.axis.getMinZ() - 2.0D) / 16.0D)).value();
      int l = (new Floored((this.axis.getMaxZ() + 2.0D) / 16.0D)).value();

      for(int i1 = i; i1 <= j; ++i1) {
         for(int j1 = k; j1 <= l; ++j1) {
            Chunk chunk = this.world.getChunkAt(i1, j1);
            BlockState[] tileEntities = chunk.getTileEntities();
            And thereIsNoCreatureSpawner = new And((tileEntity) -> {
               return !(tileEntity instanceof CreatureSpawner);
            }, new IterableOf(tileEntities));

            try {
               if (thereIsNoCreatureSpawner.value()) {
                  continue;
               }
            } catch (Exception var20) {
               continue;
            }

            int m = (new Clamped((new Floored((this.axis.getMinY() - 2.0D) / 16.0D)).value(), 0, tileEntities.length - 1)).value();
            int n = (new Clamped((new Floored((this.axis.getMaxY() + 2.0D) / 16.0D)).value(), 0, tileEntities.length - 1)).value();

            for(int o = m; o <= n; ++o) {
               BlockState[] var14 = tileEntities;
               int var15 = tileEntities.length;

               for(int var16 = 0; var16 < var15; ++var16) {
                  BlockState blockState = var14[var16];
                  if (blockState instanceof CreatureSpawner) {
                     BoundingBox boundingBox = BoundingBox.of(blockState.getLocation(), 1.0D, 1.0D, 1.0D);
                     Axis stateAxis = new Axis(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ(), boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ());
                     if (stateAxis.collider(this.axis)) {
                        list.add((CreatureSpawner)blockState);
                     }
                  }
               }
            }
         }
      }

      return list;
   }
}
