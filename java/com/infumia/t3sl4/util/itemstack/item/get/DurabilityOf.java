package com.infumia.t3sl4.util.itemstack.item.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class DurabilityOf extends ScalarRuntime<Short> {
   public DurabilityOf(@NotNull Scalar<ItemStack> itemStackScalar) {
      super(() -> {
         return ((ItemStack)itemStackScalar.value()).getDurability();
      });
   }

   public DurabilityOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }
}
