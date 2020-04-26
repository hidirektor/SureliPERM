package com.infumia.t3sl4.util.nbt.api;

import java.util.List;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface NBTRegistry {
   @NotNull
   ItemStack toItemStack(@NotNull NBTCompound var1);

   @NotNull
   NBTCompound toCompound(@NotNull ItemStack var1);

   @NotNull
   NBTCompound toCompound(@NotNull String var1);

   @NotNull
   NBTList toTagStringList(@NotNull List<String> var1);

   @NotNull
   NBTList toTagStringList(@NotNull String var1);

   @NotNull
   NBTCompound toCompound(@NotNull CreatureSpawner var1);

   @NotNull
   CreatureSpawner toSpawner(@NotNull CreatureSpawner var1, @NotNull NBTCompound var2);

   NBTCompound toCompound(@NotNull Entity var1);

   Entity toEntity(@NotNull Entity var1, @NotNull NBTCompound var2);
}
