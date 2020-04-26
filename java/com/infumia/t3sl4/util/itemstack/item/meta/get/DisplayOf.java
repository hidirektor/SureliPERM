package com.infumia.t3sl4.util.itemstack.item.meta.get;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import java.util.Optional;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class DisplayOf extends ScalarRuntime<Optional<String>> {
   public DisplayOf(@NotNull Scalar<ItemMeta> itemMeta) {
      super(() -> {
         return ((ItemMeta)itemMeta.value()).hasDisplayName() ? Optional.of(((ItemMeta)itemMeta.value()).getDisplayName()) : Optional.empty();
      });
   }

   public DisplayOf(@NotNull ItemMeta itemMeta) {
      this(() -> {
         return itemMeta;
      });
   }

   public DisplayOf(@NotNull ItemStack original) {
      this((Scalar)(new MetaOf(original)));
   }
}
