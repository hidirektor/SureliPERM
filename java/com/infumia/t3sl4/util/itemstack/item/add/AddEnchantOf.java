package com.infumia.t3sl4.util.itemstack.item.add;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;

import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.jetbrains.annotations.NotNull;

public final class AddEnchantOf extends ScalarRuntime<ItemStack> {
   public AddEnchantOf(@NotNull Scalar<ItemStack> itemStackScalar, @NotNull Scalar<Map<Enchantment, Integer>> mapScalar) {
      super(() -> {
         ItemStack itemStack = (ItemStack)itemStackScalar.value();
         itemStack.addUnsafeEnchantments((Map)mapScalar.value());
         return itemStack;
      });
   }

   public AddEnchantOf(@NotNull Scalar<ItemStack> itemStack, @NotNull Map<Enchantment, Integer> map) {
      this(itemStack, () -> {
         return map;
      });
   }

   public AddEnchantOf(@NotNull ItemStack itemStack, @NotNull Enchantment enchantment, int level) {
      this(() -> {
         return itemStack;
      }, (Map)(new MapOf(new Entry[]{new MapEntry(enchantment, level)})));
   }
}
