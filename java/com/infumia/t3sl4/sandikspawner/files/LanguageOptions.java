package com.infumia.t3sl4.sandikspawner.files;

import com.infumia.t3sl4.util.itemstack.util.Colored;
import io.github.portlek.mcyaml.IYaml;
import java.util.List;

import io.github.portlek.mcyaml.YamlOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LanguageOptions {
   @NotNull
   private final IYaml yaml;
   @Nullable
   private Language language;

   public LanguageOptions(@NotNull YamlOf yaml) {
      this.yaml = yaml;
   }

   @NotNull
   public Language create() {
      if (this.language != null) {
         return this.language;
      } else {
         this.yaml.create();
         String errorPermission = this.c((String)this.yaml.getOrSet("error.permission", "error.permission"));
         String errorSpawnerNotFound = this.c((String)this.yaml.getOrSet("error.spawner-not-found", "error.spawner-not-found"));
         String errorYourInventoryIsFull = this.c((String)this.yaml.getOrSet("error.your-inventory-is-full", "error.your-inventory-is-full"));
         String errorPlayerInventoryIsFull = this.c((String)this.yaml.getOrSet("error.player-inventory-is-full", "error.player-inventory-is-full"));
         String errorBreakerNotFound = this.c((String)this.yaml.getOrSet("error.breaker-not-found", "error.breaker-not-found"));
         String errorInputNumber = this.c((String)this.yaml.getOrSet("error.input-number", "error.input-number"));
         String errorPlayerNotFound = this.c((String)this.yaml.getOrSet("error.player-not-found", "error.player-not-found"));
         String errorInGameCommand = this.c((String)this.yaml.getOrSet("error.in-game-command", "error.in-game-command"));
         String errorYouCantTakeThisSoMuch = this.c((String)this.yaml.getOrSet("error.you-cant-take-this-so-much", "error.you-cant-take-this-so-much"));
         String errorYouCantAddYourself = this.c((String)this.yaml.getOrSet("error.you-cant-add-yourself", "error.you-cant-add-yourself"));
         String errorPlayerAlreadyAdded = this.c((String)this.yaml.getOrSet("error.player-already-added", "error.player-already-added"));
         String errorNotEnoughItem = this.c((String)this.yaml.getOrSet("error.not-enough-item", "error.not-enough-item"));
         String generalYouGaveASpawner = this.c((String)this.yaml.getOrSet("general.you-gave-a-spawner", "general.you-gave-a-spawner"));
         String generalYouTookASpawner = this.c((String)this.yaml.getOrSet("general.you-took-a-spawner", "general.you-took-a-spawner"));
         String generalYouGaveABreaker = this.c((String)this.yaml.getOrSet("general.you-gave-a-breaker", "general.you-gave-a-breaker"));
         String generalYouTookABreaker = this.c((String)this.yaml.getOrSet("general.you-took-a-breaker", "general.you-took-a-breaker"));
         String generalYouBreakAChestSpawner = this.c((String)this.yaml.getOrSet("general.you-break-a-chest-spawner", "general.you-break-a-chest-spawner"));
         String generalYouPlaceAChestSpawner = this.c((String)this.yaml.getOrSet("general.you-place-a-chest-spawner", "general.you-place-a-chest-spawner"));
         String generalPlayerAdded = this.c((String)this.yaml.getOrSet("general.player-added", "general.player-added"));
         String generalReloadComplete = this.c((String)this.yaml.getOrSet("general.reload-complete", "general.reload-complete"));
         String generalCombined = this.c((String)this.yaml.getOrSet("general.combined", "general.combined"));
         String commandReload = (String)this.yaml.getOrSet("command.reload", "command.reload");
         String commandItemSpawnerGive = (String)this.yaml.getOrSet("command.item-spawner.give", "command.item-spawner.give");
         String commandItemSpawnerDistribute = (String)this.yaml.getOrSet("command.item-spawner.distribute", "command.item-spawner.distribute");
         String commandItemSpawnerBreaker = (String)this.yaml.getOrSet("command.item-spawner.breaker", "command.item-spawner.breaker");
         String commandChestSpawnerSettings = (String)this.yaml.getOrSet("command.chest-spawner.settings", "command.chest-spawner.settings");
         String commandChestSpawnerGive = (String)this.yaml.getOrSet("command.chest-spawner.give", "command.chest-spawner.give");
         String commandChestSpawnerDistribute = (String)this.yaml.getOrSet("command.chest-spawner.distribute", "command.chest-spawner.distribute");
         String chestStatusIn = (String)this.yaml.getOrSet("chest-status.in", "chest-status.in");
         String chestStatusOut = (String)this.yaml.getOrSet("chest-status.out", "chest-status.out");
         String chestStatusStorage = (String)this.yaml.getOrSet("chest-status.storage", "chest-status.storage");
         String menuStatusOffline = (String)this.yaml.getOrSet("menu-status.offline", "menu-status.offline");
         String menuStatusOnline = (String)this.yaml.getOrSet("menu-status.online", "menu-status.online");
         this.language = new Language(errorPermission, errorSpawnerNotFound, errorYourInventoryIsFull, errorPlayerInventoryIsFull, errorBreakerNotFound, errorInputNumber, errorPlayerNotFound, errorInGameCommand, errorYouCantTakeThisSoMuch, errorYouCantAddYourself, errorPlayerAlreadyAdded, errorNotEnoughItem, generalYouGaveASpawner, generalYouTookASpawner, generalYouGaveABreaker, generalYouTookABreaker, generalYouPlaceAChestSpawner, generalYouBreakAChestSpawner, generalPlayerAdded, generalReloadComplete, generalCombined, commandReload, commandItemSpawnerGive, commandItemSpawnerDistribute, commandItemSpawnerBreaker, commandChestSpawnerSettings, commandChestSpawnerGive, commandChestSpawnerDistribute, chestStatusIn, chestStatusOut, chestStatusStorage, menuStatusOffline, menuStatusOnline, this.cL(this.yaml.getStringList("commands")));
         return this.language;
      }
   }

   @NotNull
   public Language getLanguage() {
      if (this.language == null) {
         this.create();
      }

      return this.language;
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
