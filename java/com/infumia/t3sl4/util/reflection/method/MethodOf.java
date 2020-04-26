package com.infumia.t3sl4.util.reflection.method;

import com.infumia.t3sl4.util.reflection.LoggerOf;
import com.infumia.t3sl4.util.reflection.RefMethod;
import com.infumia.t3sl4.util.reflection.RefMethodExecuted;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class MethodOf implements RefMethod {
   private static final Logger LOGGER_METHOD_OF = new LoggerOf(new Class[]{MethodOf.class});
   private static final Logger LOGGER = new LoggerOf(new Class[]{MethodOf.class, MethodOf.MethodExecuted.class});
   @NotNull
   private final Method method;
   private final boolean isAccessible;

   public MethodOf(@NotNull Method method) {
      this.method = method;
      this.isAccessible = method.isAccessible();
   }

   @NotNull
   public RefMethodExecuted of(@NotNull Object object) {
      return new MethodOf.MethodExecuted(object);
   }

   @NotNull
   public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
      this.method.setAccessible(true);

      Object var4;
      try {
         Object var3 = this.method.invoke((Object)null, parameters);
         return var3;
      } catch (Exception var8) {
         LOGGER_METHOD_OF.warning("call(Object[]) -> \n" + var8.toString());
         var4 = fallback;
      } finally {
         this.method.setAccessible(this.isAccessible);
      }

      return var4;
   }

   private class MethodExecuted implements RefMethodExecuted {
      @NotNull
      private final Object object;

      MethodExecuted(@NotNull Object object) {
         this.object = object;
      }

      @NotNull
      public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
         MethodOf.this.method.setAccessible(true);

         Object var4;
         try {
            Object var3 = MethodOf.this.method.invoke(this.object, parameters) == null ? fallback : MethodOf.this.method.invoke(this.object, parameters);
            return var3;
         } catch (Exception var8) {
            MethodOf.LOGGER.warning("call(Object[]) -> \n" + var8.toString());
            var4 = fallback;
         } finally {
            MethodOf.this.method.setAccessible(MethodOf.this.isAccessible);
         }

         return var4;
      }
   }
}
