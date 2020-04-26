package com.infumia.t3sl4.sandikspawner.util;

import org.bukkit.Location;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

class BoundingBox {
   private double minX;
   private double minY;
   private double minZ;
   private double maxX;
   private double maxY;
   private double maxZ;

   private BoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
      this.resize(x1, y1, z1, x2, y2, z2);
   }

   private void resize(double x1, double y1, double z1, double x2, double y2, double z2) {
      NumberConversions.checkFinite(x1, "x1 not finite");
      NumberConversions.checkFinite(y1, "y1 not finite");
      NumberConversions.checkFinite(z1, "z1 not finite");
      NumberConversions.checkFinite(x2, "x2 not finite");
      NumberConversions.checkFinite(y2, "y2 not finite");
      NumberConversions.checkFinite(z2, "z2 not finite");
      this.minX = Math.min(x1, x2);
      this.minY = Math.min(y1, y2);
      this.minZ = Math.min(z1, z2);
      this.maxX = Math.max(x1, x2);
      this.maxY = Math.max(y1, y2);
      this.maxZ = Math.max(z1, z2);
   }

   double getMinX() {
      return this.minX;
   }

   double getMinY() {
      return this.minY;
   }

   double getMinZ() {
      return this.minZ;
   }

   double getMaxX() {
      return this.maxX;
   }

   double getMaxY() {
      return this.maxY;
   }

   double getMaxZ() {
      return this.maxZ;
   }

   @NotNull
   static BoundingBox of(@NotNull Location center, double x, double y, double z) {
      return new BoundingBox(center.getX() - x, center.getY() - y, center.getZ() - z, center.getX() + x, center.getY() + y, center.getZ() + z);
   }
}
