package com.infumia.t3sl4.util.nbt.base;

import com.infumia.t3sl4.util.itemstack.util.XMaterial;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.jetbrains.annotations.NotNull;

public class SpawnerNBTOf extends ElementEnvelope<CreatureSpawner, NBTCompound> {
   public SpawnerNBTOf(@NotNull CreatureSpawner element) {
      super(element);
   }

   public SpawnerNBTOf(@NotNull Supplier<CreatureSpawner> element) {
      this((CreatureSpawner)element.get());
   }

   public SpawnerNBTOf(@NotNull Block element) {
      this(() -> {
         if (!(element.getState() instanceof CreatureSpawner)) {
            element.setType(XMaterial.SPAWNER.parseMaterial());
         }

         return (CreatureSpawner)element.getState();
      });
   }

   public SpawnerNBTOf(@NotNull Location location) {
      this(location.getBlock());
   }

   @NotNull
   public NBTCompound nbt() {
      return REGISTRY.toCompound((CreatureSpawner)this.element());
   }

   @NotNull
   public CreatureSpawner apply(@NotNull NBTCompound applied) {
      return REGISTRY.toSpawner((CreatureSpawner)this.element(), applied);
   }
}
