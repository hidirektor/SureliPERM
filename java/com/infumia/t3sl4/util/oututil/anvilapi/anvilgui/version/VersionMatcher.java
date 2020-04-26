package com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.version;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;

public class VersionMatcher {
   private final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
   private final List<Class<? extends VersionWrapper>> versions = Arrays.asList(Wrapper1_8_R1.class, Wrapper1_8_R2.class, Wrapper1_8_R3.class, Wrapper1_9_R1.class, Wrapper1_9_R2.class, Wrapper1_10_R1.class, Wrapper1_11_R1.class, Wrapper1_12_R1.class, Wrapper1_13_R1.class, Wrapper1_13_R2.class, Wrapper1_14_R1.class);

   public VersionWrapper match() {
      try {
         return (VersionWrapper)((Class)this.versions.stream().filter((version) -> {
            return version.getSimpleName().substring(7).equals(this.serverVersion);
         }).findFirst().orElseThrow(() -> {
            return new RuntimeException("Your server version isn't supported in AnvilGUI!");
         })).newInstance();
      } catch (InstantiationException | IllegalAccessException var2) {
         throw new RuntimeException(var2);
      }
   }
}
