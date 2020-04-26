package com.infumia.t3sl4.util.itemstack.item.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class DataOf extends ScalarRuntime<MaterialData> {
   public DataOf(@NotNull Scalar<ItemStack> itemStackScalar) {
      super(() -> {
         return ((ItemStack)itemStackScalar.value()).getData();
      });
   }

   public DataOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }
}
