package com.infumia.t3sl4.sandikspawner.util;

import org.cactoos.Scalar;
import org.cactoos.scalar.MinOf;

public class Clamped implements Scalar<Integer> {
   private final int var0;
   private final int var1;
   private final int var2;

   public Clamped(int var0, int var1, int var2) {
      this.var0 = var0;
      this.var1 = var1;
      this.var2 = var2;
   }

   public Integer value() {
      return this.var0 < this.var1 ? this.var1 : (new MinOf(new Integer[]{this.var0, this.var2})).intValue();
   }
}
