package com.infumia.t3sl4.util.reflection.mck;

import com.infumia.t3sl4.util.reflection.RefConstructed;
import org.jetbrains.annotations.NotNull;

public class MckConstructed implements RefConstructed {
   @NotNull
   public Object create(@NotNull Object fallback, @NotNull Object... parameters) {
      return fallback;
   }
}
