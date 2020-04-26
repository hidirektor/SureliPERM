package com.infumia.t3sl4.sandikspawner.util;

import org.jetbrains.annotations.NotNull;

public class Axis {
   private final double minX;
   private final double minY;
   private final double minZ;
   private final double maxX;
   private final double maxY;
   private final double maxZ;

   public Axis(double var0, double var2, double var4, double var6, double var8, double var10) {
      this.minX = Math.min(var0, var6);
      this.minY = Math.min(var2, var8);
      this.minZ = Math.min(var4, var10);
      this.maxX = Math.max(var0, var6);
      this.maxY = Math.max(var2, var8);
      this.maxZ = Math.max(var4, var10);
   }

   public double getMinX() {
      return this.minX;
   }

   public double getMinY() {
      return this.minY;
   }

   public double getMinZ() {
      return this.minZ;
   }

   public double getMaxX() {
      return this.maxX;
   }

   public double getMaxY() {
      return this.maxY;
   }

   public double getMaxZ() {
      return this.maxZ;
   }

   public boolean collider(@NotNull Axis axis) {
      return this.minX < axis.maxX && this.maxX > axis.minX && this.minY < axis.maxY && this.maxY > axis.minY && this.minZ < axis.maxZ && this.maxZ > axis.minZ;
   }
}
