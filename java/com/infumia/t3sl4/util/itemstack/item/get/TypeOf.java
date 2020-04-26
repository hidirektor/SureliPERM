package com.infumia.t3sl4.util.itemstack.item.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class TypeOf extends ScalarRuntime<Material> {
   public TypeOf(@NotNull Scalar<ItemStack> scalar) {
      super(() -> {
         return ((ItemStack)scalar.value()).getType();
      });
   }

   public TypeOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }
}
