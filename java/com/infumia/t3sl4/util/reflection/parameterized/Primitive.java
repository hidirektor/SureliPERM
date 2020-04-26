package com.infumia.t3sl4.util.reflection.parameterized;

import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public class Primitive implements Scalar<Class> {
   @NotNull
   private final Class clazz;

   public Primitive(@NotNull Class clazz) {
      this.clazz = clazz;
   }

   @NotNull
   public Class value() {
      String var1 = this.clazz.getName();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -2056817302:
         if (var1.equals("java.lang.Integer")) {
            var2 = 0;
         }
         break;
      case -527879800:
         if (var1.equals("java.lang.Float")) {
            var2 = 1;
         }
         break;
      case -515992664:
         if (var1.equals("java.lang.Short")) {
            var2 = 2;
         }
         break;
      case 155276373:
         if (var1.equals("java.lang.Character")) {
            var2 = 3;
         }
         break;
      case 344809556:
         if (var1.equals("java.lang.Boolean")) {
            var2 = 4;
         }
         break;
      case 398507100:
         if (var1.equals("java.lang.Byte")) {
            var2 = 5;
         }
         break;
      case 398795216:
         if (var1.equals("java.lang.Long")) {
            var2 = 6;
         }
         break;
      case 399092968:
         if (var1.equals("java.lang.Void")) {
            var2 = 7;
         }
         break;
      case 761287205:
         if (var1.equals("java.lang.Double")) {
            var2 = 8;
         }
      }

      switch(var2) {
      case 0:
         return Integer.TYPE;
      case 1:
         return Float.TYPE;
      case 2:
         return Short.TYPE;
      case 3:
         return Character.TYPE;
      case 4:
         return Boolean.TYPE;
      case 5:
         return Byte.TYPE;
      case 6:
         return Long.TYPE;
      case 7:
         return Void.TYPE;
      case 8:
         return Double.TYPE;
      default:
         return this.clazz;
      }
   }
}
