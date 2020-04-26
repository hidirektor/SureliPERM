package com.infumia.t3sl4.sandikspawner;

import com.infumia.t3sl4.sandikspawner.listener.*;
import io.github.portlek.inventory.Inventories;
import io.github.portlek.mcyaml.YamlOf;
import com.infumia.t3sl4.sandikspawner.command.CommandChestSpawner;
import com.infumia.t3sl4.sandikspawner.files.ConfigOptions;
import com.infumia.t3sl4.sandikspawner.hook.VaultHook;
import com.infumia.t3sl4.sandikspawner.hook.VaultWrapper;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.cactoos.list.ListOf;

public final class SandikSpawner extends JavaPlugin {
   public void onEnable() {
      if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
         VaultHook hook = new VaultHook();
         if (hook.initiate()) {
            VaultWrapper vaultWrapper = new VaultWrapper(hook.get());
            Bukkit.getConsoleSender().sendMessage("   ");
            Bukkit.getConsoleSender().sendMessage("  ___            __                       _         ");
            Bukkit.getConsoleSender().sendMessage(" |_ _|  _ __    / _|  _   _   _ __ ___   (_)   __ _ ");
            Bukkit.getConsoleSender().sendMessage("  | |  | '_ \\  | |_  | | | | | '_ ` _ \\  | |  / _` |");
            Bukkit.getConsoleSender().sendMessage("  | |  | | | | |  _| | |_| | | | | | | | | | | (_| |");
            Bukkit.getConsoleSender().sendMessage(" |___| |_| |_| |_|    \\__,_| |_| |_| |_| |_|  \\__,_|");
            Bukkit.getConsoleSender().sendMessage("    ");
            PluginCommand chestSpawnerCommand = this.getCommand("sandiksp");
            if (chestSpawnerCommand != null) {
               SpawnerAPI spawnerAPI = new SpawnerAPI(this, vaultWrapper, new YamlOf(this, "chest/menuler", "chesticimenu"), new YamlOf(this, "chest/menuler", "genelayarlarmenu"), new YamlOf(this, "chest/menuler", "ganimetdeposumenu"), new YamlOf(this, "chest/menuler", "sandikayarlarmenu"), new YamlOf(this, "chest/menuler", "yetkililermenu"), new YamlOf(this, "chest", "spawners"), new YamlOf(this, "chest", "users"), new ConfigOptions(new YamlOf(this, "config")), new YamlOf(this, "chest", "placed"));
               this.getServer().getScheduler().runTaskTimer(this, () -> {
                  SpawnerAPI.PLACED_CHESTS.values().forEach(ChestPlaced::updateSign);
               }, 0L, 20L);
               spawnerAPI.reloadPlugin();
               (new Inventories()).prepareFor(this);
               (new ListOf(new Listener[]{new ChestControl(spawnerAPI), new ChestClick(spawnerAPI), new ChestBreak(spawnerAPI), new ChestPlace(spawnerAPI), new SignClick(spawnerAPI), new SignBreak(spawnerAPI)})).forEach((listener) -> {
                  this.getServer().getPluginManager().registerEvents((Listener) listener, this);
               });
               CommandChestSpawner commandChestSpawner = new CommandChestSpawner(spawnerAPI);
               chestSpawnerCommand.setExecutor(commandChestSpawner);
               chestSpawnerCommand.setTabCompleter(commandChestSpawner);
            }
         }
      }
   }

}
