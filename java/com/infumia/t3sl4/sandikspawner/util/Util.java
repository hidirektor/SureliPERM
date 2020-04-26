package com.infumia.t3sl4.sandikspawner.util;

import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.util.itemstack.item.set.SetAmountOf;
import com.infumia.t3sl4.util.itemstack.util.XMaterial;
import com.infumia.t3sl4.util.reflection.clazz.ClassOf;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.files.Language;
import com.infumia.t3sl4.sandikspawner.mock.MckChestType;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import io.github.portlek.versionmatched.Version;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.block.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.cactoos.iterable.Filtered;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.cactoos.list.Sorted;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Util {
   private static final Version VERSION = new Version();
   @NotNull
   private SpawnerAPI spawnerAPI;

   private Util() {
   }

   @NotNull
   public static ItemStack[] getStorageContents(@NotNull Inventory inventory) {
      return VERSION.minor() < 9 ? inventory.getContents() : inventory.getStorageContents();
   }

   @Nullable
   public static Block putSignOn(@NotNull Block block, @NotNull BlockFace blockface, @NotNull List<String> lines) {
      Block newSign = block.getRelative(blockface);
      newSign.setType(XMaterial.ACACIA_WALL_SIGN.parseMaterial());
      byte data;
      switch(blockface) {
      case NORTH:
         data = 2;
         break;
      case EAST:
         data = 5;
         break;
      case WEST:
         data = 4;
         break;
      case SOUTH:
         data = 3;
         break;
      default:
         return null;
      }

      (new ClassOf(newSign)).getMethod("setData", Byte.TYPE, Boolean.TYPE).of(newSign).call(newSign, data, true);
      newSign.getState().update();
      Sign sign = (Sign)newSign.getState();
      sign.setLine(0, (String)lines.get(0));
      sign.setLine(1, (String)lines.get(1));
      sign.setLine(2, (String)lines.get(2));
      sign.setLine(3, (String)lines.get(3));
      sign.update();
      return newSign;
   }

   public static boolean inGameCommand(@NotNull Language language, @NotNull CommandSender sender) {
      if (!(sender instanceof Player)) {
         sender.sendMessage(language.errorInGameCommand);
         return false;
      } else {
         return true;
      }
   }

   @NotNull
   public static List<String> listArgument(@NotNull List<String> args, @NotNull String lastWord) {
      return new Sorted(String.CASE_INSENSITIVE_ORDER, new ListOf(new Mapped((argument) -> {
         return argument;
      }, new Filtered<>((arg) -> {
         return StringUtil.startsWithIgnoreCase(arg, lastWord);
      }, args))));
   }

   @NotNull
   public static List<String> listChest(@NotNull SpawnerAPI spawnerAPI, @NotNull String lastWord) {
      return new Sorted(String.CASE_INSENSITIVE_ORDER, new ListOf(new Mapped<>(Entry::getKey, new Filtered<>((spawner) -> {
         return StringUtil.startsWithIgnoreCase((String)spawner.getKey(), lastWord);
      }, spawnerAPI.chestTypes.entrySet()))));
   }

   @NotNull
   public static List<String> listPlayer(@NotNull CommandSender sender, @NotNull String lastWord) {
      Player senderPlayer = sender instanceof Player ? (Player)sender : null;
      return new Sorted(String.CASE_INSENSITIVE_ORDER, new ListOf(new Mapped<>(HumanEntity::getName, new Filtered<>((player) -> {
         return senderPlayer != null && !senderPlayer.canSee(player) || StringUtil.startsWithIgnoreCase(player.getName(), lastWord);
      }, Bukkit.getOnlinePlayers()))));
   }

   public static void giveSpawner(@NotNull Language language, @NotNull CommandSender sender, @NotNull Player player, @NotNull ChestType chestType, @NotNull String spawnerId, int amount) {
      ItemStack itemStack = chestType.getChestItem(1).clone();

      try {
         itemStack = (ItemStack)(new SetAmountOf(itemStack, amount)).value();
      } catch (Exception var8) {
      }

      if (isInventoryFull(player)) {
         sender.sendMessage(language.errorPlayerInventoryIsFull(player.getName()));
         player.sendMessage(language.errorYourInventoryIsFull);
      } else {
         player.getInventory().addItem(new ItemStack[]{itemStack});
         sender.sendMessage(language.generalYouGaveASpawner(player.getName()));
         player.sendMessage(language.generalYouTookASpawner(spawnerId));
      }
   }

   public static boolean isNotNumber(@NotNull Language language, @NotNull CommandSender sender, @NotNull String text) {
      try {
         Integer.parseInt(text);
         return false;
      } catch (Exception var4) {
         sender.sendMessage(language.errorInputNumber);
         return true;
      }
   }

   public static boolean spawnerNotFound(@NotNull Language language, @NotNull CommandSender sender, @NotNull ChestType spawner) {
      if (spawner instanceof MckChestType) {
         sender.sendMessage(language.errorSpawnerNotFound);
         return true;
      } else {
         return false;
      }
   }

   public static boolean playerNotFound(@NotNull Language language, @NotNull CommandSender sender, @NotNull String playerName) {
      if (Bukkit.getPlayer(playerName) != null) {
         return false;
      } else {
         sender.sendMessage(language.errorPlayerNotFound);
         return true;
      }
   }

   public static boolean isInventoryFull(@NotNull Player player) {
      return player.getInventory().firstEmpty() == -1;
   }

}
