package com.infumia.t3sl4.util.reflection.parameterized;

import com.infumia.t3sl4.util.reflection.RefClass;
import java.util.concurrent.atomic.AtomicInteger;
import org.cactoos.Scalar;
import org.cactoos.scalar.And;
import org.jetbrains.annotations.NotNull;

public class Converted implements Scalar<Class[]> {
   private final boolean isPrimitive;
   private final Object[] objects;

   public Converted(boolean isPrimitive, @NotNull Object... objects) {
      this.isPrimitive = isPrimitive;
      this.objects = objects;
   }

   @NotNull
   public Class[] value() {
      Class[] classes = new Class[this.objects.length];
      AtomicInteger i = new AtomicInteger();

      try {
         (new And((object) -> {
            Class clazz;
            if (object instanceof RefClass) {
               clazz = ((RefClass)object).getRealClass();
            } else if (object instanceof Class) {
               clazz = (Class)object;
            } else {
               clazz = object.getClass();
            }

            if (!this.isPrimitive) {
               classes[i.getAndIncrement()] = clazz;
            } else {
               classes[i.getAndIncrement()] = (new Primitive(clazz)).value();
            }
         }, this.objects)).value();
      } catch (Exception var4) {
      }

      return classes;
   }
}
