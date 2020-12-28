package me.t3sl4.sureliperm.util;

import me.t3sl4.sureliperm.T3SL4Perm;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ListGUI implements Listener
{
    private static ArrayList<Inventory> chestPages;
    private static HashMap<String, String> map;
    private static String name;
    private static T3SL4Perm pl;

    public ListGUI(final T3SL4Perm pl) {
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)pl);
        ListGUI.pl = pl;
    }

    public ListGUI(final HashMap<String, String> map, final String guiName) {
        ListGUI.map = map;
        ListGUI.name = guiName;
    }

    public void openInvChest(final Player p) {
        this.setupPages(1, ListGUI.name);
        p.openInventory((Inventory)ListGUI.chestPages.get(0));
        System.err.println(ListGUI.chestPages.size() + " - " + ListGUI.map.size());
    }

    private void setupPages(final int num, final String guiName) {
        final Config conf = ListGUI.pl.getConfig();
        String start = "";
        if (num == 1) {
            start = guiName + " - Sayfa ";
        }
        final ItemStack nextPage = ItemBuilder.buildWithConfig(conf, "Menü.Sonraki", new HashMap<String, String>()).build();
        final ItemStack prevPage = ItemBuilder.buildWithConfig(conf, "Menü.Onceki", new HashMap<String, String>()).build();
        if (num == 1) {
            ListGUI.chestPages.clear();
        }
        int size = 0;
        if (num == 1) {
            size = ListGUI.map.size();
        }
        int i = 1;
        do {
            final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, ChatColor.translateAlternateColorCodes('&', start + i));
            for (int i2 = (i - 1) * 45; i2 < i * 45 && i2 < size; ++i2) {
                ItemStack item = null;
                if (num == 1) {
                    final String key = new ArrayList<String>(ListGUI.map.keySet()).get(i2);
                    final String value = new ArrayList<String>(ListGUI.map.values()).get(i2);
                    final HashMap<String, String> variables = new HashMap<String, String>();
                    variables.put("%perm%", key);
                    variables.put("%zaman%", value);
                    item = ItemBuilder.buildWithConfig(conf, "Menü.Perm", variables).build();
                }
                System.err.println(i2 - (i - 1) * 45 + " - " + i + " - " + item + " - " + item.getType().name());
                inv.setItem(i2 - (i - 1) * 45, item);
            }
            inv.setItem(48, prevPage);
            inv.setItem(50, nextPage);
            if (num == 1) {
                ListGUI.chestPages.add(inv);
            }
        } while (++i <= Math.ceil(size / 45));
    }

    @EventHandler
    public void invClick(final InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        if (e.getView().getTitle().startsWith(ListGUI.name + " - Sayfa ")) {
            final Config conf = ListGUI.pl.getConfig();
            final StringBuilder start = new StringBuilder();
            for (final char c : e.getView().getTitle().toCharArray()) {
                if (c == ' ') {
                    break;
                }
                start.append(c);
            }
            int num = 0;
            if (e.getView().getTitle().startsWith(ListGUI.name)) {
                num = 1;
            }
            final int sayfa = Integer.parseInt(e.getView().getTitle().replace(start.toString() + " - Sayfa ", "")) - 1;
            if (e.getSlot() == -999) {
                return;
            }
            final ItemStack item = inv.getItem(e.getSlot());
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', conf.getString("Menü.Onceki.name")))) {
                    e.setCancelled(true);
                    if (sayfa == 0) {
                        return;
                    }
                    int size = 0;
                    if (num == 1) {
                        size = ListGUI.chestPages.size() - 1;
                    }
                    if (sayfa == size && inv.firstEmpty() >= 45 && num == 1) {
                        ListGUI.chestPages.remove(inv);
                    }
                    if (num == 1) {
                        e.getWhoClicked().openInventory((Inventory)ListGUI.chestPages.get(sayfa - 1));
                    }
                }
                else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', conf.getString("Menü.Sonraki.name")))) {
                    e.setCancelled(true);
                    int size = 0;
                    if (num == 1) {
                        size = ListGUI.chestPages.size() - 1;
                    }
                    if (sayfa == size) {
                        this.newPage(num);
                    }
                    if (num == 1) {
                        e.getWhoClicked().openInventory((Inventory)ListGUI.chestPages.get(sayfa + 1));
                    }
                }
                else {
                    e.setCancelled(true);
                }
            }
        }
    }

    private void newPage(final int num) {
        String start = "";
        final Config conf = ListGUI.pl.getConfig();
        if (num == 1) {
            start = ListGUI.name + " - Sayfa ";
        }
        final ItemStack nextPage = ItemBuilder.buildWithConfig(conf, "Menü.Sonraki", new HashMap<String, String>()).build();
        final ItemStack prevPage = ItemBuilder.buildWithConfig(conf, "Menü.Onceki", new HashMap<String, String>()).build();
        int i = 0;
        if (num == 1) {
            i = ListGUI.chestPages.size();
        }
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, start + (i + 1));
        inv.setItem(48, prevPage);
        inv.setItem(50, nextPage);
        if (num == 1) {
            ListGUI.chestPages.add(inv);
        }
    }

    static {
        ListGUI.chestPages = new ArrayList<Inventory>();
        ListGUI.map = new HashMap<String, String>();
    }
}