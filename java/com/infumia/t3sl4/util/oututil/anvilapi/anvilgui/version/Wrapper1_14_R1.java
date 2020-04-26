package com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version;

import java.lang.reflect.Field;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.Container;
import net.minecraft.server.v1_14_R1.ContainerAccess;
import net.minecraft.server.v1_14_R1.ContainerAnvil;
import net.minecraft.server.v1_14_R1.Containers;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenWindow;
import com.infumia.t3sl4.util.oututil.anvilapi.version.special.AnvilContainer1_14_4_R1;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_14_R1.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Wrapper1_14_R1 implements VersionWrapper {
   public int getNextContainerId(Player player) {
      return this.toNMS(player).nextContainerCounter();
   }

   public void handleInventoryCloseEvent(Player player) {
      CraftEventFactory.handleInventoryCloseEvent(this.toNMS(player));
   }

   public void sendPacketOpenWindow(Player player, int containerId) {
      this.toNMS(player).playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, Containers.ANVIL, new ChatMessage("Repair & Name", new Object[0])));
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
      try {
         Field field = Container.class.getField("windowId");
         Field modifiersField = Field.class.getDeclaredField("modifiers");
         modifiersField.setAccessible(true);
         modifiersField.setInt(field, field.getModifiers() & -17);
         field.set(container, containerId);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public void addActiveContainerSlotListener(Object container, Player player) {
      ((Container)container).addSlotListener(this.toNMS(player));
   }

   public Inventory toBukkitInventory(Object container) {
      return ((Container)container).getBukkitView().getTopInventory();
   }

   public Object newContainerAnvil(Player player) {
      return Bukkit.getBukkitVersion().contains("1.14.4") ? new AnvilContainer1_14_4_R1(player, this.getNextContainerId(player)) : new Wrapper1_14_R1.AnvilContainer(player);
   }

   private EntityPlayer toNMS(Player player) {
      return ((CraftPlayer)player).getHandle();
   }

   private class AnvilContainer extends ContainerAnvil {
      public AnvilContainer(Player player) {
         super(Wrapper1_14_R1.this.getNextContainerId(player), ((CraftPlayer)player).getHandle().inventory, ContainerAccess.at(((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
         this.checkReachable = false;
         this.setTitle(new ChatMessage("Repair & Name", new Object[0]));
      }

      public void e() {
         super.e();
         this.levelCost.a();
      }
   }
}
