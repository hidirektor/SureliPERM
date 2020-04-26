package com.infumia.t3sl4.sandikspawner.command;

import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import com.infumia.t3sl4.sandikspawner.menu.GenelAyarlarMenu;
import com.infumia.t3sl4.sandikspawner.util.Util;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.SumOf;
import org.jetbrains.annotations.NotNull;

public final class CommandChestSpawner implements TabExecutor {
   @NotNull
   private final SpawnerAPI spawnerAPI;

   public CommandChestSpawner(@NotNull SpawnerAPI spawnerAPI) {
      this.spawnerAPI = spawnerAPI;
   }

   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      if (args.length > 4) {
         if (sender instanceof Player) {
            Player phover = (Player)sender;
            TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
            sender.sendMessage(this.spawnerAPI.getLanguage().commands);
            phover.spigot().sendMessage(msg);
         } else {
            Bukkit.getConsoleSender().sendMessage(this.spawnerAPI.getLanguage().commands);
         }
         return true;
      } else if (args.length == 0) {
         if (sender instanceof Player) {
            Player phover = (Player)sender;
            TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
            sender.sendMessage(this.spawnerAPI.getLanguage().commands);
            phover.spigot().sendMessage(msg);
         } else {
            Bukkit.getConsoleSender().sendMessage(this.spawnerAPI.getLanguage().commands);
         }
         return true;
      } else {
         String arg1 = args[0];
         if (args.length == 1) {
            if (arg1.equals("closeInventory") && sender instanceof Player) {
               Player player = (Player)sender;
               player.closeInventory();
               return true;
            } else if (arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandReload)) {
               if (!sender.hasPermission("sandiksp.reload")) {
                  sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                  return true;
               } else {
                  this.spawnerAPI.reloadPlugin();
                  sender.sendMessage(this.spawnerAPI.getLanguage().generalReloadComplete);
                  return true;
               }
            } else if (arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerSettings)) {
               if (!sender.hasPermission("sandiksp.ayarlar")) {
                  sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                  return true;
               } else if (!Util.inGameCommand(this.spawnerAPI.getLanguage(), sender)) {
                  return true;
               } else {
                  (new GenelAyarlarMenu((Player)sender, this.spawnerAPI)).show();
                  return true;
               }
            } else {
               if (sender instanceof Player) {
                  Player phover = (Player)sender;
                  TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
                  msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
                  sender.sendMessage(this.spawnerAPI.getLanguage().commands);
                  phover.spigot().sendMessage(msg);
               } else {
                  Bukkit.getConsoleSender().sendMessage(this.spawnerAPI.getLanguage().commands);
               }
               return true;
            }
         } else {
            String arg2 = args[1];
            int amount;
            Player player;
            if (args.length == 2) {
               if (arg1.equals("sell") && sender instanceof Player) {
                  Player p = (Player)sender;
                  ChestPlaced placed = this.spawnerAPI.findByUUID(arg2);
                  if (placed != null) {
                     p.closeInventory();
                     amount = (new SumOf(new Mapped<>(ItemStack::getAmount, new Filtered(Objects::nonNull, Util.getStorageContents(placed.chest.getBlockInventory()))))).intValue();
                     int money = amount * placed.chestType.money();
                     placed.chest.getBlockInventory().clear();
                     placed.chest.update();
                     this.spawnerAPI.vaultWrapper.addMoney(p, (double)money);
                     placed.saveTo(this.spawnerAPI.placedChest);
                  }

                  return true;
               } else if (!sender.hasPermission("sandiksp.dagit")) {
                  sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                  return true;
               } else if (!arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerDistribute)) {
                  if (sender instanceof Player) {
                     Player phover = (Player)sender;
                     TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
                     msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
                     sender.sendMessage(this.spawnerAPI.getLanguage().commands);
                     phover.spigot().sendMessage(msg);
                  } else {
                     Bukkit.getConsoleSender().sendMessage(this.spawnerAPI.getLanguage().commands);
                  }
                  return true;
               } else {
                  ChestType chest = this.spawnerAPI.findById(arg2);
                  if (Util.spawnerNotFound(this.spawnerAPI.getLanguage(), sender, chest)) {
                     return true;
                  } else {
                     Iterator var20 = Bukkit.getOnlinePlayers().iterator();

                     while(var20.hasNext()) {
                        player = (Player)var20.next();
                        Util.giveSpawner(this.spawnerAPI.getLanguage(), sender, player, (ChestType)chest, arg2, 1);
                     }

                     return true;
                  }
               }
            } else {
               String arg3 = args[2];
               if (args.length != 3) {
                  String arg4 = args[3];
                  if (!sender.hasPermission("sandiksp.ver")) {
                     sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                     return true;
                  } else if (Util.playerNotFound(this.spawnerAPI.getLanguage(), sender, arg2)) {
                     return true;
                  } else {
                     player = Bukkit.getPlayer(arg2);
                     ChestType spawner = this.spawnerAPI.findById(arg3);
                     if (!Util.spawnerNotFound(this.spawnerAPI.getLanguage(), sender, spawner) && !Util.isNotNumber(this.spawnerAPI.getLanguage(), sender, arg4)) {
                        try {
                           amount = Integer.parseInt(arg4);
                        } catch (Exception var14) {
                           sender.sendMessage(this.spawnerAPI.getLanguage().errorInputNumber);
                           return true;
                        }

                        assert player != null;

                        Util.giveSpawner(this.spawnerAPI.getLanguage(), sender, player, spawner, arg3, amount);
                        return true;
                     } else {
                        return true;
                     }
                  }
               } else if (!arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerDistribute)) {
                  if (arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerGive)) {
                     if (!sender.hasPermission("sandiksp.ver")) {
                        sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                        return true;
                     }

                     if (Util.playerNotFound(this.spawnerAPI.getLanguage(), sender, arg2)) {
                        return true;
                     }

                     Player p = Bukkit.getPlayer(arg2);
                     ChestType chest = this.spawnerAPI.findById(arg3);
                     if (Util.spawnerNotFound(this.spawnerAPI.getLanguage(), sender, chest)) {
                        return true;
                     }

                     assert p != null;

                     Util.giveSpawner(this.spawnerAPI.getLanguage(), sender, p, (ChestType)chest, arg3, 1);
                  }

                  if (sender instanceof Player) {
                     Player phover = (Player)sender;
                     TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
                     msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
                     sender.sendMessage(this.spawnerAPI.getLanguage().commands);
                     phover.spigot().sendMessage(msg);
                  } else {
                     Bukkit.getConsoleSender().sendMessage(this.spawnerAPI.getLanguage().commands);
                  }
                  return true;
               } else if (!sender.hasPermission("sandiksp.dagit")) {
                  sender.sendMessage(this.spawnerAPI.getLanguage().errorPermission);
                  return true;
               } else {
                  ChestType chest = this.spawnerAPI.findById(arg2);
                  if (Util.spawnerNotFound(this.spawnerAPI.getLanguage(), sender, chest)) {
                     return true;
                  } else {
                     try {
                        amount = Integer.parseInt(arg3);
                     } catch (Exception var13) {
                        sender.sendMessage(this.spawnerAPI.getLanguage().errorInputNumber);
                        return true;
                     }

                     Iterator var10 = Bukkit.getOnlinePlayers().iterator();

                     while(var10.hasNext()) {
                        Player p = (Player)var10.next();
                        Util.giveSpawner(this.spawnerAPI.getLanguage(), sender, p, chest, arg2, amount);
                     }

                     return true;
                  }
               }
            }
         }
      }
   }

   @NotNull
   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
      if (!sender.hasPermission("sandiksp.ver") && !sender.hasPermission("sandiksp.dagit") && !sender.hasPermission("sandiksp.ayarlar")) {
         return new ListOf(new String[0]);
      } else if (args.length > 4) {
         return new ListOf(new String[0]);
      } else if (args.length == 0) {
         return this.spawnerAPI.getLanguage().getChestSpawnerCommands();
      } else {
         String arg1 = args[0];
         String lastWord = args[args.length - 1];
         if (args.length == 1) {
            return Util.listArgument(this.spawnerAPI.getLanguage().getChestSpawnerCommands(), lastWord);
         } else if (args.length == 2) {
            return arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerGive) ? Util.listPlayer(sender, lastWord) : Util.listChest(this.spawnerAPI, lastWord);
         } else if (args.length == 3) {
            if (arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerGive)) {
               return Util.listChest(this.spawnerAPI, lastWord);
            } else {
               return arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerDistribute) ? new ListOf(new String[]{"[<amount>]"}) : new ListOf(new String[0]);
            }
         } else {
            return arg1.equalsIgnoreCase(this.spawnerAPI.getLanguage().commandChestSpawnerGive) ? new ListOf(new String[]{"[<amount>]"}) : new ListOf(new String[0]);
         }
      }
   }
}
