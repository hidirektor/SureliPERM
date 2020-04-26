package com.infumia.t3sl4.sandikspawner.files;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class Config {
   @NotNull
   public final String language;
   @NotNull
   public final List<String> sign;
   @NotNull
   public final String ganimetAnvilAlis;
   @NotNull
   public final String ganimetAnvilSatis;
   @NotNull
   public final String yetkiliAnvil;
   @NotNull
   public final ItemStack chestItem;
   @NotNull
   public final List<String> book;
   @NotNull
   public final String yes;
   @NotNull
   public final String no;

   public Config(@NotNull String language, @NotNull List<String> sign, @NotNull String ganimetAnvilAlis, @NotNull String ganimetAnvilSatis, @NotNull String yetkiliAnvil, @NotNull ItemStack chestItem, @NotNull List<String> book, @NotNull String yes, @NotNull String no) {
      this.language = language;
      this.sign = sign;
      this.ganimetAnvilAlis = ganimetAnvilAlis;
      this.ganimetAnvilSatis = ganimetAnvilSatis;
      this.yetkiliAnvil = yetkiliAnvil;
      this.chestItem = chestItem;
      this.book = book;
      this.yes = yes;
      this.no = no;
   }
}
