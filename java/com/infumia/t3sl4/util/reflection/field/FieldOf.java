package com.infumia.t3sl4.util.reflection.field;

import com.infumia.t3sl4.util.reflection.LoggerOf;
import com.infumia.t3sl4.util.reflection.RefField;
import com.infumia.t3sl4.util.reflection.RefFieldExecuted;
import java.lang.reflect.Field;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class FieldOf implements RefField {
   private static final Logger LOGGER_FIELD_OF = new LoggerOf(new Class[]{FieldOf.class});
   private static final Logger LOGGER = new LoggerOf(new Class[]{FieldOf.class, FieldOf.FieldExecuted.class});
   @NotNull
   private final Field field;
   private final boolean isAccessible;

   public FieldOf(@NotNull Field field) {
      this.field = field;
      this.isAccessible = field.isAccessible();
   }

   public void set(@NotNull Object value) {
      this.field.setAccessible(true);

      try {
         this.field.set((Object)null, value);
      } catch (Exception var6) {
         LOGGER_FIELD_OF.warning("set(Object) -> \n" + var6.getMessage());
      } finally {
         this.field.setAccessible(this.isAccessible);
      }

   }

   @NotNull
   public Object get(@NotNull Object fallback) {
      this.field.setAccessible(true);

      Object var3;
      try {
         Object var2 = this.field.get((Object)null);
         return var2;
      } catch (Exception var7) {
         LOGGER_FIELD_OF.warning("get() -> \n" + var7.getMessage());
         var3 = fallback;
      } finally {
         this.field.setAccessible(this.isAccessible);
      }

      return var3;
   }

   @NotNull
   public RefFieldExecuted of(@NotNull Object object) {
      return new FieldOf.FieldExecuted(object);
   }

   private class FieldExecuted implements RefFieldExecuted {
      @NotNull
      private final Object object;

      FieldExecuted(@NotNull Object object) {
         this.object = object;
      }

      public void set(@NotNull Object value) {
         FieldOf.this.field.setAccessible(true);

         try {
            FieldOf.this.field.set(this.object, value);
         } catch (Exception var6) {
            FieldOf.LOGGER.warning("set(Object) -> \n" + var6.getMessage());
         } finally {
            FieldOf.this.field.setAccessible(FieldOf.this.isAccessible);
         }

      }

      @NotNull
      public Object get(@NotNull Object fallback) {
         FieldOf.this.field.setAccessible(true);

         Object var3;
         try {
            Object var2 = FieldOf.this.field.get(this.object);
            return var2;
         } catch (Exception var7) {
            FieldOf.LOGGER.warning("get() -> \n" + var7.getMessage());
            var3 = fallback;
         } finally {
            FieldOf.this.field.setAccessible(FieldOf.this.isAccessible);
         }

         return var3;
      }
   }
}
