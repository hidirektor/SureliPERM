package com.infumia.t3sl4.util.nbt.base;

import com.infumia.t3sl4.util.nbt.api.Element;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackNBTOf extends ElementEnvelope<ItemStack, NBTCompound> {
   public ItemStackNBTOf(@NotNull ItemStack element) {
      super(element);
   }

   public ItemStackNBTOf(@NotNull Item element) {
      this(element.getItemStack());
   }

   public ItemStackNBTOf(@NotNull Material element) {
      this(new ItemStack(element));
   }

   public ItemStackNBTOf(@NotNull NBTCompound element) {
      this(REGISTRY.toItemStack(element));
   }

   public ItemStackNBTOf(@NotNull Element<String, NBTCompound> element) {
      this((NBTCompound)element.nbt());
   }

   @NotNull
   public NBTCompound nbt() {
      return REGISTRY.toCompound((ItemStack)this.element());
   }

   @NotNull
   public ItemStack apply(@NotNull NBTCompound applied) {
      return REGISTRY.toItemStack(applied);
   }
}
