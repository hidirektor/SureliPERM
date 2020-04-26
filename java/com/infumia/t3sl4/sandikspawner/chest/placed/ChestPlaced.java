package com.infumia.t3sl4.sandikspawner.chest.placed;

import com.infumia.t3sl4.util.itemstack.util.Colored;
import com.infumia.t3sl4.util.location.StringOf;
import io.github.portlek.mcyaml.IYaml;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.cactoos.scalar.SumOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ChestPlaced {
   @NotNull
   private final SpawnerAPI spawnerAPI;
   @NotNull
   public final UUID uuid;
   @NotNull
   public final UUID owner;
   @NotNull
   public static Chest chest;
   @NotNull
   public final Sign sign;
   @NotNull
   public final ChestType chestType;
   public int storage;
   private int durum;
   @NotNull
   private String esyaDusmeDurumu = "İç";
   public int level;
   @Nullable
   private BukkitTask task;

   public ChestPlaced(@NotNull SpawnerAPI spawnerAPI, @NotNull UUID uuid, @NotNull UUID owner, @NotNull Chest chest, @NotNull Sign sign, @NotNull ChestType chestType, int storage, int durum, int level) {
      this.spawnerAPI = spawnerAPI;
      this.uuid = uuid;
      this.owner = owner;
      this.chest = chest;
      this.sign = sign;
      this.chestType = chestType;
      this.storage = storage;
      this.durum = durum;
      this.level = level;
      this.updateEsyaDusmesi();
   }

   public void changeEsyaDusmesi(int i) {
      this.durum = i;
      this.updateEsyaDusmesi();
      this.saveTo(this.spawnerAPI.placedChest);
   }

   public void changeEsyaDusmesi() {
      if (this.durum < 2) {
         ++this.durum;
      } else {
         this.durum = 0;
      }

      this.updateEsyaDusmesi();
      this.saveTo(this.spawnerAPI.placedChest);
   }

   private void updateEsyaDusmesi() {
      if (this.durum == 0) {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusIn;
      } else if (this.durum == 1) {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusOut;
      } else {
         this.esyaDusmeDurumu = this.spawnerAPI.getLanguage().chestStatusStorage;
      }

   }

   public void remove() {
      this.close();
      Bukkit.getScheduler().runTask(this.spawnerAPI.plugin, () -> {
         this.sign.getBlock().setType(Material.AIR);
         this.chest.getBlock().setType(Material.AIR);
      });
   }

   public void levelUp() {
      if (this.level < this.chestType.maxLevel()) {
         ++this.level;
         this.update();
         this.saveTo(this.spawnerAPI.placedChest);
      }
   }

   public void saveTo(@NotNull IYaml yaml) {
      yaml.set("Spawners." + this.uuid.toString() + ".owner", this.owner.toString());
      yaml.set("Spawners." + this.uuid.toString() + ".chest-location", (new StringOf(this.chest.getLocation())).asKey());
      yaml.set("Spawners." + this.uuid.toString() + ".sign-location", (new StringOf(this.sign.getLocation())).asKey());
      yaml.set("Spawners." + this.uuid.toString() + ".type", this.chestType.getId());
      yaml.set("Spawners." + this.uuid.toString() + ".level", this.level);
      yaml.set("Spawners." + this.uuid.toString() + ".durum", this.durum);
      yaml.set("Spawners." + this.uuid.toString() + ".storage", this.storage);
   }

   public void updateSign() {
      this.sign.setLine(0, this.replaceSignLines((String)this.spawnerAPI.getConfigs().sign.get(0)));
      this.sign.setLine(1, this.replaceSignLines((String)this.spawnerAPI.getConfigs().sign.get(1)));
      this.sign.setLine(2, this.replaceSignLines((String)this.spawnerAPI.getConfigs().sign.get(2)));
      this.sign.setLine(3, this.replaceSignLines((String)this.spawnerAPI.getConfigs().sign.get(3)));
      this.sign.update();
   }

   public void update() {
      this.updateSign();
      this.close();
      this.task = Bukkit.getScheduler().runTaskTimer(this.spawnerAPI.plugin, () -> {
         if (Bukkit.getPlayer(this.owner) != null) {
            ItemStack drop = this.chestType.getDrop(this.level);
            if (this.durum == 0) {
               this.chest.getBlockInventory().addItem(new ItemStack[]{drop});
            } else if (this.durum == 1) {
               Location location = this.chest.getLocation();
               if (location.getWorld() != null) {
                  location.getWorld().dropItemNaturally(location, drop);
               }
            } else {
               this.addItemToStorage(drop);
            }
         }
      }, 0L, (long)this.chestType.speed(this.level - 1) * 20L);
   }

   public void close() {
      if (this.task != null) {
         this.task.cancel();
         this.task = null;
      }

   }

   public void addItemToStorage(@NotNull ItemStack itemStack) {
      this.storage = (new SumOf(new Integer[]{itemStack.getAmount(), this.storage})).intValue();
      this.saveTo(this.spawnerAPI.placedChest);
   }

   @NotNull
   private String replaceSignLines(@NotNull String line) {
      return (String)(new Colored(line.replaceAll("%chest-name%", this.chestType.getName()).replaceAll("%chest-owner%", (String)Optional.ofNullable(Bukkit.getOfflinePlayer(this.owner).getName()).orElse("UNKNOWN")).replaceAll("%chest-level%", String.valueOf(this.level)).replaceAll("%durum%", this.spawnerAPI.getDurumFromInt(this.durum)))).value();
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
      return replaced.replaceAll("%durum%", this.esyaDusmeDurumu);
   }
}
