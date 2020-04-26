package com.infumia.t3sl4.util.itemstack;

import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public abstract class ScalarRuntime<T> implements Scalar<T> {
   @NotNull
   private final Scalar<T> scalar;

   public ScalarRuntime(@NotNull Scalar<T> scalar) {
      this.scalar = scalar;
   }

   public T value() {
      try {
         return this.scalar.value();
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }
}
