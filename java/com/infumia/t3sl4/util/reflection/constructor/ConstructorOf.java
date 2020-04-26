package com.infumia.t3sl4.util.reflection.constructor;

import com.infumia.t3sl4.util.reflection.LoggerOf;
import com.infumia.t3sl4.util.reflection.RefConstructed;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class ConstructorOf implements RefConstructed {
   private static final Logger LOGGER = new LoggerOf(new Class[]{ConstructorOf.class});
   @NotNull
   private final Constructor constructor;
   private final boolean isAccessible;

   public ConstructorOf(@NotNull Constructor constructor) {
      this.constructor = constructor;
      this.isAccessible = constructor.isAccessible();
   }

   @NotNull
   public Object create(@NotNull Object fallback, @NotNull Object... parameters) {
      this.constructor.setAccessible(true);

      Object var4;
      try {
         Object var3 = this.constructor.newInstance(parameters);
         return var3;
      } catch (Exception var8) {
         LOGGER.warning("create(Object[]) -> \n" + var8.getMessage());
         var4 = fallback;
      } finally {
         this.constructor.setAccessible(this.isAccessible);
      }

      return var4;
   }
}
