package com.infumia.t3sl4.util.nbt.api;

import org.jetbrains.annotations.NotNull;

public interface NBTBase<T> {
   @NotNull
   T value();

   @NotNull
   String toString();
}
