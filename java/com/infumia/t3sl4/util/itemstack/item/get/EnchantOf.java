package com.infumia.t3sl4.util.itemstack.item.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class EnchantOf extends ScalarRuntime<Map<Enchantment, Integer>> {
   public EnchantOf(@NotNull Scalar<ItemStack> itemStackScalar) {
      super(() -> {
         return ((ItemStack)itemStackScalar.value()).getEnchantments();
      });
   }

   public EnchantOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }
}
