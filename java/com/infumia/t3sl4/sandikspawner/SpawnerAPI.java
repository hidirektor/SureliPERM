package com.infumia.t3sl4.sandikspawner;

import com.infumia.t3sl4.util.location.LocationOf;
import io.github.portlek.mcyaml.IYaml;
import io.github.portlek.mcyaml.YamlOf;
import io.github.portlek.mcyaml.mck.MckFileConfiguration;
import com.infumia.t3sl4.sandikspawner.files.Config;
import com.infumia.t3sl4.sandikspawner.files.ConfigOptions;
import com.infumia.t3sl4.sandikspawner.files.Language;
import com.infumia.t3sl4.sandikspawner.files.LanguageOptions;
import com.infumia.t3sl4.sandikspawner.hook.VaultWrapper;
import com.infumia.t3sl4.sandikspawner.mock.MckChestType;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestTypeBasic;
import com.infumia.t3sl4.sandikspawner.chest.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.And;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SpawnerAPI {
   protected static final Map<UUID, ChestPlaced> PLACED_CHESTS = new HashMap();
   @NotNull
   public final Plugin plugin;
   @NotNull
   public final VaultWrapper vaultWrapper;
   @NotNull
   public final IYaml chestIciMenu;
   @NotNull
   public final IYaml genelAyarlarMenu;
   @NotNull
   public final IYaml ganimetDeposuMenu;
   @NotNull
   public final IYaml sandikSettingsMenu;
   @NotNull
   public final IYaml yetkilerMenu;
   @NotNull
   public final IYaml chests;
   @NotNull
   public final IYaml usersFile;
   @NotNull
   private final ConfigOptions configOptions;
   @NotNull
   private final LanguageOptions languageOptions;
   @NotNull
   public final IYaml placedChest;
   @NotNull
   public final Map<String, ChestType> chestTypes = new HashMap();
   @NotNull
   public final Map<UUID, User> users = new HashMap();

   public SpawnerAPI(@NotNull Plugin plugin, @NotNull VaultWrapper vaultWrapper, @NotNull IYaml chestIciMenu, @NotNull IYaml genelAyarlarMenu, @NotNull IYaml ganimetDeposuMenu, @NotNull IYaml sandikSettingsMenu, @NotNull IYaml yetkilerMenu, @NotNull IYaml chests, @NotNull IYaml usersFile, @NotNull ConfigOptions configOptions, @NotNull IYaml placedChest) {
      this.plugin = plugin;
      this.vaultWrapper = vaultWrapper;
      this.chestIciMenu = chestIciMenu;
      this.genelAyarlarMenu = genelAyarlarMenu;
      this.ganimetDeposuMenu = ganimetDeposuMenu;
      this.sandikSettingsMenu = sandikSettingsMenu;
      this.yetkilerMenu = yetkilerMenu;
      this.chests = chests;
      this.usersFile = usersFile;
      this.configOptions = configOptions;
      this.languageOptions = new LanguageOptions(new YamlOf(plugin, "languages", this.getConfigs().language));
      this.placedChest = placedChest;
   }

   public void reloadPlugin() {
      this.configOptions.create();
      this.languageOptions.create();
      this.chests.create();
      this.usersFile.create();
      this.placedChest.create();
      if (this.placedChest.getSection("Spawners") instanceof MckFileConfiguration) {
         this.placedChest.createSection("Spawners");
      }

      PLACED_CHESTS.values().forEach(ChestPlaced::close);
      PLACED_CHESTS.clear();
      this.chestIciMenu.create();
      this.genelAyarlarMenu.create();
      this.ganimetDeposuMenu.create();
      this.sandikSettingsMenu.create();
      this.yetkilerMenu.create();
      this.loadChestSpawners();
      this.loadUsers();
   }

   public void add(@NotNull ChestPlaced placed) {
      PLACED_CHESTS.put(placed.uuid, placed);
      placed.saveTo(this.placedChest);
      placed.update();
      User user = this.findUserByUUID(placed.owner);
      user.placeds.add(placed);
      user.saveTo(this.usersFile);
   }

   public void remove(@NotNull ChestPlaced placed) {
      placed.remove();
      PLACED_CHESTS.remove(placed.uuid);
      this.placedChest.set("Spawners." + placed.uuid.toString(), (Object)null);
      User user = this.findUserByUUID(placed.owner);
      user.placeds.remove(placed);
      user.saveTo(this.usersFile);
   }

   @NotNull
   public User findUserByUUID(@NotNull UUID uuid) {
      Iterator var2 = this.users.values().iterator();

      User user;
      do {
         if (!var2.hasNext()) {
            User usr = new User(this, uuid, new HashMap(), new ArrayList(), this.getDurumFromInt(0), 0);
            this.users.put(uuid, usr);
            usr.saveTo(this.usersFile);
            return usr;
         }

         user = (User)var2.next();
      } while(!user.uuid.equals(uuid));

      return user;
   }

   @NotNull
   public User findByPlayer(@NotNull Player player) {
      return this.findUserByUUID(player.getUniqueId());
   }

   @Nullable
   public ChestPlaced findChestBySignLocation(@NotNull Location location) {
      Iterator var2 = PLACED_CHESTS.values().iterator();

      ChestPlaced placed;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         placed = (ChestPlaced)var2.next();
      } while(!placed.sign.getLocation().equals(location));

      return placed;
   }

   @Nullable
   public ChestPlaced findChestByLocation(@NotNull Location location) {
      Iterator var2 = PLACED_CHESTS.values().iterator();

      ChestPlaced placed;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         placed = (ChestPlaced)var2.next();
      } while(!placed.chest.getLocation().equals(location));

      return placed;
   }

   @NotNull
   public ChestType findById(@NotNull String id) {
      return (ChestType)this.chestTypes.getOrDefault(id, new MckChestType());
   }

   @Nullable
   public ChestPlaced findByUUID(@NotNull UUID uuid) {
      Iterator var2 = PLACED_CHESTS.values().iterator();

      ChestPlaced placed;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         placed = (ChestPlaced)var2.next();
      } while(!placed.uuid.equals(uuid));

      return placed;
   }

   @Nullable
   public ChestPlaced findByUUID(@NotNull String uuid) {
      return this.findByUUID(UUID.fromString(uuid));
   }

   @NotNull
   public Config getConfigs() {
      return this.configOptions.getConfig();
   }

   @NotNull
   public Language getLanguage() {
      return this.languageOptions.getLanguage();
   }

   private void loadChestSpawners() {
      this.chestTypes.clear();
      Iterator var1 = this.chests.getSection("").getKeys(false).iterator();

      while(var1.hasNext()) {
         String key = (String)var1.next();
         this.chestTypes.put(key, new ChestTypeBasic(this, key, (String)this.chests.getString(key + ".name").orElse(key), new ListOf(new Mapped((levelKey) -> {
            return this.chests.getInt(key + "." + levelKey + ".speed");
         }, new Filtered<>((filtered) -> {
            return filtered.startsWith("level-");
         }, this.chests.getSection(key).getKeys(false)))), new ListOf(new Mapped((levelKey) -> {
            return this.chests.getInt(key + "." + levelKey + ".amount");
         }, new Filtered<>((filtered) -> {
            return filtered.startsWith("level-");
         }, this.chests.getSection(key).getKeys(false)))), this.chests.getInt(key + ".money"), this.chests.getCustomItemStack(key + ".item")));
      }

      Function<String, UUID> uuid = UUID::fromString;
      Function<String, UUID> owner = (s) -> {
         return UUID.fromString((String)this.placedChest.getString("Spawners." + s + ".owner").orElse(""));
      };
      Function<String, BlockState> chest = (s) -> {
         return (new LocationOf((String)this.placedChest.getString("Spawners." + s + ".chest-location").orElse(""))).value().getBlock().getState();
      };
      Function<String, BlockState> sign = (s) -> {
         return (new LocationOf((String)this.placedChest.getString("Spawners." + s + ".sign-location").orElse(""))).value().getBlock().getState();
      };
      Function<String, ChestType> chestType = (s) -> {
         return this.findById((String)this.placedChest.getString("Spawners." + s + ".type").orElse(""));
      };
      ToIntFunction<String> storage = (s) -> {
         return this.placedChest.getInt("Spawners." + s + ".storage");
      };
      ToIntFunction<String> durum = (s) -> {
         return this.placedChest.getInt("Spawners." + s + ".durum");
      };
      ToIntFunction<String> level = (s) -> {
         return this.placedChest.getInt("Spawners." + s + ".level");
      };
      And chestLoad = new And((filtered) -> {
         ChestPlaced placed = new ChestPlaced(this, (UUID)uuid.apply(filtered), (UUID)owner.apply(filtered), (Chest)chest.apply(filtered), (Sign)sign.apply(filtered), (ChestType)chestType.apply(filtered), storage.applyAsInt(filtered), durum.applyAsInt(filtered), level.applyAsInt(filtered));
         PLACED_CHESTS.put(placed.uuid, placed);
         placed.saveTo(this.placedChest);
         placed.update();
      }, new Filtered<>((keyx) -> {
         return owner.apply(keyx) != null && !(chestType.apply(keyx) instanceof MckChestType) && chest.apply(keyx) instanceof Chest && sign.apply(keyx) instanceof Sign;
      }, this.placedChest.getSection("Spawners").getKeys(false)));

      try {
         chestLoad.value();
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private void loadUsers() {
      this.users.clear();
      Iterator var1 = this.usersFile.getSection("Users").getKeys(false).iterator();

      while(var1.hasNext()) {
         String key = (String)var1.next();
         this.users.put(UUID.fromString(key), new User(this, UUID.fromString(key), new HashMap(new MapOf(new org.cactoos.list.Mapped<>((yetkili) -> {
            return new MapEntry(UUID.fromString(yetkili), this.usersFile.getLong("Users." + key + ".yetkililer." + yetkili));
         }, this.usersFile.getSection("Users." + key + ".yetkililer").getKeys(false)))), new ArrayList(new org.cactoos.list.Mapped<>(this::findByUUID, new Filtered<>((placed) -> {
            return Objects.nonNull(this.findByUUID(placed));
         }, this.usersFile.getStringList("Users." + key + ".placeds")))), this.getDurumFromInt(this.usersFile.getInt("Users." + key + ".durum")), this.usersFile.getInt("Users." + key + ".durum")));
      }

   }

   @NotNull
   public String getDurumFromInt(int durum) {
      if (durum == 0) {
         return this.getLanguage().chestStatusIn;
      } else {
         return durum == 1 ? this.getLanguage().chestStatusOut : this.getLanguage().chestStatusStorage;
      }
   }
}
