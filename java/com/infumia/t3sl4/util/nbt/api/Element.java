package com.infumia.t3sl4.util.nbt.api;

import org.jetbrains.annotations.NotNull;

public interface Element<V, T> {
   @NotNull
   T nbt();

   @NotNull
   V apply(@NotNull T var1);

   @NotNull
   V element();
}
