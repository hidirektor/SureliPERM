package com.infumia.t3sl4.util.itemstack.item.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class AmountOf extends ScalarRuntime<Integer> {
   public AmountOf(@NotNull Scalar<ItemStack> itemStackScalar) {
      super(() -> {
         return ((ItemStack)itemStackScalar.value()).getAmount();
      });
   }

   public AmountOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }
}
