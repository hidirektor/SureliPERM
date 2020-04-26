package com.infumia.t3sl4.sandikspawner.chest.user;

import io.github.portlek.inventory.Page;
import io.github.portlek.mcyaml.IYaml;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import com.infumia.t3sl4.sandikspawner.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOfInts;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.cactoos.scalar.SumOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class User {
   @NotNull
   private final SpawnerAPI spawnerAPI;
   @NotNull
   public final UUID uuid;
   @NotNull
   public final Map<UUID, Long> yetkililer;
   @NotNull
   public final List<ChestPlaced> placeds;
   @NotNull
   public String esyaDusmeDurumu;
   private int esyaDusmePosition;

   @Nullable
   public Player getPlayer() {
      return Bukkit.getPlayer(this.uuid);
   }

   public User(@NotNull SpawnerAPI spawnerAPI, @NotNull UUID uuid, @NotNull Map<UUID, Long> yetkililer, @NotNull List<ChestPlaced> placeds, @NotNull String esyaDusmeDurumu, int esyaDusmePosition) {
      this.spawnerAPI = spawnerAPI;
      this.uuid = uuid;
      this.yetkililer = yetkililer;
      this.placeds = placeds;
      this.esyaDusmeDurumu = esyaDusmeDurumu;
      this.esyaDusmePosition = esyaDusmePosition;
   }

   public void addYetkili(@NotNull UUID uuid) {
      this.yetkililer.put(uuid, (new Date()).getTime());
      this.saveTo(this.spawnerAPI.usersFile);
   }

   public void removeYetkili(@NotNull UUID uuid) {
      this.yetkililer.remove(uuid);
      this.saveTo(this.spawnerAPI.usersFile);
   }

   public void openPage(@NotNull Page page) {
      if (this.getPlayer() != null) {
         page.showTo(this.getPlayer());
      }

   }

   public void changeEsyaDusmesi() {
      if (this.esyaDusmePosition < 2) {
         ++this.esyaDusmePosition;
      } else {
         this.esyaDusmePosition = 0;
      }

      this.updateEsyaDusmesi();
      this.saveTo(this.spawnerAPI.usersFile);
      this.placeds.forEach((chestPlaced) -> {
         chestPlaced.changeEsyaDusmesi(this.esyaDusmePosition);
      });
   }

   private void updateEsyaDusmesi() {
      if (this.esyaDusmePosition == 0) {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusIn;
      } else if (this.esyaDusmePosition == 1) {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusOut;
      } else {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusStorage;
      }

   }

   public void replaceAll(@NotNull ItemStack itemStack) {
      ItemMeta meta = itemStack.getItemMeta();
      if (meta != null) {
         String display = meta.getDisplayName();
         List<String> lore = meta.getLore() == null ? new ListOf(new String[0]) : meta.getLore();
         meta.setDisplayName(this.replaceAll(display));
         meta.setLore(this.replaceAll((List)lore));
         itemStack.setItemMeta(meta);
      }
   }

   @NotNull
   public List<String> replaceAll(@NotNull List<String> replaced) {
      return new Mapped<>(this::replaceAll, replaced);
   }

   @NotNull
   public String replaceAll(@NotNull String replaced) {
      return replaced.replaceAll("%durum%", this.esyaDusmeDurumu).replaceAll("%yetkililer%", String.valueOf(this.yetkililer.size()));
   }

   @NotNull
   public List<ChestType> getHaveChestTypes() {
      List<ChestType> haveChestTypes = new ArrayList();
      Iterator var2 = this.placeds.iterator();

      while(var2.hasNext()) {
         ChestPlaced placed = (ChestPlaced)var2.next();
         if (!(new Mapped<>(ChestType::getId, haveChestTypes)).contains(placed.chestType.getId())) {
            haveChestTypes.add(placed.chestType);
         }
      }

      return haveChestTypes;
   }

   @NotNull
   public List<ChestPlaced> getPlaceds(@NotNull ChestType chestType) {
      return new ListOf(new Filtered<>((chestPlaced) -> {
         return chestPlaced.chestType.getId().equals(chestType.getId());
      }, this.placeds));
   }

   public int getAmountOfChestType(@NotNull ChestType chestType) {
      return (new SumOf(new Mapped((filtered) -> {
         return 1;
      }, new Filtered<>((placed) -> {
         return placed.chestType.getId().equals(chestType.getId());
      }, this.placeds)))).intValue();
   }

   public int getStorageAmountOfChestType(@NotNull ChestType chestType) {
      return (new SumOf(new Mapped<>((chestPlaced) -> {
         return chestPlaced.storage;
      }, this.getPlaceds(chestType)))).intValue();
   }

   public int getAvailableAmountForItem(@NotNull ItemStack itemStack) {
      if (this.getPlayer() != null && this.getPlayer().getInventory().firstEmpty() != -1) {
         ItemStack[] items = Util.getStorageContents(this.getPlayer().getInventory());
         return (new SumOf(new Mapped<>((i) -> {
            ItemStack inventoryItem = items[i];
            return inventoryItem != null ? 0 : itemStack.getMaxStackSize();
         }, new IterableOfInts(IntStream.range(0, items.length).toArray())))).intValue();
      } else {
         return 0;
      }
   }

   public void sellStorageItem(@NotNull Player player, @NotNull ChestType chestType, int amount) {
      this.removeStorage(chestType, amount);
      this.spawnerAPI.vaultWrapper.addMoney(player, (double)chestType.money() * (double)amount);
   }

   public void saveTo(@NotNull IYaml yaml) {
      yaml.set("Users." + this.uuid.toString() + ".durum", this.esyaDusmePosition);
      yaml.set("Users." + this.uuid.toString() + ".yetkililer", (Object)null);
      yaml.set("Users." + this.uuid.toString() + ".placeds", (Object)null);
      Iterator var2 = this.yetkililer.entrySet().iterator();

      while(var2.hasNext()) {
         Entry<UUID, Long> yetkili = (Entry)var2.next();
         yaml.set("Users." + this.uuid.toString() + ".yetkililer." + ((UUID)yetkili.getKey()).toString(), yetkili.getValue());
      }

      yaml.set("Users." + this.uuid.toString() + ".placeds", new ListOf(new Mapped<>((chestPlaced) -> {
         return chestPlaced.uuid.toString();
      }, this.placeds)));
   }

   public void removeStorage(@NotNull ChestType chestType, int amount) {
      if (amount > 0) {
         int cache = amount;

         ChestPlaced placed;
         for(Iterator var4 = this.getPlaceds(chestType).iterator(); var4.hasNext(); placed.saveTo(this.spawnerAPI.placedChest)) {
            placed = (ChestPlaced)var4.next();
            if (placed.storage <= amount) {
               cache -= placed.storage;
               placed.storage = 0;
            } else {
               placed.storage -= cache;
               cache = 0;
            }
         }

      }
   }
}
