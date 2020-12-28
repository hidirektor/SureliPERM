package me.t3sl4.sureliperm.util;

import com.sun.istack.internal.Nullable;
import java.util.Locale;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;
import java.util.Iterator;
import java.util.HashMap;
import com.sun.istack.internal.NotNull;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;

public final class ItemBuilder {
    private Material mat;
    private int amount;
    private short data;
    private String name;
    private boolean glowing;
    private List<String> lore;
    private List<EnchantString> enchants;

    public ItemBuilder() {
        this.amount = 1;
        this.data = 0;
        this.glowing = false;
        this.lore = new ArrayList<String>();
        this.enchants = new ArrayList<EnchantString>();
    }

    @NotNull
    public ItemBuilder copy(@NotNull final ItemStack is) {
        this.mat = is.getType();
        this.amount = is.getAmount();
        if (is.getData() != null) {
            this.data = is.getData().getData();
        }
        if (is.hasItemMeta()) {
            this.name = is.getItemMeta().getDisplayName();
            this.lore = (List<String>)is.getItemMeta().getLore();
        }
        return this;
    }

    public static ItemBuilder buildWithConfig(final Config c, final String key, final HashMap<String, String> replaces) {
        final String material = c.getString(key + ".material", "bedrock");
        final int amount = c.getInt(key + ".amount", 1);
        final short data = (short)c.getInt(key + ".amount", 0);
        String name = c.getString(key + ".name", "");
        List<String> lore = c.isSet(key + ".lore") ? c.getStringList(key + ".lore") : new ArrayList<String>();
        final List<String> enchants = c.isSet(key + ".enchants") ? c.getStringList(key + ".enchants") : new ArrayList<String>();
        final boolean glow = c.getBoolean(key + ".glow", false);
        for (final String k : replaces.keySet()) {
            name = name.replace(k, replaces.get(k));
            lore = replaceList(lore, k, replaces.get(k));
        }
        return new ItemBuilder().material(material).amount(amount).name(name).lore(lore).data(data).enchants(enchants).glow(glow);
    }

    @NotNull
    public ItemBuilder material(@NotNull final Material mat) {
        this.mat = mat;
        return this;
    }

    @NotNull
    public ItemBuilder material(@NotNull final String matName) {
        this.mat = Material.matchMaterial(matName);
        return this;
    }

    @NotNull
    public ItemBuilder amount(final int amount) {
        this.amount = amount;
        return this;
    }

    @NotNull
    public ItemBuilder data(final short data) {
        this.data = data;
        return this;
    }

    @NotNull
    public ItemBuilder name(final String name) {
        if (name == null) {
            return this;
        }
        if (name.equals("")) {
            this.name = "";
        }
        else {
            this.name = ChatColor.translateAlternateColorCodes('&', name);
        }
        return this;
    }

    @NotNull
    public ItemBuilder lore(final String... lore) {
        if (lore == null) {
            return this;
        }
        this.lore.clear();
        for (int size = lore.length, i = 0; i < size; ++i) {
            this.lore.add(ChatColor.translateAlternateColorCodes('&', lore[i]));
        }
        return this;
    }

    @NotNull
    public ItemBuilder lore(final List<String> lore) {
        if (lore == null) {
            return this;
        }
        final List<String> newLore = new ArrayList<String>(lore.size());
        lore.forEach(e -> newLore.add(ChatColor.translateAlternateColorCodes('&', e)));
        this.lore = newLore;
        return this;
    }

    @NotNull
    public ItemBuilder enchants(final List<String> strs) {
        if (strs == null) {
            return this;
        }
        final int size = strs.size();
        if (size == 0) {
            return this;
        }
        final EnchantString[] esArray = new EnchantString[size];
        for (int i = 0; i < size; ++i) {
            esArray[i] = new EnchantString(strs.get(i));
        }
        return this.enchants(esArray);
    }

    @NotNull
    public ItemBuilder enchants(final String... strs) {
        if (strs == null) {
            return this;
        }
        final EnchantString[] esArray = new EnchantString[strs.length];
        for (int i = 0; i < strs.length; ++i) {
            esArray[i] = new EnchantString(strs[i]);
        }
        return this.enchants(esArray);
    }

    @NotNull
    private ItemBuilder enchants(final EnchantString... enchants) {
        if (enchants == null) {
            return this;
        }
        final EnchantString[] var2 = enchants;
        for (int var3 = enchants.length, var4 = 0; var4 < var3; ++var4) {
            final EnchantString estr = var2[var4];
            if (estr.valid()) {
                this.enchants.add(estr);
            }
        }
        return this;
    }

    @NotNull
    public ItemBuilder replacedInName(final String... replacements) {
        if (replacements == null) {
            return this;
        }
        this.name = this.replaceString(this.name, replacements);
        return this;
    }

    @NotNull
    public ItemBuilder replacedInLore(final String... replacements) {
        if (replacements == null) {
            return this;
        }
        this.lore = this.replaceList(this.lore, replacements);
        for (int size = this.lore.size(), i = 0; i < size; ++i) {
            this.lore.set(i, this.replaceString(this.lore.get(i), replacements));
        }
        return this;
    }

    @NotNull
    public ItemBuilder glow(final boolean glow) {
        this.glowing = glow;
        return this;
    }

    @NotNull
    public ItemBuilder glow() {
        return this.glow(true);
    }

    @NotNull
    public ItemStack build() {
        final ItemStack item = new ItemStack(this.mat, this.amount, this.data);
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            throw new RuntimeException("Impossible?");
        }
        meta.setDisplayName(this.name);
        meta.setLore((List)this.lore);
        for (final EnchantString estr : this.enchants) {
            meta.addEnchant(estr.enchantment(), estr.level(), true);
        }
        if (this.enchants.isEmpty() && this.glowing) {
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        }
        item.setItemMeta(meta);
        return item;
    }

    private List<String> replaceList(final List<String> list, final String... values) {
        if (values.length == 0) {
            return list;
        }
        final List<String> retList = new ArrayList<String>();
        for (final String base : list) {
            retList.add(this.replaceString(base, values));
        }
        return retList;
    }

    private String replaceString(final String firstVal, final String... arrs) {
        if (arrs.length == 0) {
            return firstVal;
        }
        final StringBuilder sb = new StringBuilder(firstVal);
        if (arrs.length % 2 != 0) {
            throw new IllegalArgumentException("Replacement from-to pairing is wrong!");
        }
        for (int i = 0; i < arrs.length - 1; i += 2) {
            final String key = arrs[i];
            final String value = arrs[i + 1];
            int nextSearchStart;
            for (int start = sb.indexOf(key, 0); start > -1; start = sb.indexOf(key, nextSearchStart)) {
                final int end = start + key.length();
                nextSearchStart = start + value.length();
                sb.replace(start, end, value);
            }
        }
        return sb.toString();
    }

    public static List<String> replaceList(final List<String> list, final String key, final String val) {
        final List<String> retList = new ArrayList<String>();
        for (final String base : list) {
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
            this(Enchantment.getByName(enchantName.replace(" ", "_").toUpperCase(Locale.ENGLISH)), level);
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
            return (this.enchant != null && this.level != -1);
        }
    }
}
