package com.infumia.t3sl4.util.location;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public final class CenteredOf {
   @NotNull
   private final Location location;
   @NotNull
   private final World world;

   public CenteredOf(@NotNull Location location) {
      if (location.getWorld() == null) {
         throw new IllegalStateException("World of the location cannot be null!");
      } else {
         this.location = location;
         this.world = location.getWorld();
      }
   }

   @NotNull
   public Location on() {
      return new Location(this.world, (double)this.location.getBlockX() + 0.5D, (double)this.location.getBlockY() + 0.1D, (double)this.location.getBlockZ() + 0.5D, this.location.getYaw(), this.location.getPitch());
   }

   @NotNull
   public Location in() {
      return new Location(this.world, (double)this.location.getBlockX() + 0.5D, (double)this.location.getBlockY() + 0.5D, (double)this.location.getBlockZ() + 0.5D, this.location.getYaw(), this.location.getPitch());
   }
}
