package com.infumia.t3sl4.sandikspawner.files;

import org.jetbrains.annotations.NotNull;

public final class ReplaceAll {
   @NotNull
   private final String raw;
   @NotNull
   private final String[] replace;

   public ReplaceAll(@NotNull String raw, @NotNull String... replace) {
      this.raw = raw;
      this.replace = replace;
   }

   @NotNull
   public String apply(@NotNull String... replaced) {
      if (this.replace.length != replaced.length) {
         return this.raw;
      } else {
         String cacheRaw = this.raw;

         for(int i = 0; i < this.replace.length; ++i) {
            String regex = this.replace[i];
            String value = replaced[i];
            cacheRaw = this.raw.replaceAll(regex, value);
         }

         return cacheRaw;
      }
   }
}
