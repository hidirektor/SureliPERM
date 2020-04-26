package com.infumia.t3sl4.util.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefClass {
   @NotNull
   Class<?> getRealClass();

   boolean isInstance(@NotNull Object var1);

   @NotNull
   RefMethod getPrimitiveMethod(@NotNull String var1, @NotNull Object... var2);

   @NotNull
   RefMethod getMethod(@NotNull String var1, @NotNull Object... var2);

   @NotNull
   RefMethod findPrimitiveMethodByParameter(@NotNull Object... var1);

   @NotNull
   RefMethod findMethodByParameter(@NotNull Object... var1);

   @NotNull
   RefMethod findMethodByName(@NotNull String... var1);

   @NotNull
   RefMethod findMethodByReturnType(@NotNull RefClass var1);

   @NotNull
   RefMethod findMethodByReturnType(@NotNull Class var1);

   @NotNull
   RefConstructed getPrimitiveConstructor(@NotNull Object... var1);

   @NotNull
   RefConstructed getConstructor(@NotNull Object... var1);

   @NotNull
   RefConstructed findConstructor(int var1);

   @NotNull
   com.infumia.t3sl4.util.reflection.RefField getField(@NotNull String var1);

   @NotNull
   com.infumia.t3sl4.util.reflection.RefField findField(@NotNull RefClass var1);

   @NotNull
   RefField findField(@NotNull Class var1);
}
