package com.infumia.t3sl4.util.nbt.api.mck;

import com.infumia.t3sl4.util.nbt.api.NBTBase;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.api.NBTList;
import org.jetbrains.annotations.NotNull;

public class MckNBTList extends MckNBTBase implements com.infumia.t3sl4.util.nbt.api.NBTList<com.infumia.t3sl4.util.nbt.api.NBTBase, com.infumia.t3sl4.util.nbt.api.NBTBase> {
   public void add(@NotNull com.infumia.t3sl4.util.nbt.api.NBTBase value) {
   }

   public void add(int key, @NotNull com.infumia.t3sl4.util.nbt.api.NBTBase value) {
   }

   public void set(int key, @NotNull com.infumia.t3sl4.util.nbt.api.NBTBase value) {
   }

   public void remove(int key) {
   }

   @NotNull
   public NBTBase get(int key) {
      return new MckNBTBase();
   }

   @NotNull
   public NBTCompound getCompound(int key) {
      return new MckNBTCompound();
   }

   @NotNull
   public NBTList getList(int key) {
      return new MckNBTList();
   }

   public short getShort(int key) {
      return 0;
   }

   public int getInt(int key) {
      return 0;
   }

   @NotNull
   public int[] getIntArray(int key) {
      return new int[0];
   }

   public double getDouble(int key) {
      return 0.0D;
   }

   public float getFloat(int key) {
      return 0.0F;
   }

   @NotNull
   public String getString(int key) {
      return "";
   }

   public int size() {
      return 0;
   }
}
