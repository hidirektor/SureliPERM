package com.infumia.t3sl4.util.nbt.api;

import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface NBTCompound<T> extends NBTBase<T> {
   void set(@NotNull String var1, @NotNull NBTBase var2);

   void setByte(@NotNull String var1, byte var2);

   void setShort(@NotNull String var1, short var2);

   void setInt(@NotNull String var1, int var2);

   void setLong(@NotNull String var1, long var2);

   void setFloat(@NotNull String var1, float var2);

   void setDouble(@NotNull String var1, double var2);

   void setString(@NotNull String var1, @NotNull String var2);

   void setByteArray(@NotNull String var1, @NotNull byte[] var2);

   void setIntArray(@NotNull String var1, @NotNull int[] var2);

   void setIntList(@NotNull String var1, @NotNull List<Integer> var2);

   void setLongArray(@NotNull String var1, @NotNull long[] var2);

   void setLongList(@NotNull String var1, @NotNull List<Long> var2);

   void setBoolean(@NotNull String var1, boolean var2);

   @NotNull
   NBTBase get(@NotNull String var1);

   byte getByte(@NotNull String var1);

   short getShort(@NotNull String var1);

   int getInt(@NotNull String var1);

   long getLong(@NotNull String var1);

   float getFloat(@NotNull String var1);

   double getDouble(@NotNull String var1);

   @NotNull
   String getString(@NotNull String var1);

   byte[] getByteArray(@NotNull String var1);

   int[] getIntArray(@NotNull String var1);

   long[] getLongArray(@NotNull String var1);

   @NotNull
   NBTCompound getNBTCompound(@NotNull String var1);

   @NotNull
   NBTList getList(@NotNull String var1, @NotNull NBTType var2);

   boolean getBoolean(@NotNull String var1);

   @NotNull
   Set<String> keys();

   boolean has(@NotNull String var1);

   void remove(@NotNull String var1);
}
