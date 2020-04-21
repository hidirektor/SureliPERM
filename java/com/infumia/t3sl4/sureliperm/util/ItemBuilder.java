package com.infumia.t3sl4.sureliperm.util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemBuilder {
   private Material mat;
   private int amount = 1;
   private short data = 0;
   private String name;
   private boolean glowing = false;
   private List<String> lore = new ArrayList();
   private List<ItemBuilder.EnchantString> enchants = new ArrayList();

   @NotNull
   public ItemBuilder copy(@NotNull ItemStack is) {
      this.mat = is.getType();
      this.amount = is.getAmount();
      if (is.getData() != null) {
         this.data = (short)is.getData().getData();
      }

      if (is.hasItemMeta()) {
         this.name = is.getItemMeta().getDisplayName();
         this.lore = is.getItemMeta().getLore();
      }

      return this;
   }

   public static ItemBuilder buildWithConfig(Config c, String key, HashMap<String, String> replaces) {
      String material = c.getString(key + ".material", "bedrock");
      int amount = c.getInt(key + ".amount", 1);
      short data = (short)c.getInt(key + ".amount", 0);
      String name = c.getString(key + ".name", "");
      List<String> lore = c.isSet(key + ".lore") ? c.getStringList(key + ".lore") : new ArrayList();
      List<String> enchants = c.isSet(key + ".enchants") ? c.getStringList(key + ".enchants") : new ArrayList();
      boolean glow = c.getBoolean(key + ".glow", false);

      String k;
      for(Iterator var10 = replaces.keySet().iterator(); var10.hasNext(); lore = replaceList((List)lore, k, (String)replaces.get(k))) {
         k = (String)var10.next();
         name = name.replace(k, (CharSequence)replaces.get(k));
      }

      return (new ItemBuilder()).material(material).amount(amount).name(name).lore((List)lore).data(data).enchants((List)enchants).glow(glow);
   }

   @NotNull
   public ItemBuilder material(@NotNull Material mat) {
      this.mat = mat;
      return this;
   }

   @NotNull
   public ItemBuilder material(@NotNull String matName) {
      this.mat = Material.matchMaterial(matName);
      return this;
   }

   @NotNull
   public ItemBuilder amount(int amount) {
      this.amount = amount;
      return this;
   }

   @NotNull
   public ItemBuilder data(short data) {
      this.data = data;
      return this;
   }

   @NotNull
   public ItemBuilder name(String name) {
      if (name == null) {
         return this;
      } else {
         if (name.equals("")) {
            this.name = "";
         } else {
            this.name = ChatColor.translateAlternateColorCodes('&', name);
         }

         return this;
      }
   }

   @NotNull
   public ItemBuilder lore(String... lore) {
      if (lore == null) {
         return this;
      } else {
         this.lore.clear();
         int size = lore.length;

         for(int i = 0; i < size; ++i) {
            this.lore.add(ChatColor.translateAlternateColorCodes('&', lore[i]));
         }

         return this;
      }
   }

   @NotNull
   public ItemBuilder lore(List<String> lore) {
      if (lore == null) {
         return this;
      } else {
         List<String> newLore = new ArrayList(lore.size());
         lore.forEach((e) -> {
            newLore.add(ChatColor.translateAlternateColorCodes('&', e));
         });
         this.lore = newLore;
         return this;
      }
   }

   @NotNull
   public ItemBuilder enchants(List<String> strs) {
      if (strs == null) {
         return this;
      } else {
         int size = strs.size();
         if (size == 0) {
            return this;
         } else {
            ItemBuilder.EnchantString[] esArray = new ItemBuilder.EnchantString[size];

            for(int i = 0; i < size; ++i) {
               esArray[i] = new ItemBuilder.EnchantString((String)strs.get(i));
            }

            return this.enchants(esArray);
         }
      }
   }

   @NotNull
   public ItemBuilder enchants(String... strs) {
      if (strs == null) {
         return this;
      } else {
         ItemBuilder.EnchantString[] esArray = new ItemBuilder.EnchantString[strs.length];

         for(int i = 0; i < strs.length; ++i) {
            esArray[i] = new ItemBuilder.EnchantString(strs[i]);
         }

         return this.enchants(esArray);
      }
   }

   @NotNull
   private ItemBuilder enchants(ItemBuilder.EnchantString... enchants) {
      if (enchants == null) {
         return this;
      } else {
         ItemBuilder.EnchantString[] var2 = enchants;
         int var3 = enchants.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            ItemBuilder.EnchantString estr = var2[var4];
            if (estr.valid()) {
               this.enchants.add(estr);
            }
         }

         return this;
      }
   }

   @NotNull
   public ItemBuilder replacedInName(String... replacements) {
      if (replacements == null) {
         return this;
      } else {
         this.name = this.replaceString(this.name, replacements);
         return this;
      }
   }

   @NotNull
   public ItemBuilder replacedInLore(String... replacements) {
      if (replacements == null) {
         return this;
      } else {
         this.lore = this.replaceList(this.lore, replacements);
         int size = this.lore.size();

         for(int i = 0; i < size; ++i) {
            this.lore.set(i, this.replaceString((String)this.lore.get(i), replacements));
         }

         return this;
      }
   }

   @NotNull
   public ItemBuilder glow(boolean glow) {
      this.glowing = glow;
      return this;
   }

   @NotNull
   public ItemBuilder glow() {
      return this.glow(true);
   }

   @NotNull
   public ItemStack build() {
      ItemStack item = new ItemStack(this.mat, this.amount, this.data);
      ItemMeta meta = item.getItemMeta();
      if (meta == null) {
         throw new RuntimeException("Impossible?");
      } else {
         meta.setDisplayName(this.name);
         meta.setLore(this.lore);
         Iterator var3 = this.enchants.iterator();

         while(var3.hasNext()) {
            ItemBuilder.EnchantString estr = (ItemBuilder.EnchantString)var3.next();
            meta.addEnchant(estr.enchantment(), estr.level(), true);
         }

         if (this.enchants.isEmpty() && this.glowing) {
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
         }

         item.setItemMeta(meta);
         return item;
      }
   }

   private List<String> replaceList(List<String> list, String... values) {
      if (values.length == 0) {
         return list;
      } else {
         List<String> retList = new ArrayList();
         Iterator var4 = list.iterator();

         while(var4.hasNext()) {
            String base = (String)var4.next();
            retList.add(this.replaceString(base, values));
         }

         return retList;
      }
   }

   private String replaceString(String firstVal, String... arrs) {
      if (arrs.length == 0) {
         return firstVal;
      } else {
         StringBuilder sb = new StringBuilder(firstVal);
         if (arrs.length % 2 != 0) {
            throw new IllegalArgumentException("Replacement from-to pairing is wrong!");
         } else {
            for(int i = 0; i < arrs.length - 1; i += 2) {
               String key = arrs[i];
               String value = arrs[i + 1];

               int nextSearchStart;
               for(int start = sb.indexOf(key, 0); start > -1; start = sb.indexOf(key, nextSearchStart)) {
                  int end = start + key.length();
                  nextSearchStart = start + value.length();
                  sb.replace(start, end, value);
               }
            }

            return sb.toString();
         }
      }
   }

   public static List<String> replaceList(List<String> list, String key, String val) {
      List<String> retList = new ArrayList();
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         String base = (String)var4.next();
         retList.add(base.replace(key, val));
      }

      return retList;
   }

   class EnchantString {
      private Enchantment enchant;
      private int level;

      EnchantString(Enchantment enchant, int level) {
         this.enchant = enchant;
         this.level = Math.max(0, level);
      }

      EnchantString(String enchantName, int level) {
         this((Enchantment)Enchantment.getByName(enchantName.replace(" ", "_").toUpperCase(Locale.ENGLISH)), level);
      }

      EnchantString(String token) {
         String[] split = token.split(":");
         if (split.length != 2) {
            this.level = -1;
         } else {
            this.enchant = Enchantment.getByName(split[0].replace(" ", "_").toUpperCase(Locale.ENGLISH));

            try {
               this.level = Integer.parseInt(split[1]);
            } catch (NumberFormatException var5) {
               this.level = -1;
            }

         }
      }

      @Nullable
      Enchantment enchantment() {
         return this.enchant;
      }

      int level() {
         return this.level;
      }

      boolean valid() {
         return this.enchant != null && this.level != -1;
      }
   }
}
