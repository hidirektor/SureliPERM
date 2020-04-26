package com.infumia.t3sl4.util.itemstack.item.meta.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;
import org.jetbrains.annotations.NotNull;

public final class LoreOf extends ScalarRuntime<List<String>> {
   public LoreOf(@NotNull Scalar<ItemMeta> itemMeta) {
      super(() -> {
         return (List)(((ItemMeta)itemMeta.value()).hasLore() ? ((ItemMeta)itemMeta.value()).getLore() : new ListOf(new String[0]));
      });
   }

   public LoreOf(@NotNull ItemMeta itemMeta) {
      this(() -> {
         return itemMeta;
      });
   }

   public LoreOf(@NotNull ItemStack itemStack) {
      this((Scalar)(new MetaOf(itemStack)));
   }
}
