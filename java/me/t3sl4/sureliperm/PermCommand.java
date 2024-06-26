package me.t3sl4.sureliperm;

import me.t3sl4.sureliperm.util.InfoInventory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor
{
    private final T3SL4Perm plugin;

    public PermCommand(final T3SL4Perm plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439")).create()));
            if (sender instanceof Player) {
                Player sndr = (Player) sender;
                this.sendMessage(sender, this.plugin.getConfig().getStringList("Komutlar." + (sender.isOp() ? "Admin" : "Oyuncu")));
                sndr.spigot().sendMessage((BaseComponent)msg);
            }
            else {
                this.sendMessage(sender, this.plugin.getConfig().getStringList("Komutlar." + (sender.isOp() ? "Admin" : "Oyuncu")));
            }
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.isOp()) {
                return true;
            }
            this.plugin.getConfig().reload();
            this.plugin.getData().reload();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Yenilendi", "&6&lSureliPERM > &aEklenti yenilendi!"));
        }
        else if (args[0].equalsIgnoreCase("sorgu")) {
            if (!(sender instanceof Player)) {
                return true;
            }
            InfoInventory.open(sender.getName(), (args.length == 1) ? sender.getName() : args[1], this.getPerm((args.length == 1) ? sender.getName() : args[1]));
        }
        else if (args[0].equalsIgnoreCase("sil") && args.length == 3) {
            if (!sender.isOp()) {
                return true;
            }
            this.plugin.getData().set(args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_"), (Object)null);
            this.plugin.getData().save();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Silindi", "&6&lTimelyPerms > &aBa\u015far\u0131yla %player% adl\u0131 oyuncunun %perm% permi silindi!").replace("%player%", args[1]).replace("%perm%", args[2]));
        }
        else if (args[0].equalsIgnoreCase("zsil") && args.length == 4) {
            if (!sender.isOp()) {
                return true;
            }
            final String val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            String settedValue = this.plugin.getData().getString(val, "def");
            settedValue = this.getNewDate(this.getDate(settedValue), args[3], false);
            this.plugin.getData().set(val, (Object)settedValue);
            this.plugin.getData().save();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Zaman-Silindi", "&6&lTimelyPerms > &aBa\u015far\u0131yla %player% adl\u0131 oyuncunun %perm% perminden %zaman% s\u00fcre silindi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
        }
        else if (args[0].equalsIgnoreCase("zekle") && args.length == 4) {
            if (!sender.isOp()) {
                return true;
            }
            final String val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            String settedValue = this.plugin.getData().getString(val, "def");
            settedValue = this.getNewDate(this.getDate(settedValue), args[3], true);
            this.plugin.getData().set(val, (Object)settedValue);
            this.plugin.getData().save();
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Zaman-Eklendi", "&6&lTimelyPerms > &aBa\u015far\u0131yla %player% adl\u0131 oyuncunun %perm% perminden %zaman% s\u00fcre eklendi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
        }
        else if (args[0].equalsIgnoreCase("ver") && args.length == 4) {
            if (!sender.isOp()) {
                return true;
            }
            final String val = args[1].toLowerCase(Locale.US) + "." + args[2].replace(".", "_");
            this.plugin.getData().set(val, (Object)this.getNewDate(this.getDate("def"), args[3], true));
            this.plugin.getData().save();
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), this.plugin.getConfig().getString("Yetki.Verme", "manuaddp %player% %perm%").replace("%player%", args[1]).replace("%perm%", args[2]));
            this.sendMessage(sender, this.plugin.getConfig().getString("Mesajlar.Verildi", "&6&lTimelyPerms > &aBa\u015far\u0131yla %player% adl\u0131 oyuncunun %perm% perminden %zaman% s\u00fcre eklendi!").replace("%player%", args[1]).replace("%perm%", args[2]).replace("%zaman%", args[3]).replace("%sure%", this.asText(args[3])));
        }
        else {
            this.sendMessage(sender, this.plugin.getConfig().getStringList("Komutlar." + (sender.isOp() ? "Admin" : "Oyuncu")));
        }
        return false;
    }

    private void sendMessage(final CommandSender sender, final List<String> messages) {
        messages.forEach(m -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m)));
    }

    private void sendMessage(final CommandSender sender, final String m) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
    }

    private String asText(String format) {
        format = format.replace("d", " dakika").replace("h", " hafta").replace("s", " saat").replace("g", " g\u00fcn").replace("a", " ay");
        return format;
    }

    private Date getDate(final String val) {
        if (val.equalsIgnoreCase("def")) {
            return new Date();
        }
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(val);
        }
        catch (Exception var3) {
            return new Date();
        }
    }

    private String getNewDate(final Date date, String format, final boolean b) {
        format = format.toLowerCase();
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final String[] splited = format.split("(d|h|s|g|a)");
        int charSize = 0;
        for (int i = 0; i < splited.length; ++i) {
            charSize += splited[i].length() + ((i != 0) ? 1 : 0);
            if (format.charAt(charSize) == 'd') {
                cal.add(12, this.parseInt((b ? '+' : '-') + splited[i]));
            }
            else if (format.charAt(charSize) == 'h') {
                cal.add(4, this.parseInt((b ? '+' : '-') + splited[i]));
            }
            else if (format.charAt(charSize) == 's') {
                cal.add(10, this.parseInt((b ? '+' : '-') + splited[i]));
            }
            else if (format.charAt(charSize) == 'g') {
                cal.add(5, this.parseInt((b ? '+' : '-') + splited[i]));
            }
            else if (format.charAt(charSize) == 'a') {
                cal.add(2, this.parseInt((b ? '+' : '-') + splited[i]));
            }
        }
        return df.format(cal.getTime());
    }

    private int parseInt(final String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException var3) {
            return 0;
        }
    }

    private HashMap<String, String> getPerm(String userName) {
        final HashMap<String, String> map = new HashMap<String, String>();
        userName = userName.toLowerCase(Locale.US);
        if (this.plugin.getData().isSet(userName) && this.plugin.getData().isConfigurationSection(userName)) {
            final ConfigurationSection permSection = this.plugin.getData().getConfigurationSection(userName);
            if (permSection != null) {
                for (final String perm : permSection.getKeys(false)) {
                    final String time = permSection.getString(perm);
                    map.put(perm, time);
                }
            }
        }
        return map;
    }
}