package com.infumia.t3sl4.util.observer.source;

import com.infumia.t3sl4.util.observer.Target;
import com.infumia.t3sl4.util.observer.Source;

import java.util.Iterator;
import java.util.Vector;
import org.jetbrains.annotations.NotNull;

public final class BasicSource<T> implements Source<T> {
   @NotNull
   private final Vector<com.infumia.t3sl4.util.observer.Target<T>> targets = new Vector();

   public void subscribe(@NotNull com.infumia.t3sl4.util.observer.Target<T> target) {
      if (!this.targets.contains(target)) {
         this.targets.add(target);
      }

   }

   public void unsubscribe(@NotNull com.infumia.t3sl4.util.observer.Target<T> target) {
      this.targets.remove(target);
   }

   public void notifyTargets(@NotNull T argument) {
      Iterator var2 = this.targets.iterator();

      while(var2.hasNext()) {
         com.infumia.t3sl4.util.observer.Target<T> target = (Target)var2.next();
         target.update(argument);
      }

   }
}
