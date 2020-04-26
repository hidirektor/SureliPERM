package com.infumia.t3sl4.sandikspawner.listener;

import com.infumia.t3sl4.util.location.StringOf;
import io.github.portlek.mcyaml.IYaml;
import io.github.portlek.nbt.base.ItemStackNBTOf;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.mock.MckChestType;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import com.infumia.t3sl4.sandikspawner.util.Util;
import io.github.portlek.versionmatched.Version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.cactoos.scalar.MaxOf;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class ChestPlace implements Listener {

   @NotNull
   private final SpawnerAPI spawnerAPI;

   public ChestPlace(@NotNull SpawnerAPI spawnerAPI) {
      this.spawnerAPI = spawnerAPI;
   }

   @EventHandler
   public void chestPlace(BlockPlaceEvent event) {
      final ChestType chestType = spawnerAPI.findById(
              new ItemStackNBTOf(event.getItemInHand())
                      .nbt()
                      .getNBTCompound("tag")
                      .getString("chest-spawner-id")
      );

      if (chestType instanceof MckChestType) {
         return;
      }

      final Block block = event.getBlockPlaced();

      if (!check(block)) {
         event.setCancelled(true);
         return;
      }


      final Chest chest = (Chest) block.getState();
      final Block signBlock;

      if (new Version().minor() < 13) {
         signBlock = Util.putSignOn(
                 block,
                 ((org.bukkit.material.Directional) chest.getData()).getFacing(),
                 spawnerAPI.getConfigs().sign
         );
      } else {
         signBlock = Util.putSignOn(
                 block,
                 ((Directional) chest.getBlock().getBlockData()).getFacing(),
                 spawnerAPI.getConfigs().sign
         );
      }

      if (signBlock == null) {
         return;
      }

      spawnerAPI.add(
              new ChestPlaced(
                      spawnerAPI,
                      UUID.randomUUID(),
                      event.getPlayer().getUniqueId(),
                      chest,
                      (Sign) signBlock.getState(),
                      chestType,
                      0,
                      0,
                      new MaxOf(
                              1,
                              new ItemStackNBTOf(event.getItemInHand())
                                      .nbt()
                                      .getNBTCompound("tag")
                                      .getInt("chest-spawner-level")
                      ).intValue()
              )
      );
      event.getPlayer().sendMessage(spawnerAPI.getLanguage().generalYouPlaceAChestSpawner(chestType.getName()));
      //final Location blockLocation = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ());
      //event.getPlayer().sendMessage("Blok X: " + blockLocation.getBlockX());
   }

   private boolean check(@NotNull Block block) {
      for (int x = 1; x >= -1; x--) {
         for (int y = 1; y >= -1; y--) {
            for (int z = 1; z >= -1; z--) {
               if (x == 0 && y == 0 && z == 0) {
                  continue;
               }

               final Block nearby = block.getRelative(x, y, z);

               if (nearby.getState() instanceof Chest ) {
                  return false;
               }
            }
         }
      }

      return true;
   }

}
