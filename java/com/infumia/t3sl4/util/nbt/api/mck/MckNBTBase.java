package com.infumia.t3sl4.util.nbt.api.mck;

import com.infumia.t3sl4.util.nbt.api.NBTBase;
import org.jetbrains.annotations.NotNull;

public class MckNBTBase implements com.infumia.t3sl4.util.nbt.api.NBTBase<com.infumia.t3sl4.util.nbt.api.NBTBase> {
   @NotNull
   public NBTBase value() {
      return this;
   }

   @NotNull
   public String toString() {
      return "";
   }
}
