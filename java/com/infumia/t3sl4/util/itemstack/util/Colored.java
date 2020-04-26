package com.infumia.t3sl4.util.itemstack.util;

import com.infumia.t3sl4.util.itemstack.ScalarRuntime;
import org.bukkit.ChatColor;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public class Colored extends ScalarRuntime<String> {
   public Colored(@NotNull Scalar<String> scalar) {
      super(scalar);
   }

   public Colored(@NotNull String text) {
      this(() -> {
         return ChatColor.translateAlternateColorCodes('&', text);
      });
   }

   public String toString() {
      try {
         return (String)this.value();
      } catch (Exception var2) {
         return "";
      }
   }
}
