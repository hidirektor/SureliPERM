package com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version;

import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.Blocks;
import net.minecraft.server.v1_9_R1.ChatMessage;
import net.minecraft.server.v1_9_R1.Container;
import net.minecraft.server.v1_9_R1.ContainerAnvil;
import net.minecraft.server.v1_9_R1.EntityHuman;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_9_R1.PacketPlayOutOpenWindow;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R1.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Wrapper1_9_R1 implements VersionWrapper {
   public int getNextContainerId(Player player) {
      return this.toNMS(player).nextContainerCounter();
   }

   public void handleInventoryCloseEvent(Player player) {
      CraftEventFactory.handleInventoryCloseEvent(this.toNMS(player));
   }

   public void sendPacketOpenWindow(Player player, int containerId) {
      this.toNMS(player).playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", new ChatMessage(Blocks.ANVIL.a() + ".name", new Object[0])));
   }

   public void sendPacketCloseWindow(Player player, int containerId) {
      this.toNMS(player).playerConnection.sendPacket(new PacketPlayOutCloseWindow(containerId));
   }

   public void setActiveContainerDefault(Player player) {
      this.toNMS(player).activeContainer = this.toNMS(player).defaultContainer;
   }

   public void setActiveContainer(Player player, Object container) {
      this.toNMS(player).activeContainer = (Container)container;
   }

   public void setActiveContainerId(Object container, int containerId) {
      ((Container)container).windowId = containerId;
   }

   public void addActiveContainerSlotListener(Object container, Player player) {
      ((Container)container).addSlotListener(this.toNMS(player));
   }

   public Inventory toBukkitInventory(Object container) {
      return ((Container)container).getBukkitView().getTopInventory();
   }

   public Object newContainerAnvil(Player player) {
      return new Wrapper1_9_R1.AnvilContainer(this.toNMS(player));
   }

   private EntityPlayer toNMS(Player player) {
      return ((CraftPlayer)player).getHandle();
   }

   private class AnvilContainer extends ContainerAnvil {
      public AnvilContainer(EntityHuman entityhuman) {
         super(entityhuman.inventory, entityhuman.world, new BlockPosition(0, 0, 0), entityhuman);
      }

      public boolean a(EntityHuman entityhuman) {
         return true;
      }
   }
}
