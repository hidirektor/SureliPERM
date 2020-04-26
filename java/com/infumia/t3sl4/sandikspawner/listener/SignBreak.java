package com.infumia.t3sl4.sandikspawner.listener;

import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.user.User;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class SignBreak implements Listener {
   @NotNull
   private final SpawnerAPI spawnerAPI;

   public SignBreak(@NotNull SpawnerAPI spawnerAPI) {
      this.spawnerAPI = spawnerAPI;
   }

   @EventHandler
   public void chestBreak(BlockBreakEvent event) {
      Block block = event.getBlock();
      BlockState blockState = block.getState();
      if (blockState instanceof Sign) {
         Sign sign = (Sign)blockState;
         ChestPlaced placed = this.spawnerAPI.findChestBySignLocation(sign.getLocation());
         if (placed != null) {
            User user = this.spawnerAPI.findUserByUUID(placed.owner);
            event.setCancelled(true);
            if (placed.owner.equals(event.getPlayer().getUniqueId()) || user.yetkililer.containsKey(event.getPlayer().getUniqueId()) || event.getPlayer().isOp()) {
               if (event.getPlayer().getInventory().firstEmpty() == -1) {
                  event.getPlayer().sendMessage(this.spawnerAPI.getLanguage().errorYourInventoryIsFull);
               } else {
                  event.getPlayer().getInventory().addItem(new ItemStack[]{placed.chestType.getChestItem(placed.level)});
                  this.spawnerAPI.remove(placed);
                  event.getPlayer().sendMessage(this.spawnerAPI.getLanguage().generalYouBreakeAChestSpawner(placed.chestType.getId()));
               }
            }
         }
      }
   }
}
