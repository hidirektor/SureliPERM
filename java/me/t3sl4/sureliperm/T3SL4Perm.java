package me.t3sl4.sureliperm;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import me.t3sl4.sureliperm.util.PermRunnable;
import me.t3sl4.sureliperm.util.ListGUI;
import org.bukkit.command.CommandExecutor;
import me.t3sl4.sureliperm.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class T3SL4Perm extends JavaPlugin
{
    private Config config;
    private Config data;

    public void onEnable() {
        this.config = new Config(this, "config");
        this.data = new Config(this, "data");
        this.getCommand("sureliperm").setExecutor((CommandExecutor)new PermCommand(this));
        new ListGUI(this);
        new PermRunnable(this).runTaskTimer((Plugin)this, 0L, 600L);
        Bukkit.getConsoleSender().sendMessage("   ");
        Bukkit.getConsoleSender().sendMessage("  ____   __   __  _   _   _____   _____   ____    _       _  _   ");
        Bukkit.getConsoleSender().sendMessage(" / ___|  \\ \\ / / | \\ | | |_   _| |___ /  / ___|  | |     | || |  ");
        Bukkit.getConsoleSender().sendMessage(" \\___ \\   \\ V /  |  \\| |   | |     |_ \\  \\___ \\  | |     | || |_ ");
        Bukkit.getConsoleSender().sendMessage("  ___) |   | |   | |\\  |   | |    ___) |  ___) | | |___  |__   _|");
        Bukkit.getConsoleSender().sendMessage(" |____/    |_|   |_| \\_|   |_|   |____/  |____/  |_____|    |_|  ");
        Bukkit.getConsoleSender().sendMessage("    ");
    }

    public void onDisable() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
        }
        this.getServer().getScheduler().cancelTasks((Plugin)this);
    }

    public Config getConfig() {
        return this.config;
    }

    public Config getData() {
        return this.data;
    }
}
