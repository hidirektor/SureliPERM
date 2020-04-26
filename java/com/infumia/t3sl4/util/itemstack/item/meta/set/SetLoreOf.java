package com.infumia.t3sl4.util.itemstack.item.meta.set;

import com.infumia.t3sl4.util.itemstack.item.meta.get.MetaOf;
import com.infumia.t3sl4.util.itemstack.ScalarRuntime;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;
import org.jetbrains.annotations.NotNull;

public final class SetLoreOf extends ScalarRuntime<ItemMeta> {
   public SetLoreOf(@NotNull Scalar<ItemMeta> itemMetaScalar, @NotNull Scalar<List<String>> loreScalar) {
      super(() -> {
         ItemMeta itemMeta = (ItemMeta)itemMetaScalar.value();
         itemMeta.setLore((List)loreScalar.value());
         return itemMeta;
      });
   }

   public SetLoreOf(@NotNull ItemMeta itemMeta, @NotNull List<String> lore) {
      this(() -> {
         return itemMeta;
      }, () -> {
         return lore;
      });
   }

   public SetLoreOf(@NotNull ItemMeta itemMeta, @NotNull String... lore) {
      this((ItemMeta)itemMeta, (List)(new ListOf(lore)));
   }

   public SetLoreOf(@NotNull ItemStack itemStack, @NotNull List<String> lore) {
      this((Scalar)(new MetaOf(itemStack)), (Scalar)(() -> {
         return lore;
      }));
   }

   public SetLoreOf(@NotNull ItemStack itemStack, @NotNull String... lore) {
      this((Scalar)(new MetaOf(itemStack)), (Scalar)(() -> {
         return new ListOf(lore);
      }));
   }
}
