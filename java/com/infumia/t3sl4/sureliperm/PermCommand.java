package com.infumia.t3sl4.sureliperm;

import com.infumia.t3sl4.sureliperm.util.InfoInventory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor {
   private final SureliPERM plugin;

   public PermCommand(SureliPERM plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (args.length == 0) {
         this.sendMessage(sender, this.plugin.getConfig().getStringList("Komutlar." + (sender.isOp() ? "Admin" : "Oyuncu")));
      } else if (args[0].equalsIgnoreCase("reload")) {
         if (!sender.isOp()) {
            return true;
         }

         this.plugin.getConfig().reload();
         this.plugin.getData().reload();
         this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Yenilendi", "&6&lTimelyPerms > &aEklenti yenilendi!"));
      } else if (args[0].equalsIgnoreCase("sorgu")) {
         if (!(sender instanceof Player)) {
            return true;
         }

         InfoInventory.open(sender.getName(), args.length == 1 ? sender.getName() : args[1], this.getPerm(args.length == 1 ? sender.getName() : args[1]));
      } else if (args[0].equalsIgnoreCase("sil") && args.length == 3) {
         if (!sender.isOp()) {
            return true;
         }

         this.plugin.getData().set(args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_"), (Object)null);
         this.plugin.getData().save();
         this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Silindi", "&6&lTimelyPerms > &aBaşarıyla %player% adlı oyuncunun %perm% permi silindi!").replace("%player%", args[1]).replace("%perm%", args[2]));
      } else {
         String val;
         String settedValue;
         if (args[0].equalsIgnoreCase("zsil") && args.length == 4) {
            if (!sender.isOp()) {
               return true;
            }

            val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            settedValue = this.plugin.getData().getString(val, "def");
            settedValue = this.getNewDate(this.getDate(settedValue), args[3], false);
            this.plugin.getData().set(val, settedValue);
            this.plugin.getData().save();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Zaman-Silindi", "&6&lTimelyPerms > &aBaşarıyla %player% adlı oyuncunun %perm% perminden %zaman% süre silindi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
         } else if (args[0].equalsIgnoreCase("zekle") && args.length == 4) {
            if (!sender.isOp()) {
               return true;
            }

            val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            settedValue = this.plugin.getData().getString(val, "def");
            settedValue = this.getNewDate(this.getDate(settedValue), args[3], true);
            this.plugin.getData().set(val, settedValue);
            this.plugin.getData().save();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Zaman-Eklendi", "&6&lTimelyPerms > &aBaşarıyla %player% adlı oyuncunun %perm% perminden %zaman% süre eklendi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
         } else if (args[0].equalsIgnoreCase("ver") && args.length == 4) {
            if (!sender.isOp()) {
               return true;
            }

            val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            this.plugin.getData().set(val, this.getNewDate(this.getDate("def"), args[3], true));
            this.plugin.getData().save();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.plugin.getConfig().getString("Yetki.Verme", "manuaddp %player% %perm%").replace("%player%", args[1]).replace("%perm%", args[2]));
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Verildi", "&6&lTimelyPerms > &aBaşarıyla %player% adlı oyuncunun %perm% perminden %zaman% süre eklendi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
         } else {
            this.sendMessage(sender, this.plugin.getConfig().getStringList("Komutlar." + (sender.isOp() ? "Admin" : "Oyuncu")));
         }
      }

      return false;
   }

   private void sendMessage(CommandSender sender, List<String> messages) {
      messages.forEach((m) -> {
         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
      });
   }

   private void sendMessage(CommandSender sender, String m) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
   }

   private String asText(String format) {
      format = format.replace("d", " dakika").replace("h", " hafta").replace("s", " saat").replace("g", " gün").replace("a", " ay");
      return format;
   }

   private Date getDate(String val) {
      if (val.equalsIgnoreCase("def")) {
         return new Date();
      } else {
         try {
            return (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(val);
         } catch (Exception var3) {
            return new Date();
         }
      }
   }

   private String getNewDate(Date date, String format, boolean b) {
      format = format.toLowerCase();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      String[] splited = format.split("(d|h|s|g|a)");
      int charSize = 0;

      for(int i = 0; i < splited.length; ++i) {
         charSize += splited[i].length() + (i != 0 ? 1 : 0);
         if (format.charAt(charSize) == 'd') {
            cal.add(12, this.parseInt((b ? '+' : '-') + splited[i]));
         } else if (format.charAt(charSize) == 'h') {
            cal.add(4, this.parseInt((b ? '+' : '-') + splited[i]));
         } else if (format.charAt(charSize) == 's') {
            cal.add(10, this.parseInt((b ? '+' : '-') + splited[i]));
         } else if (format.charAt(charSize) == 'g') {
            cal.add(5, this.parseInt((b ? '+' : '-') + splited[i]));
         } else if (format.charAt(charSize) == 'a') {
            cal.add(2, this.parseInt((b ? '+' : '-') + splited[i]));
         }
      }

      return df.format(cal.getTime());
   }

   private int parseInt(String str) {
      try {
         return Integer.parseInt(str);
      } catch (NumberFormatException var3) {
         return 0;
      }
   }

   private HashMap<String, String> getPerm(String userName) {
      HashMap<String, String> map = new HashMap();
      userName = userName.toLowerCase(Locale.US);
      if (this.plugin.getData().isSet(userName) && this.plugin.getData().isConfigurationSection(userName)) {
         ConfigurationSection permSection = this.plugin.getData().getConfigurationSection(userName);
         if (permSection != null) {
            Iterator var4 = permSection.getKeys(false).iterator();

            while(var4.hasNext()) {
               String perm = (String)var4.next();
               String time = permSection.getString(perm);
               map.put(perm, time);
            }
         }
      }

      return map;
   }
}
