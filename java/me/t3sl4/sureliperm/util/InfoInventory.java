package me.t3sl4.sureliperm.util;

import java.util.HashMap;
import org.bukkit.Bukkit;

public class InfoInventory {
    public static void open(String player, String playerName, HashMap<String, String> map) {
        ListGUI listGui = new ListGUI(map, playerName);
        listGui.openInvChest(Bukkit.getPlayer(player));
    }
}
