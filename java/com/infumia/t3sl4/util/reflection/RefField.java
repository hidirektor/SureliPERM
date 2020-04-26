package com.infumia.t3sl4.util.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefField {
   @NotNull
   RefFieldExecuted of(@NotNull Object var1);

   void set(@NotNull Object var1);

   @NotNull
   Object get(@NotNull Object var1);
}
