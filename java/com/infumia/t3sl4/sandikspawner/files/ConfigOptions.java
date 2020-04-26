package com.infumia.t3sl4.sandikspawner.files;

import com.infumia.t3sl4.util.itemstack.util.Colored;
import com.infumia.t3sl4.util.itemstack.util.ColoredList;
import io.github.portlek.mcyaml.IYaml;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ConfigOptions {
   @NotNull
   private final IYaml yaml;
   @Nullable
   private Config config;

   public ConfigOptions(@NotNull IYaml yaml) {
      this.yaml = yaml;
   }

   @NotNull
   public Config create() {
      if (this.config != null) {
         return this.config;
      } else {
         this.yaml.create();
         String language = (String)this.yaml.getString("language").orElse("tr");

         List<String> sign = this.yaml.getStringList("chest-spawner.sign");
         String ganimetAnvilAlis = this.c((String)this.yaml.getString("chest-spawner.ganimet-anvil-alis").orElse(""));
         String ganimetAnvilSatis = this.c((String)this.yaml.getString("chest-spawner.ganimet-anvil-satis").orElse(""));
         String yetkiliAnvil = this.c((String)this.yaml.getString("chest-spawner.yetkili-anvil").orElse(""));
         ItemStack chestItem = this.yaml.getCustomItemStack("chest-spawner.chest-item");
         List<String> book = (List)(new ColoredList(this.yaml.getStringList("chest-spawner.book"))).value();
         String yes = this.c((String)this.yaml.getString("chest-spawner.clickyes").orElse("chest-spawner.clickyes"));
         String no = this.c((String)this.yaml.getString("chest-spawner.clickno").orElse("chest-spawner.clickno"));
         this.config = new Config(language, sign, ganimetAnvilAlis, ganimetAnvilSatis, yetkiliAnvil, chestItem, book, yes, no);
         return this.config;
      }
   }

   @NotNull
   public Config getConfig() {
      if (this.config == null) {
         this.create();
      }

      return this.config;
   }

   @NotNull
   private String c(@NotNull String text) {
      return (String)(new Colored(text)).value();
   }
}
