package com.infumia.t3sl4.util.itemstack.item.meta.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class MetaOf extends ScalarRuntime<ItemMeta> {
   public MetaOf(@NotNull Scalar<ItemStack> original) {
      super(() -> {
         return (ItemMeta)Objects.requireNonNull(((ItemStack)original.value()).getItemMeta());
      });
   }

   public MetaOf(@NotNull ItemStack itemStack) {
      this(() -> {
         return itemStack;
      });
   }

   public MetaOf(@NotNull Material material) {
      this(new ItemStack(material));
   }
}
