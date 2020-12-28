package me.t3sl4.sureliperm.util;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends YamlConfiguration {
    private final JavaPlugin plugin;

    private final File configFile;

    private final String name;

    public Config(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.configFile = new File(this.plugin.getDataFolder(), name + ".yml");
        if (!this.configFile.exists()) {
            this.configFile.getParentFile().mkdirs();
            saveDefault();
        }
        reload();
    }

    public void saveDefault() {
        this.plugin.saveResource(this.name + ".yml", false);
    }

    public void reload() {
        try {
            load(this.configFile);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public void save() {
        try {
            save(this.configFile);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
