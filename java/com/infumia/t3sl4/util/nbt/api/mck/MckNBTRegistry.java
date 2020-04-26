package com.infumia.t3sl4.util.nbt.api.mck;

import com.infumia.t3sl4.util.nbt.api.NBTList;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.api.NBTRegistry;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MckNBTRegistry implements NBTRegistry {
   @NotNull
   public ItemStack toItemStack(@NotNull NBTCompound nbtCompound) {
      return new ItemStack(Material.AIR);
   }

   @NotNull
   public NBTCompound toCompound(@NotNull ItemStack itemStack) {
      return new com.infumia.t3sl4.util.nbt.api.mck.MckNBTCompound();
   }

   @NotNull
   public NBTCompound toCompound(@NotNull String nbtString) {
      return new com.infumia.t3sl4.util.nbt.api.mck.MckNBTCompound();
   }

   @NotNull
   public com.infumia.t3sl4.util.nbt.api.NBTList toTagStringList(@NotNull List<String> nbtString) {
      return new com.infumia.t3sl4.util.nbt.api.mck.MckNBTList();
   }

   @NotNull
   public NBTList toTagStringList(@NotNull String nbtString) {
      return new MckNBTList();
   }

   @NotNull
   public NBTCompound toCompound(@NotNull CreatureSpawner creatureSpawner) {
      return new com.infumia.t3sl4.util.nbt.api.mck.MckNBTCompound();
   }

   @NotNull
   public CreatureSpawner toSpawner(@NotNull CreatureSpawner spawner, @NotNull NBTCompound applied) {
      return spawner;
   }

   public NBTCompound toCompound(@NotNull Entity entity) {
      return new MckNBTCompound();
   }

   public Entity toEntity(@NotNull Entity original, @NotNull NBTCompound applied) {
      return original;
   }
}
