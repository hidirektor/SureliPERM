package com.infumia.t3sl4.util.reflection.clazz;

import com.infumia.t3sl4.util.reflection.RefConstructed;
import com.infumia.t3sl4.util.reflection.parameterized.ParameterizedOf;
import com.infumia.t3sl4.util.reflection.constructor.ConstructorOf;
import com.infumia.t3sl4.util.reflection.field.FieldOf;
import com.infumia.t3sl4.util.reflection.mck.MckConstructed;
import com.infumia.t3sl4.util.reflection.mck.MckField;
import com.infumia.t3sl4.util.reflection.mck.MckMethod;
import com.infumia.t3sl4.util.reflection.method.MethodOf;
import org.cactoos.iterable.Filtered;
import org.cactoos.list.Joined;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.FirstOf;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ClassOf implements com.infumia.t3sl4.util.reflection.RefClass {

   private static final Logger LOGGER = new com.infumia.t3sl4.util.reflection.LoggerOf(ClassOf.class);

   @NotNull
   private final Class<?> clazz;

   public ClassOf(@NotNull final Class<?> clazz) {
      this.clazz = clazz;
   }

   public ClassOf(@NotNull final Object object) {
      this(object.getClass());
   }

   public ClassOf(@NotNull final String className) throws ClassNotFoundException {
      this(Class.forName(className));
   }

   @NotNull
   public Class<?> getRealClass() {
      return clazz;
   }

   @Override
   public boolean isInstance(@NotNull final Object object) {
      return clazz.isInstance(object);
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefMethod getPrimitiveMethod(@NotNull final String name, @NotNull final Object... types) {
      return getMethod0( name, true, types);
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefMethod getMethod(@NotNull final String name, @NotNull final Object... types) {
      return getMethod0( name, false, types);
   }

   @NotNull
   private com.infumia.t3sl4.util.reflection.RefMethod getMethod0(@NotNull final String name, final boolean primitive, @NotNull final Object... types) {
      final com.infumia.t3sl4.util.reflection.RefParameterized<com.infumia.t3sl4.util.reflection.RefMethod> parameter = new com.infumia.t3sl4.util.reflection.parameterized.ParameterizedOf<>(primitive, types);

      try {
         try {
            return parameter.apply(classes ->
                    new MethodOf(clazz.getMethod(name, classes)));
         } catch (NoSuchMethodException ignored) {
            return parameter.apply(classes ->
                    new MethodOf(clazz.getDeclaredMethod(name, classes)));
         }
      } catch (Exception exception) {
         LOGGER.warning("getMethod0(String, boolean, Object[]) -> \n"
                 + exception.toString());
         return new MckMethod();
      }
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefConstructed getPrimitiveConstructor(@NotNull Object... types) {
      return getConstructor0(true, types);
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefConstructed getConstructor(@NotNull final Object... types) {
      return getConstructor0(false, types);
   }

   @NotNull
   private com.infumia.t3sl4.util.reflection.RefConstructed getConstructor0(final boolean primitive, @NotNull Object... types) {
      final com.infumia.t3sl4.util.reflection.RefParameterized<com.infumia.t3sl4.util.reflection.RefConstructed> parameter = new com.infumia.t3sl4.util.reflection.parameterized.ParameterizedOf<>(primitive, types);

      try {
         try {
            return parameter.apply(classes ->
                    new ConstructorOf(clazz.getConstructor(classes)));
         } catch (Exception ignored) {
            return parameter.apply(classes ->
                    new ConstructorOf(clazz.getDeclaredConstructor(classes)));
         }
      } catch (Exception exception) {
         LOGGER.warning("getConstructor0(boolean, Object[]) -> \n"
                 + exception.toString());
         return new MckConstructed();
      }
   }

   @NotNull
   public com.infumia.t3sl4.util.reflection.RefMethod findPrimitiveMethodByParameter(@NotNull final Object... types) {
      return findMethod0(true, types);
   }

   @NotNull
   public com.infumia.t3sl4.util.reflection.RefMethod findMethodByParameter(@NotNull final Object... types) {
      return findMethod0(false, types);
   }

   @NotNull
   private com.infumia.t3sl4.util.reflection.RefMethod findMethod0(final boolean primitive, @NotNull final Object... types) {
      final com.infumia.t3sl4.util.reflection.RefParameterized<com.infumia.t3sl4.util.reflection.RefMethod> parameter = new ParameterizedOf<>(primitive, types);

      final List<Method> methods = new ListOf<>(
              new Joined<>(
                      new ListOf<>(
                              clazz.getMethods()
                      ),
                      new ListOf<Method>(
                              clazz.getDeclaredMethods()
                      )
              )
      );

      final List<Class> classList = new ArrayList<>();

      try {
         parameter.apply(classes -> {
            classList.addAll(new ListOf<>(classes));
            return new MckMethod();
         });
      } catch (Exception ignore) {
      }

      findMethod:
      for (Method method : methods) {
         final Class[] methodTypes = method.getParameterTypes();

         if (methodTypes.length != classList.size())
            continue;

         for (int i = 0; i < classList.size(); i++)
            if (!Arrays.equals(classList.toArray(new Class[0]), methodTypes))
               continue findMethod;
         return new MethodOf(method);
      }

      LOGGER.warning("findMethod0(boolean, Object[]) -> \n"
              + new NoSuchMethodException().toString());
      return new MckMethod();
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefMethod findMethodByName(@NotNull final String... names) {
      try {
         return new MethodOf(
                 new FirstOf<>(
                         input -> true,
                         new Filtered<>(
                                 method -> {
                                    if (method == null)
                                       return false;

                                    for (String name : names)
                                       return method.getName().equals(name);

                                    return false;
                                 },
                                 new Joined<>(
                                         new ListOf<>(
                                                 clazz.getMethods()
                                         ),
                                         new ListOf<Method>(
                                                 clazz.getDeclaredMethods()
                                         )
                                 )
                         ),
                         () -> {
                            LOGGER.warning("findMethodByName(String[]) -> \n"
                                    + new NoSuchMethodException().toString());
                            return null;
                         }
                 ).value()
         );
      } catch (Exception exception) {
         LOGGER.warning("findMethodByName(String[]) -> \n"
                 + exception.toString());
         return new MckMethod();
      }
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefMethod findMethodByReturnType(@NotNull final com.infumia.t3sl4.util.reflection.RefClass type) {
      return findMethodByReturnType(type.getRealClass());
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefMethod findMethodByReturnType(@NotNull final Class type) {
      try {
         return new MethodOf(
                 new FirstOf<>(
                         method -> method != null && type.equals(method.getReturnType()),
                         new Joined<>(
                                 new ListOf<>(
                                         clazz.getMethods()
                                 ),
                                 new ListOf<Method>(
                                         clazz.getDeclaredMethods()
                                 )
                         ),
                         null
                 ).value()
         );
      } catch (Exception exception) {
         LOGGER.warning("findMethodByReturnType(Class) -> \n"
                 + exception.toString());
         return new MckMethod();
      }
   }

   @NotNull
   @Override
   public RefConstructed findConstructor(final int number) {
      try {
         return new ConstructorOf(
                 new FirstOf<>(
                         constructor ->
                                 constructor != null && constructor.getParameterTypes().length == number,
                         new Joined<>(
                                 new ListOf<>(
                                         clazz.getConstructors()
                                 ),
                                 new ListOf<Constructor>(
                                         clazz.getDeclaredConstructors()
                                 )
                         ),
                         null
                 ).value()
         );
      } catch (Exception exception) {
         LOGGER.warning("findConstructor(int) -> \n"
                 + exception.toString());
         return new MckConstructed();
      }
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefField getField(@NotNull final String name) {
      try {
         try {
            return new FieldOf(clazz.getField(name));
         } catch (NoSuchFieldException ignored) {
            return new FieldOf(clazz.getDeclaredField(name));
         }
      } catch (Exception exception) {
         LOGGER.warning("getField(String) -> \n"
                 + exception.toString());
         return new MckField();
      }
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefField findField(@NotNull final com.infumia.t3sl4.util.reflection.RefClass type) {
      return findField(type.getRealClass());
   }

   @NotNull
   @Override
   public com.infumia.t3sl4.util.reflection.RefField findField(@NotNull final Class type) {
      try {
         return new FieldOf(
                 new FirstOf<>(
                         field ->
                                 field != null && type.equals(field.getType()),
                         new Joined<>(
                                 new ListOf<>(
                                         clazz.getFields()
                                 ),
                                 new ListOf<Field>(
                                         clazz.getDeclaredFields()
                                 )
                         ),
                         null
                 ).value()
         );
      } catch (Exception exception) {
         LOGGER.warning("findField(Class) -> \n"
                 + exception.toString());
         return new MckField();
      }
   }

}