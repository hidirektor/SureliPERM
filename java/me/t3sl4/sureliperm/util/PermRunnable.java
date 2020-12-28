package me.t3sl4.sureliperm.util;

import me.t3sl4.sureliperm.T3SL4Perm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class PermRunnable extends BukkitRunnable {
    private T3SL4Perm t3SL4PERM;

    public PermRunnable(T3SL4Perm t3SL4PERM) {
        this.t3SL4PERM = t3SL4PERM;
    }

    public void run() {
        Iterator<String> var1 = this.t3SL4PERM.getData().getConfigurationSection("").getKeys(false).iterator();
        while (var1.hasNext()) {
            String username = var1.next();
            ConfigurationSection permSection = this.t3SL4PERM.getData().getConfigurationSection(username);
            Iterator<String> var4 = permSection.getKeys(false).iterator();
            while (var4.hasNext()) {
                String perm = var4.next();
                String time = permSection.getString(perm);
                if (timeIsFinish(time)) {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), this.t3SL4PERM.getConfig().getString("Yetki.Alma", "manuaddp %player% %perm%").replace("%player%", username).replace("%perm%", perm.replace("_", ".")));
                    permSection.set(perm, null);
                    this.t3SL4PERM.getData().save();
                }
            }
        }
    }

    private boolean timeIsFinish(String target) {
        try {
            Date date = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(target);
            return
                    (date.getTime() - (new Date()).getTime() <= 0L);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }
}
