package com.infumia.t3sl4.sandikspawner.util;

import com.infumia.t3sl4.sandikspawner.mock.MckChestType;
import com.infumia.t3sl4.sandikspawner.chest.type.ChestType;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.FirstOf;
import org.jetbrains.annotations.NotNull;

public class ChestFind implements Scalar<ChestType> {
   @NotNull
   private final List<Entry<String, ChestType>> chests;
   @NotNull
   private final String id;

   public ChestFind(@NotNull List<Entry<String, ChestType>> chests, @NotNull String id) {
      this.chests = chests;
      this.id = id;
   }

   public ChestType value() {
      try {
         String spawnerId = (String)(new FirstOf((spawner) -> {
            return spawner.equals(this.id);
         }, new Mapped<>(Entry::getKey, this.chests), () -> {
            return "";
         })).value();
         Iterator var2 = this.chests.iterator();

         while(var2.hasNext()) {
            Entry<String, ChestType> ids = (Entry)var2.next();
            if (((String)ids.getKey()).equals(spawnerId)) {
               return (ChestType)ids.getValue();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return new MckChestType();
   }
}
