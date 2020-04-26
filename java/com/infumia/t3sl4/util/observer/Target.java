package com.infumia.t3sl4.util.observer;

import org.jetbrains.annotations.NotNull;

public interface Target<T> {
   void update(@NotNull T var1);
}
