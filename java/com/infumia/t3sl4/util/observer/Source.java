package com.infumia.t3sl4.util.observer;

public interface Source<T> {
   void subscribe(Target<T> var1);

   void unsubscribe(Target<T> var1);

   void notifyTargets(T var1);
}
