package com.infumia.t3sl4.sureliperm;

import com.infumia.t3sl4.sureliperm.util.Config;
import com.infumia.t3sl4.sureliperm.util.ListGui;
import com.infumia.t3sl4.sureliperm.util.PermRunnable;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SureliPERM extends JavaPlugin {
   private Config config;
   private Config data;

   public void onEnable() {
      this.config = new Config(this, "config");
      this.data = new Config(this, "data");
      this.getCommand("sureliperm").setExecutor(new PermCommand(this));
      new ListGui(this);
      (new PermRunnable(this)).runTaskTimer(this, 0L, 600L);
      Bukkit.getConsoleSender().sendMessage("   ");
      Bukkit.getConsoleSender().sendMessage("  ___            __                       _         ");
      Bukkit.getConsoleSender().sendMessage(" |_ _|  _ __    / _|  _   _   _ __ ___   (_)   __ _ ");
      Bukkit.getConsoleSender().sendMessage("  | |  | '_ \\  | |_  | | | | | '_ ` _ \\  | |  / _` |");
      Bukkit.getConsoleSender().sendMessage("  | |  | | | | |  _| | |_| | | | | | | | | | | (_| |");
      Bukkit.getConsoleSender().sendMessage(" |___| |_| |_| |_|    \\__,_| |_| |_| |_| |_|  \\__,_|");
      Bukkit.getConsoleSender().sendMessage("    ");
   }

   public void onDisable() {
      Iterator var1 = Bukkit.getOnlinePlayers().iterator();

      while(var1.hasNext()) {
         Player p = (Player)var1.next();
         p.closeInventory();
      }

      this.getServer().getScheduler().cancelTasks(this);
   }

   public Config getConfig() {
      return this.config;
   }

   public Config getData() {
      return this.data;
   }
}
