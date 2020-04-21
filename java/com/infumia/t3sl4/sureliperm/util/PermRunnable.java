package com.infumia.t3sl4.sureliperm.util;

import com.infumia.t3sl4.sureliperm.SureliPERM;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class PermRunnable extends BukkitRunnable {
   private SureliPERM sureliPERM;

   public PermRunnable(SureliPERM sureliPERM) {
      this.sureliPERM = sureliPERM;
   }

   public void run() {
      Iterator var1 = this.sureliPERM.getData().getConfigurationSection("").getKeys(false).iterator();

      while(var1.hasNext()) {
         String username = (String)var1.next();
         ConfigurationSection permSection = this.sureliPERM.getData().getConfigurationSection(username);
         Iterator var4 = permSection.getKeys(false).iterator();

         while(var4.hasNext()) {
            String perm = (String)var4.next();
            String time = permSection.getString(perm);
            if (this.timeIsFinish(time)) {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.sureliPERM.getConfig().getString("Yetki.Alma", "manuaddp %player% %perm%").replace("%player%", username).replace("%perm%", perm.replace("_", ".")));
               permSection.set(perm, (Object)null);
               this.sureliPERM.getData().save();
            }
         }
      }

   }

   private boolean timeIsFinish(String target) {
      try {
         Date date = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(target);
         return date.getTime() - (new Date()).getTime() <= 0L;
      } catch (ParseException var3) {
         var3.printStackTrace();
         return false;
      }
   }
}
