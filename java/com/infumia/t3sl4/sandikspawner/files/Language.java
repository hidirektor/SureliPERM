package com.infumia.t3sl4.sandikspawner.files;

import com.infumia.t3sl4.util.itemstack.util.Colored;
import java.util.List;
import org.cactoos.list.ListOf;
import org.jetbrains.annotations.NotNull;

public final class Language {
   @NotNull
   public final String errorPermission;
   @NotNull
   public final String errorSpawnerNotFound;
   @NotNull
   public final String errorYourInventoryIsFull;
   @NotNull
   private final String errorPlayerInventoryIsFull;
   @NotNull
   public final String errorInputNumber;
   @NotNull
   public final String errorPlayerNotFound;
   @NotNull
   public final String errorInGameCommand;
   @NotNull
   public final String errorYouCantTakeThisSoMuch;
   @NotNull
   public final String errorYouCantAddYourself;
   @NotNull
   private final String errorPlayerAlreadyAdded;
   @NotNull
   public final String errorNotEnoughItem;
   @NotNull
   private final String generalYouGaveASpawner;
   @NotNull
   private final String generalYouTookASpawner;
   @NotNull
   private final String generalYouPlaceAChestSpawner;
   @NotNull
   private final String generalYouBreakeAChestSpawner;
   @NotNull
   private final String generalPlayerAdded;
   @NotNull
   public final String generalReloadComplete;
   @NotNull
   public final String commandReload;
   @NotNull
   public final String commandChestSpawnerSettings;
   @NotNull
   public final String commandChestSpawnerGive;
   @NotNull
   public final String commandChestSpawnerDistribute;
   @NotNull
   public final String chestStatusIn;
   @NotNull
   public final String chestStatusOut;
   @NotNull
   public final String chestStatusStorage;
   @NotNull
   public final String menuStatusOffline;
   @NotNull
   public final String menuStatusOnline;
   @NotNull
   public final String commands;

   public Language(@NotNull String errorPermission, @NotNull String errorSpawnerNotFound, @NotNull String errorYourInventoryIsFull, @NotNull String errorPlayerInventoryIsFull, @NotNull String errorBreakerNotFound, @NotNull String errorInputNumber, @NotNull String errorPlayerNotFound, @NotNull String errorInGameCommand, @NotNull String errorYouCantTakeThisSoMuch, @NotNull String errorYouCantAddYourself, @NotNull String errorPlayerAlreadyAdded, @NotNull String errorNotEnoughItem, @NotNull String generalYouGaveASpawner, @NotNull String generalYouTookASpawner, @NotNull String generalYouGaveABreaker, @NotNull String generalYouTookABreaker, @NotNull String generalYouPlaceAChestSpawner, @NotNull String generalYouBreakAChestSpawner, @NotNull String generalPlayerAdded, @NotNull String generalReloadComplete, @NotNull String generalCombined, @NotNull String commandReload, @NotNull String commandItemSpawnerGive, @NotNull String commandItemSpawnerDistribute, @NotNull String commandItemSpawnerBreaker, @NotNull String commandChestSpawnerSettings, @NotNull String commandChestSpawnerGive, @NotNull String commandChestSpawnerDistribute, @NotNull String chestStatusIn, @NotNull String chestStatusOut, @NotNull String chestStatusStorage, @NotNull String menuStatusOffline, @NotNull String menuStatusOnline, @NotNull String commands) {
      this.errorPermission = errorPermission;
      this.errorSpawnerNotFound = errorSpawnerNotFound;
      this.errorYourInventoryIsFull = errorYourInventoryIsFull;
      this.errorPlayerInventoryIsFull = errorPlayerInventoryIsFull;
      this.errorInputNumber = errorInputNumber;
      this.errorPlayerNotFound = errorPlayerNotFound;
      this.errorInGameCommand = errorInGameCommand;
      this.errorYouCantTakeThisSoMuch = errorYouCantTakeThisSoMuch;
      this.errorYouCantAddYourself = errorYouCantAddYourself;
      this.errorPlayerAlreadyAdded = errorPlayerAlreadyAdded;
      this.errorNotEnoughItem = errorNotEnoughItem;
      this.generalYouGaveASpawner = generalYouGaveASpawner;
      this.generalYouTookASpawner = generalYouTookASpawner;
      this.generalYouPlaceAChestSpawner = generalYouPlaceAChestSpawner;
      this.generalYouBreakeAChestSpawner = generalYouBreakAChestSpawner;
      this.generalPlayerAdded = generalPlayerAdded;
      this.generalReloadComplete = generalReloadComplete;
      this.commandReload = commandReload;
      this.commandChestSpawnerSettings = commandChestSpawnerSettings;
      this.commandChestSpawnerGive = commandChestSpawnerGive;
      this.commandChestSpawnerDistribute = commandChestSpawnerDistribute;
      this.chestStatusIn = chestStatusIn;
      this.chestStatusOut = chestStatusOut;
      this.chestStatusStorage = chestStatusStorage;
      this.menuStatusOffline = menuStatusOffline;
      this.menuStatusOnline = menuStatusOnline;
      this.commands = commands;
   }

