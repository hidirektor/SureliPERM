package com.infumia.t3sl4.sandikspawner.util;

import org.cactoos.Scalar;

public class Floored implements Scalar<Integer> {
   private final double number;

   public Floored(double number) {
      this.number = number;
   }

   public Integer value() {
      int var2 = (int)this.number;
      return this.number < (double)var2 ? var2 - 1 : var2;
   }
}
