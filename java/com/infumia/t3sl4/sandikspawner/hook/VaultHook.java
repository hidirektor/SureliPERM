package com.infumia.t3sl4.sandikspawner.hook;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class VaultHook {
   @Nullable
   private Economy economy;

   public boolean initiate() {
      RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
      if (economyProvider != null) {
         this.economy = (Economy)economyProvider.getProvider();
      }

      return this.economy != null;
   }

   @NotNull
   public Economy get() {
      return this.economy;
   }
}
