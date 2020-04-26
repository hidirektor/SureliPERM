package com.infumia.t3sl4.util.nbt.api;

public enum NBTType {
   END(0),
   BYTE(1),
   SHORT(2),
   INTEGER(3),
   LONG(4),
   FLOAT(5),
   DOUBLE(6),
   BYTE_ARRAY(7),
   STRING(8),
   TAG_LIST(9),
   COMPOUND(10),
   BOOLEAN(2),
   INT_ARRAY(11),
   LONG_ARRAY(12);

   private final int id;

   private NBTType(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}