   @NotNull
   public String errorPlayerAlreadyAdded(@NotNull String player) {
      return this.player(this.errorPlayerAlreadyAdded, player);
   }

   @NotNull
   public String generalPlayerAdded(@NotNull String player) {
      return this.player(this.generalPlayerAdded, player);
   }

   @NotNull
   public String errorPlayerInventoryIsFull(@NotNull String player) {
      return this.player(this.errorPlayerInventoryIsFull, player);
   }

   @NotNull
   public String generalYouGaveASpawner(@NotNull String player) {
      return this.player(this.generalYouGaveASpawner, player);
   }

   @NotNull
   public String generalYouTookASpawner(@NotNull String spawner) {
      return this.spawner(this.generalYouTookASpawner, spawner);
   }

   @NotNull
   public String generalYouPlaceAChestSpawner(@NotNull String spawner) {
      return this.spawner(this.generalYouPlaceAChestSpawner, spawner);
   }

   @NotNull
   public String generalYouBreakeAChestSpawner(@NotNull String spawner) {
      return this.spawner(this.generalYouBreakeAChestSpawner, spawner);
   }

   @NotNull
   public List<String> getChestSpawnerCommands() {
      return new ListOf(new String[]{this.commandChestSpawnerSettings, this.commandChestSpawnerGive, this.commandChestSpawnerDistribute, this.commandReload});
   }

   @NotNull
   private String spawner(@NotNull String text, @NotNull String spawner) {
      return this.c(text.replaceAll("%spawner%", spawner));
   }

   @NotNull
   private String player(@NotNull String text, @NotNull String playerName) {
      return this.c(text.replaceAll("%player%", playerName));
   }

   @NotNull
   private String x(@NotNull String text, int x) {
      return this.c(text.replaceAll("%x%", String.valueOf(x)));
   }

   @NotNull
   private String y(@NotNull String text, int y) {
      return this.c(text.replaceAll("%y%", String.valueOf(y)));
   }

   @NotNull
   private String z(@NotNull String text, int z) {
      return this.c(text.replaceAll("%z%", String.valueOf(z)));
   }

   @NotNull
   private String c(@NotNull String text) {
      try {
         return (String)(new Colored(text)).value();
      } catch (Exception var3) {
         return "";
      }
   }

   @NotNull
   private String cL(@NotNull List<String> list) {
      StringBuilder stringBuilder = new StringBuilder();

      for(int i = 0; i < list.size(); ++i) {
         stringBuilder.append(this.c((String)list.get(i)));
         if (i != list.size() - 1) {
            stringBuilder.append("\n");
         }
      }

      return stringBuilder.toString();
   }
}
