package com.infumia.t3sl4.util.location;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public final class LocationOf implements Scalar<Location> {
   @NotNull
   private static final Pattern PATTERN = Pattern.compile("((?<world>[^:/]+)[:/])?(?<x>[\\-0-9.]+),(?<y>[\\-0-9.]+),(?<z>[\\-0-9.]+)(:(?<yaw>[\\-0-9.]+):(?<pitch>[\\-0-9.]+))?");
   @NotNull
   private final String text;

   public LocationOf(@NotNull String text) {
      this.text = text;
   }

   @NotNull
   public Location value() {
      Matcher match = PATTERN.matcher(this.text.replaceAll("_", "\\."));
      if (match.matches()) {
         return new Location(Bukkit.getWorld(match.group("world")), Double.parseDouble(match.group("x")), Double.parseDouble(match.group("y")), Double.parseDouble(match.group("z")), match.group("yaw") != null ? Float.parseFloat(match.group("yaw")) : 0.0F, match.group("pitch") != null ? Float.parseFloat(match.group("pitch")) : 0.0F);
      } else {
         throw new IllegalStateException("Location string has wrong style!");
      }
   }
}
