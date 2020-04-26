package com.infumia.t3sl4.util.nbt.api;

import org.jetbrains.annotations.NotNull;

public interface NBTList<V, T> extends NBTBase<T> {
   void add(@NotNull V var1);

   void add(int var1, @NotNull V var2);

   void set(int var1, @NotNull V var2);

   void remove(int var1);

   @NotNull
   V get(int var1);

   @NotNull
   NBTCompound getCompound(int var1);

   @NotNull
   NBTList getList(int var1);

   short getShort(int var1);

   int getInt(int var1);

   @NotNull
   int[] getIntArray(int var1);

   double getDouble(int var1);

   float getFloat(int var1);

   @NotNull
   String getString(int var1);

   int size();
}
