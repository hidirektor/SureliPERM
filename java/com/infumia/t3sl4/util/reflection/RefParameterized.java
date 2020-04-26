package com.infumia.t3sl4.util.reflection;

import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

public interface RefParameterized<T> {
   T apply(@NotNull Func<Class[], T> var1) throws Exception;
}
