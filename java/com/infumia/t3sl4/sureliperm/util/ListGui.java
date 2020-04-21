package com.infumia.t3sl4.sureliperm.util;

import com.infumia.t3sl4.sureliperm.SureliPERM;
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

public class ListGui implements Listener {
   private static ArrayList<Inventory> chestPages = new ArrayList();
   private static HashMap<String, String> map = new HashMap();
   private static String name;
   private static SureliPERM pl;

   public ListGui(SureliPERM pl) {
      Bukkit.getServer().getPluginManager().registerEvents(this, pl);
      ListGui.pl = pl;
   }

   public ListGui(HashMap<String, String> map, String guiName) {
      ListGui.map = map;
      name = guiName;
   }

   public void openInvChest(Player p) {
      this.setupPages(1, name);
      p.openInventory((Inventory)chestPages.get(0));
      System.err.println(chestPages.size() + " - " + map.size());
   }

   private void setupPages(int num, String guiName) {
      Config conf = pl.getConfig();
      String start = "";
      if (num == 1) {
         start = guiName + " - Sayfa ";
      }

      ItemStack nextPage = ItemBuilder.buildWithConfig(conf, "Menü.Sonraki", new HashMap()).build();
      ItemStack prevPage = ItemBuilder.buildWithConfig(conf, "Menü.Onceki", new HashMap()).build();
      if (num == 1) {
         chestPages.clear();
      }

      int size = 0;
      if (num == 1) {
         size = map.size();
      }

      int i = 1;

      do {
         Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, ChatColor.translateAlternateColorCodes('&', start + i));

         for(int i2 = (i - 1) * 45; i2 < i * 45 && i2 < size; ++i2) {
            ItemStack item = null;
            if (num == 1) {
               String key = (String)(new ArrayList(map.keySet())).get(i2);
               String value = (String)(new ArrayList(map.values())).get(i2);
               HashMap<String, String> variables = new HashMap();
               variables.put("%perm%", key);
               variables.put("%zaman%", value);
               item = ItemBuilder.buildWithConfig(conf, "Menü.Perm", variables).build();
            }
            System.err.println(i2 - ((i-1)*45) + " - " + i + " - " + item + " - "+item.getType().name());
            inv.setItem(i2 - (i - 1) * 45, item);
         }

         inv.setItem(48, prevPage);
         inv.setItem(50, nextPage);
         if (num == 1) {
            chestPages.add(inv);
         }

         ++i;
      } while((double)i <= Math.ceil((double)(size / 45)));

   }

   @EventHandler
   public void invClick(InventoryClickEvent e) {
      Inventory inv = e.getClickedInventory();
      if (e.getView().getTitle().startsWith(name + " - Sayfa ")) {
         Config conf = pl.getConfig();
         StringBuilder start = new StringBuilder();
         char[] var5 = e.getView().getTitle().toCharArray();
         int sayfa = var5.length;

         for(int var7 = 0; var7 < sayfa; ++var7) {
            char c = var5[var7];
            if (c == ' ') {
               break;
            }

            start.append(c);
         }

         int num = 0;
         if (e.getView().getTitle().startsWith(name)) {
            num = 1;
         }

         sayfa = Integer.parseInt(e.getView().getTitle().replace(start.toString() + " - Sayfa ", "")) - 1;
         if (e.getSlot() == -999) {
            return;
         }

         ItemStack item = inv.getItem(e.getSlot());
         if (item != null && item.getType() != Material.AIR) {
            int size;
            if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', conf.getString("Menü.Onceki.name")))) {
               e.setCancelled(true);
               if (sayfa == 0) {
                  return;
               }

               size = 0;
               if (num == 1) {
                  size = chestPages.size() - 1;
               }

               if (sayfa == size && inv.firstEmpty() >= 45 && num == 1) {
                  chestPages.remove(inv);
               }

               if (num == 1) {
                  e.getWhoClicked().openInventory((Inventory)chestPages.get(sayfa - 1));
               }
            } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', conf.getString("Menü.Sonraki.name")))) {
               e.setCancelled(true);
               size = 0;
               if (num == 1) {
                  size = chestPages.size() - 1;
               }

               if (sayfa == size) {
                  this.newPage(num);
               }

               if (num == 1) {
                  e.getWhoClicked().openInventory((Inventory)chestPages.get(sayfa + 1));
               }
            } else {
               e.setCancelled(true);
            }
         }
      }

   }

   private void newPage(int num) {
      String start = "";
      Config conf = pl.getConfig();
      if (num == 1) {
         start = name + " - Sayfa ";
      }

      ItemStack nextPage = ItemBuilder.buildWithConfig(conf, "Menü.Sonraki", new HashMap()).build();
      ItemStack prevPage = ItemBuilder.buildWithConfig(conf, "Menü.Onceki", new HashMap()).build();
      int i = 0;
      if (num == 1) {
         i = chestPages.size();
      }

      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, start + (i + 1));
      inv.setItem(48, prevPage);
      inv.setItem(50, nextPage);
      if (num == 1) {
         chestPages.add(inv);
      }

   }
}
