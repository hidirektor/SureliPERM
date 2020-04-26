package com.infumia.t3sl4.sandikspawner.hook;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VaultWrapper {
   @NotNull
   private final Economy economy;

   public VaultWrapper(@NotNull Economy economy) {
      this.economy = economy;
   }

   public double getMoney(@NotNull Player player) {
      return this.economy.getBalance(player);
   }

   public void addMoney(@NotNull Player player, double money) {
      this.economy.depositPlayer(player, money);
   }

   public void removeMoney(@NotNull Player player, double money) {
      if (this.getMoney(player) >= money) {
         this.economy.withdrawPlayer(player, money);
      }
   }
}
