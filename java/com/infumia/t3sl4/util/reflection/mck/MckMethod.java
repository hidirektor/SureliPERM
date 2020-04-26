package com.infumia.t3sl4.util.reflection.mck;

import com.infumia.t3sl4.util.reflection.RefMethod;
import com.infumia.t3sl4.util.reflection.RefMethodExecuted;
import org.jetbrains.annotations.NotNull;

public class MckMethod implements RefMethod {
   @NotNull
   public com.infumia.t3sl4.util.reflection.RefMethodExecuted of(@NotNull Object object) {
      return new MckMethod.MckMethodExecuted();
   }

   @NotNull
   public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
      return fallback;
   }

   public static class MckMethodExecuted implements RefMethodExecuted {
      @NotNull
      public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
         return fallback;
      }
   }
}
