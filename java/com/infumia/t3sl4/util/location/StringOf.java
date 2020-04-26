package com.infumia.t3sl4.util.location;

import java.util.Locale;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public final class StringOf {
   @NotNull
   private final Location location;
   @NotNull
   private final World world;

   public StringOf(@NotNull Location location) {
      if (location.getWorld() == null) {
         throw new IllegalStateException("World of the location cannot be null!");
      } else {
         this.location = location;
         this.world = location.getWorld();
      }
   }

   @NotNull
   public String asString() {
      String s = this.world.getName() + ":";
      s = s + String.format(Locale.ENGLISH, "%.2f,%.2f,%.2f", this.location.getX(), this.location.getY(), this.location.getZ());
      if (this.location.getYaw() != 0.0F || this.location.getPitch() != 0.0F) {
         s = s + String.format(Locale.ENGLISH, ":%.2f:%.2f", this.location.getYaw(), this.location.getPitch());
      }

      return s;
   }

   @NotNull
   public String asKey() {
      return this.asString().replaceAll(":", "/").replaceAll("\\.", "_");
   }
}
