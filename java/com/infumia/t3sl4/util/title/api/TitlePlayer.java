package com.infumia.t3sl4.util.title.api;

import org.jetbrains.annotations.NotNull;

public interface TitlePlayer {
   void clearTitle();

   void sendTitle(@NotNull String var1);

   void sendTitle(@NotNull String var1, @NotNull String var2);

   void sendTitle(@NotNull String var1, int var2, int var3, int var4);

   void sendTitle(@NotNull String var1, @NotNull String var2, int var3, int var4, int var5);

   void sendTitle(@NotNull String var1, @NotNull String var2, int var3);
}
