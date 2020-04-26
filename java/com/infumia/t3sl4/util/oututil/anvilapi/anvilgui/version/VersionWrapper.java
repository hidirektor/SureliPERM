package com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface VersionWrapper {
   int getNextContainerId(Player var1);

   void handleInventoryCloseEvent(Player var1);

   void sendPacketOpenWindow(Player var1, int var2);

   void sendPacketCloseWindow(Player var1, int var2);

   void setActiveContainerDefault(Player var1);

   void setActiveContainer(Player var1, Object var2);

   void setActiveContainerId(Object var1, int var2);

   void addActiveContainerSlotListener(Object var1, Player var2);

   Inventory toBukkitInventory(Object var1);

   Object newContainerAnvil(Player var1);
}
