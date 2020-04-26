package com.infumia.t3sl4.sandikspawner.chest.type;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ChestType {
   @NotNull
   String getId();

   int maxLevel();

   @NotNull
   String getName();

   @NotNull
   ItemStack getChestItem(int var1);

   @NotNull
   ItemStack getDrop();

   @NotNull
   ItemStack getDrop(int var1);

   int speed(int var1);

   int money();
}
