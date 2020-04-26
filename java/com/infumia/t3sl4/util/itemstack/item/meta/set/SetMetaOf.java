package com.infumia.t3sl4.util.itemstack.item.meta.set;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class SetMetaOf extends ScalarRuntime<ItemStack> {
   public SetMetaOf(@NotNull Scalar<ItemStack> itemStackScalar, @NotNull Scalar<ItemMeta> itemMetaScalar) {
      super(() -> {
         ItemStack itemStack = (ItemStack)itemStackScalar.value();
         itemStack.setItemMeta((ItemMeta)itemMetaScalar.value());
         return itemStack;
      });
   }

   public SetMetaOf(@NotNull Scalar<ItemStack> itemStack, @NotNull ItemMeta itemMeta) {
      this(itemStack, () -> {
         return itemMeta;
      });
   }

   public SetMetaOf(@NotNull ItemStack itemStack, @NotNull Scalar<ItemMeta> itemMeta) {
      this(() -> {
         return itemStack;
      }, itemMeta);
   }

   public SetMetaOf(@NotNull ItemStack itemStack, @NotNull ItemMeta itemMeta) {
      this(() -> {
         return itemStack;
      }, () -> {
         return itemMeta;
      });
   }
}
