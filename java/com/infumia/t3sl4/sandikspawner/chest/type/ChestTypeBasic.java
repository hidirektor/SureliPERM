package com.infumia.t3sl4.sandikspawner.chest.type;

import com.infumia.t3sl4.util.itemstack.item.meta.get.DisplayOf;
import com.infumia.t3sl4.util.itemstack.item.meta.get.LoreOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetDisplayOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetLoreOf;
import com.infumia.t3sl4.util.itemstack.item.meta.set.SetMetaOf;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.base.ItemStackNBTOf;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import java.util.List;
import java.util.Optional;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.jetbrains.annotations.NotNull;

public final class ChestTypeBasic implements ChestType {
   @NotNull
   private final SpawnerAPI spawnerAPI;
   @NotNull
   private final String id;
   @NotNull
   private final String name;
   @NotNull
   private final List<Integer> speed;
   @NotNull
   private final List<Integer> amount;
   private final int money;
   @NotNull
   private final ItemStack drop;

   public ChestTypeBasic(@NotNull SpawnerAPI spawnerAPI, @NotNull String id, @NotNull String name, @NotNull List<Integer> speed, @NotNull List<Integer> amount, int money, @NotNull ItemStack drop) {
      this.spawnerAPI = spawnerAPI;
      this.id = id;
      this.name = name;
      this.speed = speed;
      this.amount = amount;
      this.money = money;
      this.drop = drop;
   }

   @NotNull
   public String getId() {
      return this.id;
   }

   public int maxLevel() {
      return this.speed.size();
   }

   @NotNull
   public String getName() {
      return this.name;
   }

   @NotNull
   public ItemStack getDrop() {
      return this.drop;
   }

   @NotNull
   public ItemStack getDrop(int level) {
      ItemStack itemStack = this.drop.clone();
      itemStack.setAmount((Integer)this.amount.get(level - 1));
      return itemStack;
   }

   public int money() {
      return this.money;
   }

   @NotNull
   public ItemStack getChestItem(int level) {
      ItemStackNBTOf itemStackNBTOf = new ItemStackNBTOf(this.spawnerAPI.getConfigs().chestItem.clone());
      NBTCompound nbtCompound = itemStackNBTOf.nbt();
      NBTCompound tagCompound = nbtCompound.getNBTCompound("tag");
      tagCompound.setString("chest-spawner-id", this.id);
      tagCompound.setInt("chest-spawner-level", level);
      nbtCompound.set("tag", tagCompound);
      ItemStack withId = itemStackNBTOf.apply(nbtCompound);
      return (ItemStack)(new SetMetaOf(withId, new SetLoreOf((ItemMeta)(new SetDisplayOf(withId, ((String)((Optional)(new DisplayOf(withId)).value()).orElse("")).replaceAll("%chest-name%", this.name).replaceAll("%chest-level%", String.valueOf(level)))).value(), new ListOf(new Mapped<>((lore) -> {
         return lore.replaceAll("%chest-name%", this.name).replaceAll("%chest-level%", String.valueOf(level));
      }, (new LoreOf(withId)).value()))))).value();
   }

   public int speed(int level) {
      return (Integer)this.speed.get(level);
   }
}
