package com.infumia.t3sl4.sureliperm.util;

import java.util.HashMap;
import org.bukkit.Bukkit;

public class InfoInventory {
   public static void open(String player, String playerName, HashMap<String, String> map) {
      ListGui listGui = new ListGui(map, playerName);
      listGui.openInvChest(Bukkit.getPlayer(player));
   }
}
