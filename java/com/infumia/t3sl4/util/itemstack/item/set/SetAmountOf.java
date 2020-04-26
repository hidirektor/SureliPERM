package com.infumia.t3sl4.util.itemstack.item.set;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.cactoos.scalar.MaxOf;
import org.jetbrains.annotations.NotNull;

public final class SetAmountOf extends ScalarRuntime<ItemStack> {
   public SetAmountOf(@NotNull Scalar<ItemStack> itemStackScalar, Scalar<Integer> amountScalar) {
      super(() -> {
         ItemStack itemStack = (ItemStack)itemStackScalar.value();
         itemStack.setAmount((Integer)amountScalar.value());
         return itemStack;
      });
   }

   public SetAmountOf(@NotNull Scalar<ItemStack> itemStack) {
      this(itemStack, () -> {
         return 1;
      });
   }

   public SetAmountOf(@NotNull Scalar<ItemStack> itemStack, int amount) {
      this(itemStack, () -> {
         return (new MaxOf(new Integer[]{amount, 0})).intValue();
      });
   }

   public SetAmountOf(@NotNull ItemStack itemStack, int amount) {
      this(() -> {
         return itemStack;
      }, amount);
   }

   private SetAmountOf(@NotNull ItemStack itemStack) {
      this((ItemStack)itemStack, 1);
   }
}
