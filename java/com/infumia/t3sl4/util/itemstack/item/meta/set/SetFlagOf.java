package com.infumia.t3sl4.util.itemstack.item.meta.set;

import com.infumia.t3sl4.util.itemstack.item.meta.get.MetaOf;
import com.infumia.t3sl4.util.itemstack.ScalarRuntime;

import java.util.List;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;
import org.jetbrains.annotations.NotNull;

public final class SetFlagOf extends ScalarRuntime<ItemMeta> {
   public SetFlagOf(@NotNull Scalar<ItemMeta> itemMetaScalar, @NotNull Scalar<List<ItemFlag>> flagsScalar) {
      super(() -> {
         ItemMeta itemMeta = (ItemMeta)itemMetaScalar.value();
         itemMeta.removeItemFlags((ItemFlag[])itemMeta.getItemFlags().toArray(new ItemFlag[0]));
         itemMeta.addItemFlags((ItemFlag[])((List)flagsScalar.value()).toArray(new ItemFlag[0]));
         return itemMeta;
      });
   }

   public SetFlagOf(@NotNull ItemMeta itemMeta, @NotNull List<ItemFlag> flags) {
      this(() -> {
         return itemMeta;
      }, () -> {
         return flags;
      });
   }

   public SetFlagOf(@NotNull ItemMeta itemMeta, @NotNull ItemFlag... flags) {
      this((ItemMeta)itemMeta, (List)(new ListOf(flags)));
   }

   public SetFlagOf(@NotNull ItemStack itemStack, @NotNull List<ItemFlag> flags) {
      this((Scalar)(new MetaOf(itemStack)), (Scalar)(() -> {
         return flags;
      }));
   }

   public SetFlagOf(@NotNull ItemStack itemStack, @NotNull ItemFlag... flags) {
      this((Scalar)(new MetaOf(itemStack)), (Scalar)(() -> {
         return new ListOf(flags);
      }));
   }
}
