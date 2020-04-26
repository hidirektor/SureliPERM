package com.infumia.t3sl4.util.itemstack.util;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import java.util.List;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.jetbrains.annotations.NotNull;

public class ColoredList extends ScalarRuntime<List<String>> {
   public ColoredList(@NotNull Scalar<List<String>> scalar) {
      super(scalar);
   }

   public ColoredList(@NotNull List<String> list) {
      this(() -> {
         return new Mapped<>((input) -> {
            return (String)(new Colored(input)).value();
         }, list);
      });
   }

   public ColoredList(@NotNull String... strings) {
      this((List)(new ListOf(strings)));
   }
}
