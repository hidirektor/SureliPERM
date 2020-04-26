package com.infumia.t3sl4.util.reflection.mck;

import com.infumia.t3sl4.util.reflection.RefFieldExecuted;
import com.infumia.t3sl4.util.reflection.RefField;
import org.jetbrains.annotations.NotNull;

public class MckField implements RefField {
   public void set(@NotNull Object value) {
   }

   @NotNull
   public Object get(@NotNull Object fallback) {
      return fallback;
   }

   @NotNull
   public com.infumia.t3sl4.util.reflection.RefFieldExecuted of(@NotNull Object object) {
      return new RefFieldExecuted() {
         public void set(@NotNull Object value) {
         }

         @NotNull
         public Object get(@NotNull Object fallback) {
            return fallback;
         }
      };
   }
}
