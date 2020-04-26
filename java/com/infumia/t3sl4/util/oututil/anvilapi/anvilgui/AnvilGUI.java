package com.infumia.t3sl4.util.oututil.anvilapi.anvilgui;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version.VersionMatcher;
import com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version.VersionWrapper;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AnvilGUI {
   private static VersionWrapper WRAPPER = (new VersionMatcher()).match();
   private final Plugin plugin;
   private final Player player;
   private String text;
   private final boolean preventClose;
   private final Consumer<Player> closeListener;
   private final BiFunction<Player, String, AnvilGUI.Response> completeFunction;
   private ItemStack insert;
   private int containerId;
   private Inventory inventory;
   private final AnvilGUI.ListenUp listener;
   private boolean open;

   /** @deprecated */
   @Deprecated
   public AnvilGUI(Plugin plugin, Player holder, String insert, BiFunction<Player, String, String> biFunction) {
      this(plugin, holder, insert, false, (Consumer)null, (player, text) -> {
         String response = (String)biFunction.apply(player, text);
         return response != null ? AnvilGUI.Response.text(response) : AnvilGUI.Response.close();
      });
   }

   private AnvilGUI(Plugin plugin, Player player, String text, boolean preventClose, Consumer<Player> closeListener, BiFunction<Player, String, AnvilGUI.Response> completeFunction) {
      this.text = "";
      this.listener = new AnvilGUI.ListenUp();
      this.plugin = plugin;
      this.player = player;
      this.text = text;
      this.preventClose = preventClose;
      this.closeListener = closeListener;
      this.completeFunction = completeFunction;
      this.openInventory();
   }

   private void openInventory() {
      ItemStack paper = new ItemStack(Material.PAPER);
      ItemMeta paperMeta = paper.getItemMeta();
      paperMeta.setDisplayName(this.text);
      paper.setItemMeta(paperMeta);
      this.insert = paper;
      WRAPPER.handleInventoryCloseEvent(this.player);
      WRAPPER.setActiveContainerDefault(this.player);
      Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
      Object container = WRAPPER.newContainerAnvil(this.player);
      this.inventory = WRAPPER.toBukkitInventory(container);
      this.inventory.setItem(0, this.insert);
      this.containerId = WRAPPER.getNextContainerId(this.player);
      WRAPPER.sendPacketOpenWindow(this.player, this.containerId);
      WRAPPER.setActiveContainer(this.player, container);
      WRAPPER.setActiveContainerId(container, this.containerId);
      WRAPPER.addActiveContainerSlotListener(container, this.player);
      this.open = true;
   }

   public void closeInventory() {
      Validate.isTrue(this.open, "You can't close an inventory that isn't open!");
      this.open = false;
      WRAPPER.handleInventoryCloseEvent(this.player);
      WRAPPER.setActiveContainerDefault(this.player);
      WRAPPER.sendPacketCloseWindow(this.player, this.containerId);
      HandlerList.unregisterAll(this.listener);
      if (this.closeListener != null) {
         this.closeListener.accept(this.player);
      }

   }

   public Inventory getInventory() {
      return this.inventory;
   }

   // $FF: synthetic method
   AnvilGUI(Plugin x0, Player x1, String x2, boolean x3, Consumer x4, BiFunction x5, Object x6) {
      this(x0, x1, x2, x3, x4, x5);
   }

   public static class Slot {
      public static final int INPUT_LEFT = 0;
      public static final int INPUT_RIGHT = 1;
      public static final int OUTPUT = 2;
   }

   public static class Response {
      private final String text;

      private Response(String text) {
         this.text = text;
      }

      public String getText() {
         return this.text;
      }

      public static AnvilGUI.Response close() {
         return new AnvilGUI.Response((String)null);
      }

      public static AnvilGUI.Response text(String text) {
         return new AnvilGUI.Response(text);
      }
   }

   public static class Builder {
      private Consumer<Player> closeListener;
      private boolean preventClose = false;
      private BiFunction<Player, String, AnvilGUI.Response> completeFunction;
      private Plugin plugin;
      private String text = "";

      public AnvilGUI.Builder preventClose() {
         this.preventClose = true;
         return this;
      }

      public AnvilGUI.Builder onClose(Consumer<Player> closeListener) {
         Validate.notNull(closeListener, "closeListener cannot be null");
         this.closeListener = closeListener;
         return this;
      }

      public AnvilGUI.Builder onComplete(BiFunction<Player, String, AnvilGUI.Response> completeFunction) {
         Validate.notNull(completeFunction, "Complete function cannot be null");
         this.completeFunction = completeFunction;
         return this;
      }

      public AnvilGUI.Builder plugin(Plugin plugin) {
         Validate.notNull(plugin, "Plugin cannot be null");
         this.plugin = plugin;
         return this;
      }

      public AnvilGUI.Builder text(String text) {
         Validate.notNull(text, "Text cannot be null");
         this.text = text;
         return this;
      }

      public AnvilGUI open(Player player) {
         Validate.notNull(this.plugin, "Plugin cannot be null");
         Validate.notNull(this.completeFunction, "Complete function cannot be null");
         Validate.notNull(player, "Player cannot be null");
         return new AnvilGUI(this.plugin, player, this.text, this.preventClose, this.closeListener, this.completeFunction);
      }
   }

   private class ListenUp implements Listener {
      private ListenUp() {
      }

      @EventHandler
      public void onInventoryClick(InventoryClickEvent event) {
         if (event.getInventory().equals(AnvilGUI.this.inventory) && event.getRawSlot() < 3) {
            event.setCancelled(true);
            Player clicker = (Player)event.getWhoClicked();
            if (event.getRawSlot() == 2) {
               ItemStack clicked = AnvilGUI.this.inventory.getItem(2);
               if (clicked == null || clicked.getType() == Material.AIR) {
                  return;
               }

               AnvilGUI.Response response = (AnvilGUI.Response)AnvilGUI.this.completeFunction.apply(clicker, clicked.hasItemMeta() ? clicked.getItemMeta().getDisplayName() : "");
               if (response.getText() != null) {
                  ItemMeta meta = clicked.getItemMeta();
                  meta.setDisplayName(response.getText());
                  clicked.setItemMeta(meta);
                  AnvilGUI.this.inventory.setItem(0, clicked);
               } else {
                  AnvilGUI.this.closeInventory();
               }
            }
         }

      }

      @EventHandler
      public void onInventoryClose(InventoryCloseEvent event) {
         if (AnvilGUI.this.open && event.getInventory().equals(AnvilGUI.this.inventory)) {
            AnvilGUI.this.closeInventory();
            if (AnvilGUI.this.preventClose) {
               Bukkit.getScheduler().runTask(AnvilGUI.this.plugin, () -> {
                  AnvilGUI.this.openInventory();
               });
            }
         }

      }

      // $FF: synthetic method
      ListenUp(Object x1) {
         this();
      }
   }
}
