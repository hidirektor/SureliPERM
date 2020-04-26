package com.infumia.t3sl4.sandikspawner.util;

import io.github.portlek.versionmatched.Version;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class HandItem {
   private final Version version = new Version();
   @NotNull
   private final Player player;

   public HandItem(@NotNull Player player) {
      this.player = player;
   }

   @NotNull
   public ItemStack value() {
      return this.version.minor() < 9 ? this.player.getItemInHand() : this.player.getInventory().getItemInMainHand();
   }
}
