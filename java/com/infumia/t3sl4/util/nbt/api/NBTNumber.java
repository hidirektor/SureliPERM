package com.infumia.t3sl4.util.nbt.api;

public interface NBTNumber<T> extends NBTBase<T> {
   long longValue();

   int intValue();

   short shortValue();

   byte byteValue();

   double doubleValue();

   float floatValue();
}
