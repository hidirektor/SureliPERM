package com.infumia.t3sl4.util.oututil.bookapi;

import java.util.EnumMap;
import java.util.Map;
import org.bukkit.Achievement;

public final class AchievementUtil {
   private static final Map<Achievement, String> achievements = new EnumMap<Achievement, String>(Achievement.class) {
      {
         this.put(Achievement.OPEN_INVENTORY, "openInventory");
         this.put(Achievement.MINE_WOOD, "mineWood");
         this.put(Achievement.BUILD_WORKBENCH, "buildWorkBench");
         this.put(Achievement.BUILD_PICKAXE, "buildPickaxe");
         this.put(Achievement.BUILD_FURNACE, "buildFurnace");
         this.put(Achievement.ACQUIRE_IRON, "aquireIron");
         this.put(Achievement.BUILD_HOE, "buildHoe");
         this.put(Achievement.MAKE_BREAD, "makeBread");
         this.put(Achievement.BAKE_CAKE, "bakeCake");
         this.put(Achievement.BUILD_BETTER_PICKAXE, "buildBetterPickaxe");
         this.put(Achievement.COOK_FISH, "cookFish");
         this.put(Achievement.ON_A_RAIL, "onARail");
         this.put(Achievement.BUILD_SWORD, "buildSword");
         this.put(Achievement.KILL_ENEMY, "killEnemy");
         this.put(Achievement.KILL_COW, "killCow");
         this.put(Achievement.FLY_PIG, "flyPig");
         this.put(Achievement.SNIPE_SKELETON, "snipeSkeleton");
         this.put(Achievement.GET_DIAMONDS, "diamonds");
         this.put(Achievement.NETHER_PORTAL, "portal");
         this.put(Achievement.GHAST_RETURN, "ghast");
         this.put(Achievement.GET_BLAZE_ROD, "blazerod");
         this.put(Achievement.BREW_POTION, "potion");
         this.put(Achievement.END_PORTAL, "thEnd");
         this.put(Achievement.THE_END, "theEnd2");
         this.put(Achievement.ENCHANTMENTS, "enchantments");
         this.put(Achievement.OVERKILL, "overkill");
         this.put(Achievement.BOOKCASE, "bookacase");
         this.put(Achievement.EXPLORE_ALL_BIOMES, "exploreAllBiomes");
         this.put(Achievement.SPAWN_WITHER, "spawnWither");
         this.put(Achievement.KILL_WITHER, "killWither");
         this.put(Achievement.FULL_BEACON, "fullBeacon");
         this.put(Achievement.BREED_COW, "breedCow");
         this.put(Achievement.DIAMONDS_TO_YOU, "diamondsToYou");
         this.put(Achievement.OVERPOWERED, "overpowered");
      }
   };

   public static String toId(Achievement achievement) {
      return (String)achievements.get(achievement);
   }

   private AchievementUtil() {
   }
}
