package com.infumia.t3sl4.util.itemstack.item;

import com.infumia.t3sl4.util.itemstack.item.meta.set.SetDisplayOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetFlagOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetLoreOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetMetaOf;
import com.infumia.t3sl4.util.itemstack.item.set.SetAmountOf;
import com.infumia.t3sl4.util.itemstack.item.set.SetDataOf;
import com.infumia.t3sl4.util.itemstack.item.set.SetDurabilityOf;
import com.infumia.t3sl4.util.itemstack.util.XMaterial;
import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import com.infumia.t3sl4.util.itemstack.item.set.SetTypeOf;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public class ItemStackOf extends ScalarRuntime<ItemStack> {
   public ItemStackOf(@NotNull Material material, int amount, short damage, byte data) {
      super(new SetDataOf(new SetDurabilityOf(new SetAmountOf(new SetTypeOf(material), amount), damage), material, data));
   }

   public ItemStackOf(@NotNull Scalar<ItemStack> itemStack, @NotNull Scalar<ItemMeta> itemMeta) {
      super(new SetMetaOf(itemStack, itemMeta));
   }

   public ItemStackOf(@NotNull Scalar<ItemStack> itemStack, @NotNull ItemMeta itemMeta) {
      super(new SetMetaOf(itemStack, itemMeta));
   }

   public ItemStackOf(@NotNull ItemStack itemStack, @NotNull String displayName) {
      this((Scalar)(() -> {
         return itemStack;
      }), (Scalar)(new SetDisplayOf(itemStack, displayName)));
   }

   public ItemStackOf(@NotNull ItemStack itemStack, @NotNull String... lore) {
      this((Scalar)(() -> {
         return itemStack;
      }), (Scalar)(new SetLoreOf(itemStack, lore)));
   }

   public ItemStackOf(@NotNull ItemStack itemStack, @NotNull ItemFlag... flags) {
      this((Scalar)(() -> {
         return itemStack;
      }), (Scalar)(new SetFlagOf(itemStack, flags)));
   }

   public ItemStackOf(@NotNull Material material, int amount, byte data) {
      this(material, amount, (short)0, data);
   }

   public ItemStackOf(@NotNull Material material, int amount) {
      this(material, amount, (byte)0);
   }

   public ItemStackOf(@NotNull Material material) {
      this(material, 1);
   }

   public ItemStackOf(@NotNull String materialString) {
      this(XMaterial.matchXMaterial(materialString).parseMaterial());
   }
}
