package com.infumia.t3sl4.util.reflection.parameterized;

import com.infumia.t3sl4.util.reflection.RefParameterized;
import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

public class ParameterizedOf<T> implements RefParameterized<T> {
   @NotNull
   private final Converted converted;

   public ParameterizedOf(@NotNull Converted converted) {
      this.converted = converted;
   }

   public ParameterizedOf(boolean primitive, @NotNull Object... objects) {
      this(new Converted(primitive, objects));
   }

   public ParameterizedOf(@NotNull Object... objects) {
      this(false, objects);
   }

   @NotNull
   public T apply(@NotNull Func<Class[], T> func) throws Exception {
      return func.apply(this.converted.value());
   }
}
