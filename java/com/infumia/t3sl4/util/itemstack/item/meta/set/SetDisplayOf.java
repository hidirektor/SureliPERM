package com.infumia.t3sl4.util.itemstack.item.meta.set;

import com.infumia.t3sl4.util.itemstack.item.meta.get.MetaOf;
import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class SetDisplayOf extends ScalarRuntime<ItemMeta> {
   public SetDisplayOf(@NotNull Scalar<ItemMeta> itemMetaScalar, @NotNull Scalar<String> displayNameScalar) {
      super(() -> {
         ItemMeta itemMeta = (ItemMeta)itemMetaScalar.value();
         itemMeta.setDisplayName((String)displayNameScalar.value());
         return itemMeta;
      });
   }

   public SetDisplayOf(@NotNull ItemStack itemStack, @NotNull Scalar<String> displayName) {
      this((Scalar)(new MetaOf(itemStack)), (Scalar)displayName);
   }

   public SetDisplayOf(@NotNull Scalar<ItemMeta> itemMeta, @NotNull String displayName) {
      this(itemMeta, () -> {
         return displayName;
      });
   }

   public SetDisplayOf(@NotNull ItemMeta itemMeta, @NotNull String displayName) {
      this(() -> {
         return itemMeta;
      }, () -> {
         return displayName;
      });
   }

   public SetDisplayOf(@NotNull ItemStack itemStack, @NotNull String displayName) {
      this((Scalar)(new MetaOf(itemStack)), (String)displayName);
   }

   public SetDisplayOf(@NotNull Material material, @NotNull String displayName) {
      this(new ItemStack(material), displayName);
   }
}
