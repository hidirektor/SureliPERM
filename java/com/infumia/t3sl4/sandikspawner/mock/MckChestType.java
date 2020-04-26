package com.infumia.t3sl4.sandikspawner.mock;

import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class MckChestType implements ChestType {
   @NotNull
   public String getId() {
      throw new UnsupportedOperationException();
   }

   public int maxLevel() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   public String getName() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   public ItemStack getChestItem(int level) {
      throw new UnsupportedOperationException();
   }

   @NotNull
   public ItemStack getDrop() {
      throw new UnsupportedOperationException();
   }

   public int speed(int level) {
      throw new UnsupportedOperationException();
   }

   public int money() {
      throw new UnsupportedOperationException();
   }

   @NotNull
   public ItemStack getDrop(int level) {
      throw new UnsupportedOperationException();
   }
}
