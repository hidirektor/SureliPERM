package com.infumia.t3sl4.util.itemstack.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum XMaterial {
   ACACIA_BOAT(0, new String[]{"BOAT_ACACIA"}),
   ACACIA_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   ACACIA_DOOR(0, new String[]{"ACACIA_DOOR_ITEM"}),
   ACACIA_FENCE(0, new String[0]),
   ACACIA_FENCE_GATE(0, new String[0]),
   ACACIA_LEAVES(0, new String[]{"LEAVES_2"}),
   ACACIA_LOG(0, new String[]{"LOG_2"}),
   ACACIA_PLANKS(4, new String[]{"WOOD"}),
   ACACIA_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   ACACIA_SAPLING(4, new String[]{"SAPLING"}),
   ACACIA_SIGN(0, new String[]{"SIGN"}),
   ACACIA_SLAB(4, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   ACACIA_STAIRS(0, new String[0]),
   ACACIA_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   ACACIA_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   ACACIA_WOOD(0, new String[]{"LOG_2"}),
   ACTIVATOR_RAIL(0, new String[0]),
   AIR(0, new String[0]),
   ALLIUM(2, new String[]{"RED_ROSE"}),
   ANDESITE(5, new String[]{"STONEZ"}),
   ANDESITE_SLAB(0, new String[0]),
   ANDESITE_STAIRS(0, new String[0]),
   ANDESITE_WALL(0, new String[0]),
   ANVIL(0, new String[0]),
   APPLE(0, new String[0]),
   ARMOR_STAND(0, new String[0]),
   ARROW(0, new String[0]),
   ATTACHED_MELON_STEM(7, new String[]{"MELON_STEM"}),
   ATTACHED_PUMPKIN_STEM(7, new String[]{"PUMPKIN_STEM"}),
   AZURE_BLUET(3, new String[]{"RED_ROSE"}),
   BAKED_POTATO(0, new String[0]),
   BAMBOO(0, new String[]{"1.14", "SUGAR_CANE"}),
   BAMBOO_SAPLING(0, new String[]{"1.14"}),
   BARREL(0, new String[]{"1.14", "CHEST"}),
   BARRIER(0, new String[0]),
   BAT_SPAWN_EGG(65, new String[]{"MONSTER_EGG"}),
   BEACON(0, new String[0]),
   BEDROCK(0, new String[0]),
   BEEF(0, new String[]{"RAW_BEEF"}),
   BEETROOT(0, new String[]{"BEETROOT_BLOCK"}),
   BEETROOTS(0, new String[]{"BEETROOT"}),
   BEETROOT_SEEDS(0, new String[0]),
   BEETROOT_SOUP(0, new String[0]),
   BELL(0, new String[]{"1.14"}),
   BIRCH_BOAT(0, new String[]{"BOAT_BIRCH"}),
   BIRCH_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   BIRCH_DOOR(0, new String[]{"BIRCH_DOOR_ITEM"}),
   BIRCH_FENCE(0, new String[0]),
   BIRCH_FENCE_GATE(0, new String[0]),
   BIRCH_LEAVES(2, new String[]{"LEAVES"}),
   BIRCH_LOG(2, new String[]{"LOG"}),
   BIRCH_PLANKS(2, new String[]{"WOOD"}),
   BIRCH_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   BIRCH_SAPLING(2, new String[]{"SAPLING"}),
   BIRCH_SIGN(0, new String[]{"SIGN"}),
   BIRCH_SLAB(2, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   BIRCH_STAIRS(0, new String[]{"BIRCH_WOOD_STAIRS"}),
   BIRCH_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   BIRCH_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   BIRCH_WOOD(2, new String[]{"LOG"}),
   BLACK_BANNER(0, new String[]{"BANNER", "STANDING_BANNER"}),
   BLACK_BED(15, new String[]{"BED", "BED_BLOCK"}),
   BLACK_CARPET(15, new String[]{"CARPET"}),
   BLACK_CONCRETE(15, new String[]{"CONCRETE"}),
   BLACK_CONCRETE_POWDER(15, new String[]{"CONCRETE_POWDER"}),
   BLACK_DYE(0, new String[]{"1.14", "INK_SACK"}),
   BLACK_GLAZED_TERRACOTTA(15, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "BLACK_TERRACOTTA"}),
   BLACK_SHULKER_BOX(0, new String[0]),
   BLACK_STAINED_GLASS(15, new String[]{"STAINED_GLASS"}),
   BLACK_STAINED_GLASS_PANE(15, new String[]{"STAINED_GLASS_PANE"}),
   BLACK_TERRACOTTA(15, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   BLACK_WALL_BANNER(0, new String[]{"WALL_BANNER"}),
   BLACK_WOOL(15, new String[]{"WOOL"}),
   BLAST_FURNACE(0, new String[]{"1.14", "FURNACE"}),
   BLAZE_POWDER(0, new String[0]),
   BLAZE_ROD(0, new String[0]),
   BLAZE_SPAWN_EGG(61, new String[]{"MONSTER_EGG"}),
   BLUE_BANNER(11, new String[]{"BANNER", "STANDING_BANNER"}),
   BLUE_BED(4, new String[]{"BED", "BED_BLOCK"}),
   BLUE_CARPET(11, new String[]{"CARPET"}),
   BLUE_CONCRETE(11, new String[]{"CONCRETE"}),
   BLUE_CONCRETE_POWDER(11, new String[]{"CONCRETE_POWDER"}),
   BLUE_DYE(4, new String[]{"INK_SACK", "LAPIS_LAZULI"}),
   BLUE_GLAZED_TERRACOTTA(11, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "BLUE_TERRACOTTA"}),
   BLUE_ICE(0, new String[]{"1.13", "PACKED_ICE"}),
   BLUE_ORCHID(1, new String[]{"RED_ROSE"}),
   BLUE_SHULKER_BOX(0, new String[0]),
   BLUE_STAINED_GLASS(11, new String[]{"STAINED_GLASS"}),
   BLUE_STAINED_GLASS_PANE(11, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   BLUE_TERRACOTTA(11, new String[]{"STAINED_CLAY"}),
   BLUE_WALL_BANNER(11, new String[]{"WALL_BANNER"}),
   BLUE_WOOL(11, new String[]{"WOOL"}),
   BONE(0, new String[0]),
   BONE_BLOCK(0, new String[0]),
   BONE_MEAL(15, new String[]{"INK_SACK"}),
   BOOK(0, new String[0]),
   BOOKSHELF(0, new String[0]),
   BOW(0, new String[0]),
   BOWL(0, new String[0]),
   BRAIN_CORAL(0, new String[]{"1.13"}),
   BRAIN_CORAL_BLOCK(0, new String[]{"1.13"}),
   BRAIN_CORAL_FAN(0, new String[]{"1.13"}),
   BRAIN_CORAL_WALL_FAN(0, new String[0]),
   BREAD(0, new String[0]),
   BREWING_STAND(0, new String[]{"BREWING_STAND_ITEM"}),
   BRICK(0, new String[]{"CLAY_BRICK"}),
   BRICKS(0, new String[]{"BRICK"}),
   BRICK_SLAB(4, new String[]{"STEP"}),
   BRICK_STAIRS(0, new String[0]),
   BRICK_WALL(0, new String[0]),
   BROWN_BANNER(3, new String[]{"BANNER", "STANDING_BANNER"}),
   BROWN_BED(12, new String[]{"BED", "BED_BLOCK"}),
   BROWN_DYE(3, new String[]{"INK_SACK", "COCOA", "COCOA_BEANS"}),
   BROWN_CARPET(12, new String[]{"CARPET"}),
   BROWN_CONCRETE(12, new String[]{"CONCRETE"}),
   BROWN_CONCRETE_POWDER(12, new String[]{"CONCRETE_POWDER"}),
   BROWN_GLAZED_TERRACOTTA(12, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "BROWN_TERRACOTTA"}),
   BROWN_MUSHROOM(0, new String[0]),
   BROWN_MUSHROOM_BLOCK(0, new String[]{"BROWN_MUSHROOM", "HUGE_MUSHROOM_1"}),
   BROWN_SHULKER_BOX(0, new String[0]),
   BROWN_STAINED_GLASS(12, new String[]{"STAINED_GLASS"}),
   BROWN_STAINED_GLASS_PANE(12, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   BROWN_TERRACOTTA(12, new String[]{"STAINED_CLAY"}),
   BROWN_WALL_BANNER(3, new String[]{"WALL_BANNER"}),
   BROWN_WOOL(12, new String[]{"WOOL"}),
   BUBBLE_COLUMN(0, new String[]{"1.13"}),
   BUBBLE_CORAL(0, new String[]{"1.13"}),
   BUBBLE_CORAL_BLOCK(0, new String[]{"1.13"}),
   BUBBLE_CORAL_FAN(0, new String[]{"1.13"}),
   BUBBLE_CORAL_WALL_FAN(0, new String[0]),
   BUCKET(0, new String[0]),
   CACTUS(0, new String[0]),
   CAKE(0, new String[]{"CAKE_BLOCK"}),
   CAMPFIRE(0, new String[]{"1.14"}),
   CARROT(0, new String[]{"CARROT_ITEM"}),
   CARROTS(0, new String[]{"CARROT"}),
   CARROT_ON_A_STICK(0, new String[]{"CARROT_STICK"}),
   CARTOGRAPHY_TABLE(0, new String[]{"1.14", "CRAFTING_TABLE"}),
   CARVED_PUMPKIN(1, new String[]{"1.13", "PUMPKIN"}),
   CAT_SPAWN_EGG(0, new String[0]),
   CAULDRON(0, new String[]{"CAULDRON_ITEM"}),
   CAVE_AIR(0, new String[]{"AIR"}),
   CAVE_SPIDER_SPAWN_EGG(59, new String[]{"MONSTER_EGG"}),
   CHAINMAIL_BOOTS(0, new String[0]),
   CHAINMAIL_CHESTPLATE(0, new String[0]),
   CHAINMAIL_HELMET(0, new String[0]),
   CHAINMAIL_LEGGINGS(0, new String[0]),
   CHAIN_COMMAND_BLOCK(0, new String[]{"COMMAND", "COMMAND_CHAIN"}),
   CHARCOAL(1, new String[]{"COAL"}),
   CHEST(0, new String[]{"LOCKED_CHEST"}),
   CHEST_MINECART(0, new String[]{"STORAGE_MINECART"}),
   CHICKEN(0, new String[]{"RAW_CHICKEN"}),
   CHICKEN_SPAWN_EGG(93, new String[]{"MONSTER_EGG"}),
   CHIPPED_ANVIL(1, new String[]{"ANVIL"}),
   CHISELED_QUARTZ_BLOCK(1, new String[]{"QUARTZ_BLOCK"}),
   CHISELED_RED_SANDSTONE(1, new String[]{"RED_SANDSTONE"}),
   CHISELED_SANDSTONE(1, new String[]{"SANDSTONE"}),
   CHISELED_STONE_BRICKS(3, new String[]{"SMOOTH_BRICK"}),
   CHORUS_FLOWER(0, new String[0]),
   CHORUS_FRUIT(0, new String[0]),
   CHORUS_PLANT(0, new String[0]),
   CLAY(0, new String[0]),
   CLAY_BALL(0, new String[0]),
   CLOCK(0, new String[]{"WATCH"}),
   COAL(0, new String[0]),
   COAL_BLOCK(0, new String[0]),
   COAL_ORE(0, new String[0]),
   COARSE_DIRT(1, new String[]{"DIRT"}),
   COBBLESTONE(0, new String[0]),
   COBBLESTONE_SLAB(3, new String[]{"STEP"}),
   COBBLESTONE_STAIRS(0, new String[0]),
   COBBLESTONE_WALL(0, new String[]{"COBBLE_WALL"}),
   COBWEB(0, new String[]{"WEB"}),
   COCOA_BEANS(3, new String[]{"INK_SACK", "COCOA"}),
   COD(0, new String[]{"RAW_FISH"}),
   COD_BUCKET(0, new String[]{"1.13", "BUCKET", "WATER_BUCKET"}),
   COD_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   COMMAND_BLOCK(0, new String[]{"COMMAND"}),
   COMMAND_BLOCK_MINECART(0, new String[]{"COMMAND_MINECART"}),
   COMPARATOR(0, new String[]{"REDSTONE_COMPARATOR", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR_OFF"}),
   COMPASS(0, new String[0]),
   COMPOSTER(0, new String[]{"1.14", "CAULDRON"}),
   CONDUIT(0, new String[]{"1.13"}),
   COOKED_BEEF(0, new String[0]),
   COOKED_CHICKEN(0, new String[0]),
   COOKED_COD(0, new String[]{"COOKED_FISH"}),
   COOKED_MUTTON(0, new String[0]),
   COOKED_PORKCHOP(0, new String[]{"PORK", "GRILLED_PORK"}),
   COOKED_RABBIT(0, new String[0]),
   COOKED_SALMON(1, new String[]{"COOKED_FISH"}),
   COOKIE(0, new String[0]),
   CORNFLOWER(4, new String[]{"1.14", "BLUE_DYE"}),
   COW_SPAWN_EGG(92, new String[]{"MONSTER_EGG"}),
   CRACKED_STONE_BRICKS(2, new String[]{"SMOOTH_BRICK"}),
   CRAFTING_TABLE(0, new String[]{"WORKBENCH"}),
   CREEPER_BANNER_PATTERN(0, new String[0]),
   CREEPER_HEAD(4, new String[]{"SKULL", "SKULL_ITEM"}),
   CREEPER_SPAWN_EGG(50, new String[]{"MONSTER_EGG"}),
   CREEPER_WALL_HEAD(4, new String[]{"SKULL", "SKULL_ITEM"}),
   CROSSBOW(0, new String[0]),
   CUT_RED_SANDSTONE(0, new String[]{"1.13"}),
   CUT_RED_SANDSTONE_SLAB(0, new String[]{"STONE_SLAB2"}),
   CUT_SANDSTONE(0, new String[]{"1.13"}),
   CUT_SANDSTONE_SLAB(0, new String[]{"STEP"}),
   CYAN_BANNER(6, new String[]{"BANNER", "STANDING_BANNER"}),
   CYAN_BED(9, new String[]{"BED", "BED_BLOCK"}),
   CYAN_CARPET(9, new String[]{"CARPET"}),
   CYAN_CONCRETE(9, new String[]{"CONCRETE"}),
   CYAN_CONCRETE_POWDER(9, new String[]{"CONCRETE_POWDER"}),
   CYAN_DYE(6, new String[]{"INK_SACK"}),
   CYAN_GLAZED_TERRACOTTA(9, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "CYAN_TERRACOTTA"}),
   CYAN_SHULKER_BOX(0, new String[0]),
   CYAN_STAINED_GLASS(9, new String[]{"STAINED_GLASS"}),
   CYAN_STAINED_GLASS_PANE(9, new String[]{"STAINED_GLASS_PANE"}),
   CYAN_TERRACOTTA(9, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   CYAN_WALL_BANNER(6, new String[]{"WALL_BANNER"}),
   CYAN_WOOL(9, new String[]{"WOOL"}),
   DAMAGED_ANVIL(2, new String[]{"ANVIL"}),
   DANDELION(0, new String[]{"YELLOW_FLOWER"}),
   DARK_OAK_BOAT(0, new String[]{"BOAT_DARK_OAK"}),
   DARK_OAK_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   DARK_OAK_DOOR(0, new String[]{"DARK_OAK_DOOR_ITEM"}),
   DARK_OAK_FENCE(0, new String[0]),
   DARK_OAK_FENCE_GATE(0, new String[0]),
   DARK_OAK_LEAVES(1, new String[]{"LEAVES", "LEAVES_2"}),
   DARK_OAK_LOG(1, new String[]{"LOG", "LOG_2"}),
   DARK_OAK_PLANKS(5, new String[]{"WOOD"}),
   DARK_OAK_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   DARK_OAK_SAPLING(5, new String[]{"SAPLING"}),
   DARK_OAK_SIGN(0, new String[]{"SIGN"}),
   DARK_OAK_SLAB(0, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   DARK_OAK_STAIRS(0, new String[0]),
   DARK_OAK_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   DARK_OAK_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   DARK_OAK_WOOD(1, new String[]{"LOG", "LOG_2"}),
   DARK_PRISMARINE(1, new String[]{"PRISMARINE"}),
   DARK_PRISMARINE_SLAB(0, new String[]{"1.13"}),
   DARK_PRISMARINE_STAIRS(0, new String[]{"1.13"}),
   DAYLIGHT_DETECTOR(0, new String[]{"DAYLIGHT_DETECTOR_INVERTED"}),
   DEAD_BRAIN_CORAL(0, new String[]{"1.13"}),
   DEAD_BRAIN_CORAL_BLOCK(0, new String[]{"1.13"}),
   DEAD_BRAIN_CORAL_FAN(0, new String[]{"1.13"}),
   DEAD_BRAIN_CORAL_WALL_FAN(0, new String[]{"1.13"}),
   DEAD_BUBBLE_CORAL(0, new String[]{"1.13"}),
   DEAD_BUBBLE_CORAL_BLOCK(0, new String[]{"1.13"}),
   DEAD_BUBBLE_CORAL_FAN(0, new String[]{"1.13"}),
   DEAD_BUBBLE_CORAL_WALL_FAN(0, new String[]{"1.13"}),
   DEAD_BUSH(0, new String[0]),
   DEAD_FIRE_CORAL(0, new String[]{"1.13"}),
   DEAD_FIRE_CORAL_BLOCK(0, new String[]{"1.13"}),
   DEAD_FIRE_CORAL_FAN(0, new String[]{"1.13"}),
   DEAD_FIRE_CORAL_WALL_FAN(0, new String[]{"1.13"}),
   DEAD_HORN_CORAL(0, new String[]{"1.13"}),
   DEAD_HORN_CORAL_BLOCK(0, new String[]{"1.13"}),
   DEAD_HORN_CORAL_FAN(0, new String[]{"1.13"}),
   DEAD_HORN_CORAL_WALL_FAN(0, new String[]{"1.13"}),
   DEAD_TUBE_CORAL(0, new String[]{"1.13"}),
   DEAD_TUBE_CORAL_BLOCK(0, new String[]{"1.13"}),
   DEAD_TUBE_CORAL_FAN(0, new String[]{"1.13"}),
   DEAD_TUBE_CORAL_WALL_FAN(0, new String[]{"1.13"}),
   DEBUG_STICK(0, new String[]{"1.13", "STICK"}),
   DETECTOR_RAIL(0, new String[0]),
   DIAMOND(0, new String[0]),
   DIAMOND_AXE(0, new String[0]),
   DIAMOND_BLOCK(0, new String[0]),
   DIAMOND_BOOTS(0, new String[0]),
   DIAMOND_CHESTPLATE(0, new String[0]),
   DIAMOND_HELMET(0, new String[0]),
   DIAMOND_HOE(0, new String[0]),
   DIAMOND_HORSE_ARMOR(0, new String[]{"DIAMOND_BARDING"}),
   DIAMOND_LEGGINGS(0, new String[0]),
   DIAMOND_ORE(0, new String[0]),
   DIAMOND_PICKAXE(0, new String[0]),
   DIAMOND_SHOVEL(0, new String[]{"DIAMOND_SPADE"}),
   DIAMOND_SWORD(0, new String[0]),
   DIORITE(3, new String[]{"1.13"}),
   DIORITE_SLAB(0, new String[0]),
   DIORITE_STAIRS(0, new String[0]),
   DIORITE_WALL(0, new String[0]),
   DIRT(0, new String[0]),
   DISPENSER(0, new String[0]),
   DOLPHIN_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   DONKEY_SPAWN_EGG(32, new String[]{"MONSTER_EGG"}),
   DRAGON_BREATH(0, new String[]{"DRAGONS_BREATH"}),
   DRAGON_EGG(0, new String[0]),
   DRAGON_HEAD(5, new String[]{"SKULL", "SKULL_ITEM"}),
   DRAGON_WALL_HEAD(5, new String[]{"SKULL", "SKULL_ITEM"}),
   DRIED_KELP(0, new String[]{"1.13"}),
   DRIED_KELP_BLOCK(0, new String[]{"1.13"}),
   DROPPER(0, new String[0]),
   DROWNED_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   EGG(0, new String[0]),
   ELDER_GUARDIAN_SPAWN_EGG(4, new String[]{"MONSTER_EGG"}),
   ELYTRA(0, new String[0]),
   EMERALD(0, new String[0]),
   EMERALD_BLOCK(0, new String[0]),
   EMERALD_ORE(0, new String[0]),
   ENCHANTED_BOOK(0, new String[0]),
   ENCHANTED_GOLDEN_APPLE(1, new String[]{"GOLDEN_APPLE"}),
   ENCHANTING_TABLE(0, new String[]{"ENCHANTMENT_TABLE"}),
   ENDERMAN_SPAWN_EGG(58, new String[]{"MONSTER_EGG"}),
   ENDERMITE_SPAWN_EGG(67, new String[]{"MONSTER_EGG"}),
   ENDER_CHEST(0, new String[0]),
   ENDER_EYE(0, new String[]{"EYE_OF_ENDER"}),
   ENDER_PEARL(0, new String[0]),
   END_CRYSTAL(0, new String[0]),
   END_GATEWAY(0, new String[0]),
   END_PORTAL(0, new String[]{"ENDER_PORTAL"}),
   END_PORTAL_FRAME(0, new String[]{"ENDER_PORTAL_FRAME"}),
   END_ROD(0, new String[0]),
   END_STONE(0, new String[]{"ENDER_STONE"}),
   END_STONE_BRICKS(0, new String[]{"END_BRICKS"}),
   END_STONE_BRICK_SLAB(4, new String[]{"STEP"}),
   END_STONE_BRICK_STAIRS(0, new String[]{"SMOOTH_STAIRS"}),
   END_STONE_BRICK_WALL(0, new String[0]),
   EVOKER_SPAWN_EGG(34, new String[]{"MONSTER_EGG"}),
   EXPERIENCE_BOTTLE(0, new String[]{"EXP_BOTTLE"}),
   FARMLAND(0, new String[]{"SOIL"}),
   FEATHER(0, new String[0]),
   FERMENTED_SPIDER_EYE(0, new String[0]),
   FERN(2, new String[]{"LONG_GRASS"}),
   FILLED_MAP(0, new String[]{"MAP"}),
   FIRE(0, new String[0]),
   FIREWORK_ROCKET(0, new String[]{"FIREWORK"}),
   FIREWORK_STAR(0, new String[]{"FIREWORK_CHARGE"}),
   FIRE_CHARGE(0, new String[]{"FIREBALL"}),
   FIRE_CORAL(0, new String[]{"1.13"}),
   FIRE_CORAL_BLOCK(0, new String[]{"1.13"}),
   FIRE_CORAL_FAN(0, new String[]{"1.13"}),
   FIRE_CORAL_WALL_FAN(0, new String[0]),
   FISHING_ROD(0, new String[0]),
   FLETCHING_TABLE(0, new String[]{"1.14", "CRAFTING_TABLE"}),
   FLINT(0, new String[0]),
   FLINT_AND_STEEL(0, new String[0]),
   FLOWER_BANNER_PATTERN(0, new String[0]),
   FLOWER_POT(0, new String[]{"FLOWER_POT_ITEM"}),
   FOX_SPAWN_EGG(0, new String[]{"1.14"}),
   FROSTED_ICE(0, new String[0]),
   FURNACE(0, new String[]{"BURNING_FURNACE"}),
   FURNACE_MINECART(0, new String[]{"POWERED_MINECART"}),
   GHAST_SPAWN_EGG(56, new String[]{"MONSTER_EGG"}),
   GHAST_TEAR(0, new String[0]),
   GLASS(0, new String[0]),
   GLASS_BOTTLE(0, new String[0]),
   GLASS_PANE(0, new String[]{"THIN_GLASS"}),
   GLISTERING_MELON_SLICE(0, new String[]{"SPECKLED_MELON"}),
   GLOBE_BANNER_PATTERN(0, new String[0]),
   GLOWSTONE(0, new String[0]),
   GLOWSTONE_DUST(0, new String[0]),
   GOLDEN_APPLE(0, new String[0]),
   GOLDEN_AXE(0, new String[]{"GOLD_AXE"}),
   GOLDEN_BOOTS(0, new String[]{"GOLD_BOOTS"}),
   GOLDEN_CARROT(0, new String[0]),
   GOLDEN_CHESTPLATE(0, new String[]{"GOLD_CHESTPLATE"}),
   GOLDEN_HELMET(0, new String[]{"GOLD_HELMET"}),
   GOLDEN_HOE(0, new String[]{"GOLD_HOE"}),
   GOLDEN_HORSE_ARMOR(0, new String[]{"GOLD_BARDING"}),
   GOLDEN_LEGGINGS(0, new String[]{"GOLD_LEGGINGS"}),
   GOLDEN_PICKAXE(0, new String[]{"GOLD_PICKAXE"}),
   GOLDEN_SHOVEL(0, new String[]{"GOLD_SPADE"}),
   GOLDEN_SWORD(0, new String[]{"GOLD_SWORD"}),
   GOLD_BLOCK(0, new String[0]),
   GOLD_INGOT(0, new String[0]),
   GOLD_NUGGET(0, new String[0]),
   GOLD_ORE(0, new String[0]),
   GRANITE(1, new String[]{"1.13"}),
   GRANITE_SLAB(0, new String[0]),
   GRANITE_STAIRS(0, new String[0]),
   GRANITE_WALL(0, new String[0]),
   GRASS(0, new String[0]),
   GRASS_BLOCK(0, new String[]{"GRASS"}),
   GRASS_PATH(0, new String[0]),
   GRAVEL(0, new String[0]),
   GRAY_BANNER(8, new String[]{"BANNER", "STANDING_BANNER"}),
   GRAY_BED(7, new String[]{"BED", "BED_BLOCK"}),
   GRAY_CARPET(7, new String[]{"CARPET"}),
   GRAY_CONCRETE(7, new String[]{"CONCRETE"}),
   GRAY_CONCRETE_POWDER(7, new String[]{"CONCRETE_POWDER"}),
   GRAY_DYE(8, new String[]{"INK_SACK"}),
   GRAY_GLAZED_TERRACOTTA(7, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "GRAY_TERRACOTTA"}),
   GRAY_SHULKER_BOX(0, new String[0]),
   GRAY_STAINED_GLASS(8, new String[]{"STAINED_GLASS"}),
   GRAY_STAINED_GLASS_PANE(7, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   GRAY_TERRACOTTA(7, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   GRAY_WALL_BANNER(8, new String[]{"WALL_BANNER"}),
   GRAY_WOOL(8, new String[]{"WOOL"}),
   GREEN_BANNER(2, new String[]{"BANNER", "STANDING_BANNER"}),
   GREEN_BED(13, new String[]{"BED", "BED_BLOCK"}),
   GREEN_CARPET(13, new String[]{"CARPET"}),
   GREEN_CONCRETE(13, new String[]{"CONCRETE"}),
   GREEN_CONCRETE_POWDER(13, new String[]{"CONCRETE_POWDER"}),
   GREEN_DYE(2, new String[]{"INK_SACK", "CACTUS_GREEN"}),
   GREEN_GLAZED_TERRACOTTA(13, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "GREEN_TERRACOTTA"}),
   GREEN_SHULKER_BOX(0, new String[0]),
   GREEN_STAINED_GLASS(13, new String[]{"STAINED_GLASS"}),
   GREEN_STAINED_GLASS_PANE(13, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   GREEN_TERRACOTTA(13, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   GREEN_WALL_BANNER(2, new String[]{"WALL_BANNER"}),
   GREEN_WOOL(13, new String[]{"WOOL"}),
   GRINDSTONE(0, new String[]{"1.14", "ANVIL"}),
   GUARDIAN_SPAWN_EGG(68, new String[]{"MONSTER_EGG"}),
   GUNPOWDER(0, new String[]{"SULPHUR"}),
   HAY_BLOCK(0, new String[0]),
   HEART_OF_THE_SEA(0, new String[]{"1.13"}),
   HEAVY_WEIGHTED_PRESSURE_PLATE(0, new String[]{"IRON_PLATE"}),
   HOPPER(0, new String[0]),
   HOPPER_MINECART(0, new String[0]),
   HORN_CORAL(0, new String[]{"1.13"}),
   HORN_CORAL_BLOCK(0, new String[]{"1.13"}),
   HORN_CORAL_FAN(0, new String[]{"1.13"}),
   HORN_CORAL_WALL_FAN(0, new String[0]),
   HORSE_SPAWN_EGG(100, new String[]{"MONSTER_EGG"}),
   HUSK_SPAWN_EGG(23, new String[]{"MONSTER_EGG"}),
   ICE(0, new String[0]),
   INFESTED_CHISELED_STONE_BRICKS(5, new String[]{"MONSTER_EGGS", "SMOOTH_BRICK"}),
   INFESTED_COBBLESTONE(1, new String[]{"MONSTER_EGGS"}),
   INFESTED_CRACKED_STONE_BRICKS(4, new String[]{"MONSTER_EGGS", "SMOOTH_BRICK"}),
   INFESTED_MOSSY_STONE_BRICKS(3, new String[]{"MONSTER_EGGS"}),
   INFESTED_STONE(0, new String[]{"MONSTER_EGGS"}),
   INFESTED_STONE_BRICKS(2, new String[]{"MONSTER_EGGS", "SMOOTH_BRICK"}),
   INK_SAC(0, new String[]{"INK_SACK"}),
   IRON_AXE(0, new String[0]),
   IRON_BARS(0, new String[]{"IRON_FENCE"}),
   IRON_BLOCK(0, new String[0]),
   IRON_BOOTS(0, new String[0]),
   IRON_CHESTPLATE(0, new String[0]),
   IRON_DOOR(0, new String[]{"IRON_DOOR_BLOCK"}),
   IRON_HELMET(0, new String[0]),
   IRON_HOE(0, new String[0]),
   IRON_HORSE_ARMOR(0, new String[]{"IRON_BARDING"}),
   IRON_INGOT(0, new String[0]),
   IRON_LEGGINGS(0, new String[0]),
   IRON_NUGGET(0, new String[0]),
   IRON_ORE(0, new String[0]),
   IRON_PICKAXE(0, new String[0]),
   IRON_SHOVEL(0, new String[]{"IRON_SPADE"}),
   IRON_SWORD(0, new String[0]),
   IRON_TRAPDOOR(0, new String[0]),
   ITEM_FRAME(0, new String[0]),
   JACK_O_LANTERN(0, new String[0]),
   JIGSAW(0, new String[]{"1.14", "COMMAND_BLOCK", "STRUCTURE_BLOCK"}),
   JUKEBOX(0, new String[0]),
   JUNGLE_BOAT(0, new String[]{"BOAT_JUNGLE"}),
   JUNGLE_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   JUNGLE_DOOR(0, new String[]{"JUNGLE_DOOR_ITEM"}),
   JUNGLE_FENCE(0, new String[0]),
   JUNGLE_FENCE_GATE(0, new String[0]),
   JUNGLE_LEAVES(3, new String[]{"LEAVES"}),
   JUNGLE_LOG(3, new String[]{"LOG"}),
   JUNGLE_PLANKS(3, new String[]{"WOOD"}),
   JUNGLE_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   JUNGLE_SAPLING(3, new String[]{"SAPLING"}),
   JUNGLE_SIGN(0, new String[]{"SIGN"}),
   JUNGLE_SLAB(3, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   JUNGLE_STAIRS(0, new String[]{"JUNGLE_WOOD_STAIRS"}),
   JUNGLE_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   JUNGLE_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   JUNGLE_WOOD(3, new String[]{"LOG"}),
   KELP(0, new String[]{"1.13"}),
   KELP_PLANT(0, new String[]{"1.13"}),
   KNOWLEDGE_BOOK(0, new String[0]),
   LADDER(0, new String[0]),
   LANTERN(0, new String[]{"1.14", "SEA_LANTERN"}),
   LAPIS_BLOCK(0, new String[0]),
   LAPIS_LAZULI(4, new String[]{"INK_SACK"}),
   LAPIS_ORE(0, new String[0]),
   LARGE_FERN(3, new String[]{"DOUBLE_PLANT"}),
   LAVA(0, new String[]{"STATIONARY_LAVA"}),
   LAVA_BUCKET(0, new String[0]),
   LEAD(0, new String[]{"LEASH"}),
   LEATHER(0, new String[0]),
   LEATHER_BOOTS(0, new String[0]),
   LEATHER_CHESTPLATE(0, new String[0]),
   LEATHER_HELMET(0, new String[0]),
   LEATHER_HORSE_ARMOR(0, new String[]{"1.14", "IRON_HORSE_ARMOR"}),
   LEATHER_LEGGINGS(0, new String[0]),
   LECTERN(0, new String[]{"1.14", "BOOKSHELF"}),
   LEVER(0, new String[0]),
   LIGHT_BLUE_BANNER(11, new String[]{"BANNER", "STANDING_BANNER"}),
   LIGHT_BLUE_BED(3, new String[]{"BED", "BED_BLOCK"}),
   LIGHT_BLUE_CARPET(3, new String[]{"CARPET"}),
   LIGHT_BLUE_CONCRETE(3, new String[]{"CONCRETE"}),
   LIGHT_BLUE_CONCRETE_POWDER(3, new String[]{"CONCRETE_POWDER"}),
   LIGHT_BLUE_DYE(12, new String[]{"INK_SACK"}),
   LIGHT_BLUE_GLAZED_TERRACOTTA(3, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "LIGHT_BLUE_TERRACOTTA"}),
   LIGHT_BLUE_SHULKER_BOX(0, new String[0]),
   LIGHT_BLUE_STAINED_GLASS(11, new String[]{"STAINED_GLASS"}),
   LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   LIGHT_BLUE_TERRACOTTA(3, new String[]{"STAINED_CLAY"}),
   LIGHT_BLUE_WALL_BANNER(12, new String[]{"WALL_BANNER", "BANNER", "STANDING_BANNER"}),
   LIGHT_BLUE_WOOL(11, new String[]{"WOOL"}),
   LIGHT_GRAY_BANNER(7, new String[]{"BANNER", "STANDING_BANNER"}),
   LIGHT_GRAY_BED(7, new String[]{"BED", "BED_BLOCK"}),
   LIGHT_GRAY_CARPET(8, new String[]{"CARPET"}),
   LIGHT_GRAY_CONCRETE(8, new String[]{"CONCRETE"}),
   LIGHT_GRAY_CONCRETE_POWDER(8, new String[]{"CONCRETE_POWDER"}),
   LIGHT_GRAY_DYE(7, new String[]{"INK_SACK"}),
   LIGHT_GRAY_GLAZED_TERRACOTTA(8, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "LIGHT_GRAY_TERRACOTTA", "SILVER_GLAZED_TERRACOTTA/1.13"}),
   LIGHT_GRAY_SHULKER_BOX(0, new String[]{"SILVER_SHULKER_BOX"}),
   LIGHT_GRAY_STAINED_GLASS(8, new String[]{"STAINED_GLASS"}),
   LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   LIGHT_GRAY_TERRACOTTA(8, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   LIGHT_GRAY_WALL_BANNER(7, new String[]{"WALL_BANNER"}),
   LIGHT_GRAY_WOOL(8, new String[]{"WOOL"}),
   LIGHT_WEIGHTED_PRESSURE_PLATE(0, new String[]{"GOLD_PLATE"}),
   LILAC(1, new String[]{"DOUBLE_PLANT"}),
   LILY_OF_THE_VALLEY(15, new String[]{"1.14", "WHITE_DYE"}),
   LILY_PAD(0, new String[]{"WATER_LILY"}),
   LIME_BANNER(10, new String[]{"BANNER", "STANDING_BANNER"}),
   LIME_BED(5, new String[]{"BED", "BED_BLOCK"}),
   LIME_CARPET(5, new String[]{"CARPET"}),
   LIME_CONCRETE(5, new String[]{"CONCRETE"}),
   LIME_CONCRETE_POWDER(5, new String[]{"CONCRETE_POWDER"}),
   LIME_DYE(10, new String[]{"INK_SACK"}),
   LIME_GLAZED_TERRACOTTA(5, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "LIME_TERRACOTTA"}),
   LIME_SHULKER_BOX(0, new String[0]),
   LIME_STAINED_GLASS(5, new String[]{"STAINED_GLASS"}),
   LIME_STAINED_GLASS_PANE(5, new String[]{"STAINED_GLASS_PANE"}),
   LIME_TERRACOTTA(5, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   LIME_WALL_BANNER(10, new String[]{"WALL_BANNER"}),
   LIME_WOOL(5, new String[]{"WOOL"}),
   LINGERING_POTION(0, new String[0]),
   LLAMA_SPAWN_EGG(103, new String[]{"MONSTER_EGG"}),
   LOOM(0, new String[]{"1.14"}),
   MAGENTA_BANNER(13, new String[]{"BANNER", "STANDING_BANNER"}),
   MAGENTA_BED(2, new String[]{"BED", "BED_BLOCK"}),
   MAGENTA_CARPET(2, new String[]{"CARPET"}),
   MAGENTA_CONCRETE(2, new String[]{"CONCRETE"}),
   MAGENTA_CONCRETE_POWDER(2, new String[]{"CONCRETE_POWDER"}),
   MAGENTA_DYE(13, new String[]{"INK_SACK"}),
   MAGENTA_GLAZED_TERRACOTTA(2, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "MAGENTA_TERRACOTTA"}),
   MAGENTA_SHULKER_BOX(0, new String[0]),
   MAGENTA_STAINED_GLASS(2, new String[]{"STAINED_GLASS"}),
   MAGENTA_STAINED_GLASS_PANE(2, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   MAGENTA_TERRACOTTA(2, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   MAGENTA_WALL_BANNER(13, new String[]{"WALL_BANNER"}),
   MAGENTA_WOOL(2, new String[]{"WOOL"}),
   MAGMA_BLOCK(0, new String[]{"MAGMA"}),
   MAGMA_CREAM(0, new String[0]),
   MAGMA_CUBE_SPAWN_EGG(62, new String[]{"MONSTER_EGG"}),
   MAP(0, new String[]{"EMPTY_MAP"}),
   MELON(0, new String[]{"MELON_BLOCK"}),
   MELON_SEEDS(0, new String[0]),
   MELON_SLICE(0, new String[]{"MELON"}),
   MELON_STEM(0, new String[0]),
   MILK_BUCKET(0, new String[0]),
   MINECART(0, new String[0]),
   MOJANG_BANNER_PATTERN(0, new String[0]),
   MOOSHROOM_SPAWN_EGG(96, new String[]{"MONSTER_EGG"}),
   MOSSY_COBBLESTONE(0, new String[0]),
   MOSSY_COBBLESTONE_SLAB(3, new String[]{"STEP"}),
   MOSSY_COBBLESTONE_STAIRS(0, new String[0]),
   MOSSY_COBBLESTONE_WALL(1, new String[]{"COBBLE_WALL", "COBBLESTONE_WALL"}),
   MOSSY_STONE_BRICKS(1, new String[]{"SMOOTH_BRICK"}),
   MOSSY_STONE_BRICK_SLAB(4, new String[]{"STEP"}),
   MOSSY_STONE_BRICK_STAIRS(0, new String[]{"SMOOTH_STAIRS"}),
   MOSSY_STONE_BRICK_WALL(0, new String[0]),
   MOVING_PISTON(0, new String[]{"PISTON_BASE", "PISTON_MOVING_PIECE"}),
   MULE_SPAWN_EGG(32, new String[]{"MONSTER_EGG"}),
   MUSHROOM_STEM(0, new String[]{"BROWN_MUSHROOM"}),
   MUSHROOM_STEW(0, new String[]{"MUSHROOM_SOUP"}),
   MUSIC_DISC_11(0, new String[]{"GOLD_RECORD"}),
   MUSIC_DISC_13(0, new String[]{"GREEN_RECORD"}),
   MUSIC_DISC_BLOCKS(0, new String[]{"RECORD_3"}),
   MUSIC_DISC_CAT(0, new String[]{"RECORD_4"}),
   MUSIC_DISC_CHIRP(0, new String[]{"RECORD_5"}),
   MUSIC_DISC_FAR(0, new String[]{"RECORD_6"}),
   MUSIC_DISC_MALL(0, new String[]{"RECORD_7"}),
   MUSIC_DISC_MELLOHI(0, new String[]{"RECORD_8"}),
   MUSIC_DISC_STAL(0, new String[]{"RECORD_9"}),
   MUSIC_DISC_STRAD(0, new String[]{"RECORD_10"}),
   MUSIC_DISC_WAIT(0, new String[]{"RECORD_11"}),
   MUSIC_DISC_WARD(0, new String[]{"RECORD_12"}),
   MUTTON(0, new String[0]),
   MYCELIUM(0, new String[]{"MYCEL"}),
   NAME_TAG(0, new String[0]),
   NAUTILUS_SHELL(0, new String[]{"1.13"}),
   NETHERRACK(0, new String[0]),
   NETHER_BRICK(0, new String[]{"NETHER_BRICK_ITEM"}),
   NETHER_BRICKS(0, new String[]{"NETHER_BRICK"}),
   NETHER_BRICK_FENCE(0, new String[]{"NETHER_FENCE"}),
   NETHER_BRICK_SLAB(4, new String[]{"STEP"}),
   NETHER_BRICK_STAIRS(0, new String[0]),
   NETHER_BRICK_WALL(0, new String[0]),
   NETHER_PORTAL(0, new String[]{"PORTAL"}),
   NETHER_QUARTZ_ORE(0, new String[]{"QUARTZ_ORE"}),
   NETHER_STAR(0, new String[0]),
   NETHER_WART(0, new String[]{"NETHER_STALK"}),
   NETHER_WART_BLOCK(0, new String[]{"NETHER_WARTS"}),
   NOTE_BLOCK(0, new String[0]),
   OAK_BOAT(0, new String[]{"BOAT"}),
   OAK_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   OAK_DOOR(0, new String[]{"WOOD_DOOR", "WOODEN_DOOR"}),
   OAK_FENCE(0, new String[]{"FENCE"}),
   OAK_FENCE_GATE(0, new String[]{"FENCE_GATE"}),
   OAK_LEAVES(0, new String[]{"LEAVES"}),
   OAK_LOG(0, new String[]{"LOG"}),
   OAK_PLANKS(0, new String[]{"WOOD"}),
   OAK_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   OAK_SAPLING(0, new String[]{"SAPLING"}),
   OAK_SIGN(0, new String[]{"SIGN"}),
   OAK_SLAB(0, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   OAK_STAIRS(0, new String[]{"WOOD_STAIRS"}),
   OAK_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   OAK_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   OAK_WOOD(0, new String[]{"LOG"}),
   OBSERVER(0, new String[0]),
   OBSIDIAN(0, new String[0]),
   OCELOT_SPAWN_EGG(98, new String[]{"MONSTER_EGG"}),
   ORANGE_BANNER(14, new String[]{"BANNER", "STANDING_BANNER"}),
   ORANGE_BED(1, new String[]{"BED", "BED_BLOCK"}),
   ORANGE_CARPET(1, new String[]{"CARPET"}),
   ORANGE_CONCRETE(1, new String[]{"CONCRETE"}),
   ORANGE_CONCRETE_POWDER(1, new String[]{"CONCRETE_POWDER"}),
   ORANGE_DYE(14, new String[]{"INK_SACK"}),
   ORANGE_GLAZED_TERRACOTTA(1, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "ORANGE_TERRACOTTA"}),
   ORANGE_SHULKER_BOX(0, new String[0]),
   ORANGE_STAINED_GLASS(1, new String[]{"STAINED_GLASS"}),
   ORANGE_STAINED_GLASS_PANE(1, new String[]{"STAINED_GLASS_PANE"}),
   ORANGE_TERRACOTTA(1, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   ORANGE_TULIP(5, new String[]{"RED_ROSE"}),
   ORANGE_WALL_BANNER(14, new String[]{"WALL_BANNER"}),
   ORANGE_WOOL(1, new String[]{"WOOL"}),
   OXEYE_DAISY(8, new String[]{"RED_ROSE"}),
   PACKED_ICE(0, new String[0]),
   PAINTING(0, new String[0]),
   PANDA_SPAWN_EGG(0, new String[]{"1.14"}),
   PAPER(0, new String[0]),
   PARROT_SPAWN_EGG(105, new String[]{"MONSTER_EGG"}),
   PEONY(5, new String[]{"DOUBLE_PLANT"}),
   PETRIFIED_OAK_SLAB(0, new String[]{"WOOD_STEP"}),
   PHANTOM_MEMBRANE(0, new String[]{"1.13"}),
   PHANTOM_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   PIG_SPAWN_EGG(90, new String[]{"MONSTER_EGG"}),
   PILLAGER_SPAWN_EGG(0, new String[]{"1.14"}),
   PINK_BANNER(9, new String[]{"BANNER", "STANDING_BANNER"}),
   PINK_BED(6, new String[]{"BED", "BED_BLOCK"}),
   PINK_CARPET(6, new String[]{"CARPET"}),
   PINK_CONCRETE(6, new String[]{"CONCRETE"}),
   PINK_CONCRETE_POWDER(6, new String[]{"CONCRETE_POWDER"}),
   PINK_DYE(9, new String[]{"INK_SACK"}),
   PINK_GLAZED_TERRACOTTA(6, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "PINK_TERRACOTTA"}),
   PINK_SHULKER_BOX(0, new String[0]),
   PINK_STAINED_GLASS(6, new String[]{"STAINED_GLASS"}),
   PINK_STAINED_GLASS_PANE(6, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   PINK_TERRACOTTA(6, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   PINK_TULIP(7, new String[]{"RED_ROSE"}),
   PINK_WALL_BANNER(14, new String[]{"WALL_BANNER"}),
   PINK_WOOL(6, new String[]{"WOOL"}),
   PISTON(0, new String[]{"PISTON_BASE"}),
   PISTON_HEAD(0, new String[]{"PISTON_EXTENSION"}),
   PLAYER_HEAD(3, new String[]{"SKULL", "SKULL_ITEM"}),
   PLAYER_WALL_HEAD(3, new String[]{"SKULL", "SKULL_ITEM"}),
   PODZOL(2, new String[]{"DIRT"}),
   POISONOUS_POTATO(0, new String[0]),
   POLAR_BEAR_SPAWN_EGG(102, new String[]{"MONSTER_EGG"}),
   POLISHED_ANDESITE(6, new String[]{"1.13"}),
   POLISHED_ANDESITE_SLAB(0, new String[0]),
   POLISHED_ANDESITE_STAIRS(0, new String[0]),
   POLISHED_DIORITE(4, new String[]{"1.13"}),
   POLISHED_DIORITE_SLAB(0, new String[0]),
   POLISHED_DIORITE_STAIRS(0, new String[0]),
   POLISHED_GRANITE(2, new String[]{"1.13"}),
   POLISHED_GRANITE_SLAB(0, new String[0]),
   POLISHED_GRANITE_STAIRS(0, new String[0]),
   POPPED_CHORUS_FRUIT(0, new String[]{"CHORUS_FRUIT_POPPED"}),
   POPPY(0, new String[]{"RED_ROSE"}),
   PORKCHOP(0, new String[]{"PORK"}),
   POTATO(0, new String[]{"POTATO_ITEM"}),
   POTATOES(0, new String[]{"POTATO"}),
   POTION(0, new String[0]),
   POTTED_ACACIA_SAPLING(4, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_ALLIUM(2, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_AZURE_BLUET(3, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_BAMBOO(0, new String[0]),
   POTTED_BIRCH_SAPLING(2, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_BLUE_ORCHID(1, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_BROWN_MUSHROOM(0, new String[]{"FLOWER_POT"}),
   POTTED_CACTUS(0, new String[]{"FLOWER_POT"}),
   POTTED_CORNFLOWER(0, new String[0]),
   POTTED_DANDELION(0, new String[]{"YELLOW_FLOWER", "FLOWER_POT"}),
   POTTED_DARK_OAK_SAPLING(5, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_DEAD_BUSH(0, new String[]{"FLOWER_POT"}),
   POTTED_FERN(2, new String[]{"LONG_GRASS", "FLOWER_POT"}),
   POTTED_JUNGLE_SAPLING(3, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_LILY_OF_THE_VALLEY(0, new String[0]),
   POTTED_OAK_SAPLING(0, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_ORANGE_TULIP(5, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_OXEYE_DAISY(8, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_PINK_TULIP(7, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_POPPY(0, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_RED_MUSHROOM(0, new String[]{"FLOWER_POT"}),
   POTTED_RED_TULIP(4, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_SPRUCE_SAPLING(1, new String[]{"SAPLING", "FLOWER_POT"}),
   POTTED_WHITE_TULIP(6, new String[]{"RED_ROSE", "FLOWER_POT"}),
   POTTED_WITHER_ROSE(0, new String[0]),
   POWERED_RAIL(0, new String[0]),
   PRISMARINE(0, new String[0]),
   PRISMARINE_BRICKS(2, new String[]{"PRISMARINE"}),
   PRISMARINE_BRICK_SLAB(4, new String[]{"STEP"}),
   PRISMARINE_BRICK_STAIRS(0, new String[]{"1.13"}),
   PRISMARINE_CRYSTALS(0, new String[0]),
   PRISMARINE_SHARD(0, new String[0]),
   PRISMARINE_SLAB(0, new String[]{"1.13"}),
   PRISMARINE_STAIRS(0, new String[]{"1.13"}),
   PRISMARINE_WALL(0, new String[0]),
   PUFFERFISH(3, new String[]{"RAW_FISH"}),
   PUFFERFISH_BUCKET(0, new String[]{"1.13", "BUCKET", "WATER_BUCKET"}),
   PUFFERFISH_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   PUMPKIN(0, new String[0]),
   PUMPKIN_PIE(0, new String[0]),
   PUMPKIN_SEEDS(0, new String[0]),
   PUMPKIN_STEM(0, new String[0]),
   PURPLE_BANNER(5, new String[]{"BANNER", "STANDING_BANNER"}),
   PURPLE_BED(10, new String[]{"BED", "BED_BLOCK"}),
   PURPLE_CARPET(10, new String[]{"CARPET"}),
   PURPLE_CONCRETE(10, new String[]{"CONCRETE"}),
   PURPLE_CONCRETE_POWDER(10, new String[]{"CONCRETE_POWDER"}),
   PURPLE_DYE(5, new String[]{"INK_SACK"}),
   PURPLE_GLAZED_TERRACOTTA(10, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "PURPLE_TERRACOTTA"}),
   PURPLE_SHULKER_BOX(0, new String[0]),
   PURPLE_STAINED_GLASS(10, new String[]{"STAINED_GLASS"}),
   PURPLE_STAINED_GLASS_PANE(10, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   PURPLE_TERRACOTTA(10, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   PURPLE_WALL_BANNER(5, new String[]{"WALL_BANNER"}),
   PURPLE_WOOL(10, new String[]{"WOOL"}),
   PURPUR_BLOCK(0, new String[0]),
   PURPUR_PILLAR(0, new String[0]),
   PURPUR_SLAB(0, new String[]{"PURPUR_DOUBLE_SLAB"}),
   PURPUR_STAIRS(0, new String[0]),
   QUARTZ(0, new String[0]),
   QUARTZ_BLOCK(0, new String[0]),
   QUARTZ_PILLAR(2, new String[]{"QUARTZ_BLOCK"}),
   QUARTZ_SLAB(7, new String[]{"STEP"}),
   QUARTZ_STAIRS(0, new String[0]),
   RABBIT(0, new String[0]),
   RABBIT_FOOT(0, new String[0]),
   RABBIT_HIDE(0, new String[0]),
   RABBIT_SPAWN_EGG(101, new String[]{"MONSTER_EGG"}),
   RABBIT_STEW(0, new String[0]),
   RAIL(0, new String[]{"RAILS"}),
   RAVAGER_SPAWN_EGG(0, new String[]{"1.14"}),
   REDSTONE(0, new String[0]),
   REDSTONE_BLOCK(0, new String[0]),
   REDSTONE_LAMP(0, new String[]{"REDSTONE_LAMP_OFF", "REDSTONE_LAMP_ON"}),
   REDSTONE_ORE(0, new String[]{"GLOWING_REDSTONE_ORE"}),
   REDSTONE_TORCH(0, new String[]{"REDSTONE_TORCH_ON", "REDSTONE_TORCH_OFF"}),
   REDSTONE_WALL_TORCH(1, new String[]{"REDSTONE_TORCH_ON", "REDSTONE_TORCH_OFF"}),
   REDSTONE_WIRE(0, new String[0]),
   RED_BANNER(1, new String[]{"BANNER", "STANDING_BANNER"}),
   RED_BED(14, new String[]{"BED", "BED_BLOCK"}),
   RED_CARPET(14, new String[]{"CARPET"}),
   RED_CONCRETE(14, new String[]{"CONCRETE"}),
   RED_CONCRETE_POWDER(14, new String[]{"CONCRETE_POWDER"}),
   RED_DYE(1, new String[]{"ROSE_RED"}),
   RED_GLAZED_TERRACOTTA(14, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "RED_TERRACOTTA"}),
   RED_MUSHROOM(0, new String[0]),
   RED_MUSHROOM_BLOCK(0, new String[]{"RED_MUSHROOM", "HUGE_MUSHROOM_2"}),
   RED_NETHER_BRICKS(0, new String[]{"RED_NETHER_BRICK"}),
   RED_NETHER_BRICK_SLAB(4, new String[]{"STEP"}),
   RED_NETHER_BRICK_STAIRS(0, new String[0]),
   RED_NETHER_BRICK_WALL(0, new String[0]),
   RED_SAND(1, new String[]{"SAND"}),
   RED_SANDSTONE(0, new String[0]),
   RED_SANDSTONE_SLAB(0, new String[]{"STONE_SLAB2", "DOUBLE_STONE_SLAB2"}),
   RED_SANDSTONE_STAIRS(0, new String[0]),
   RED_SANDSTONE_WALL(0, new String[0]),
   RED_SHULKER_BOX(0, new String[0]),
   RED_STAINED_GLASS(14, new String[]{"STAINED_GLASS"}),
   RED_STAINED_GLASS_PANE(14, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   RED_TERRACOTTA(14, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   RED_TULIP(4, new String[]{"RED_ROSE"}),
   RED_WALL_BANNER(1, new String[]{"WALL_BANNER"}),
   RED_WOOL(14, new String[]{"WOOL"}),
   REPEATER(0, new String[]{"DIODE", "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF"}),
   REPEATING_COMMAND_BLOCK(0, new String[]{"COMMAND", "COMMAND_REPEATING"}),
   ROSE_BUSH(4, new String[]{"DOUBLE_PLANT"}),
   ROTTEN_FLESH(0, new String[0]),
   SADDLE(0, new String[0]),
   SALMON(1, new String[]{"RAW_FISH"}),
   SALMON_BUCKET(0, new String[]{"1.13", "BUCKET", "WATER_BUCKET"}),
   SALMON_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   SAND(0, new String[0]),
   SANDSTONE(0, new String[0]),
   SANDSTONE_SLAB(1, new String[]{"STEP", "STONE_SLAB", "DOUBLE_STEP"}),
   SANDSTONE_STAIRS(0, new String[0]),
   SANDSTONE_WALL(0, new String[0]),
   SCAFFOLDING(0, new String[]{"1.14", "SLIME_BLOCK"}),
   SCUTE(0, new String[]{"1.13"}),
   SEAGRASS(0, new String[]{"1.13", "GRASS"}),
   SEA_LANTERN(0, new String[0]),
   SEA_PICKLE(0, new String[]{"1.13"}),
   SHEARS(0, new String[0]),
   SHEEP_SPAWN_EGG(91, new String[]{"MONSTER_EGG"}),
   SHIELD(0, new String[0]),
   SHULKER_BOX(0, new String[]{"PURPLE_SHULKER_BOX"}),
   SHULKER_SHELL(0, new String[0]),
   SHULKER_SPAWN_EGG(69, new String[]{"MONSTER_EGG"}),
   SILVERFISH_SPAWN_EGG(60, new String[]{"MONSTER_EGG"}),
   SKELETON_HORSE_SPAWN_EGG(28, new String[]{"MONSTER_EGG"}),
   SKELETON_SKULL(0, new String[]{"SKULL", "SKULL_ITEM"}),
   SKELETON_SPAWN_EGG(51, new String[]{"MONSTER_EGG"}),
   SKELETON_WALL_SKULL(0, new String[]{"SKULL", "SKULL_ITEM"}),
   SKULL_BANNER_PATTERN(0, new String[0]),
   SLIME_BALL(0, new String[0]),
   SLIME_BLOCK(0, new String[0]),
   SLIME_SPAWN_EGG(55, new String[]{"MONSTER_EGG"}),
   SMITHING_TABLE(0, new String[0]),
   SMOKER(0, new String[]{"1.14", "FURNACE"}),
   SMOOTH_QUARTZ(0, new String[]{"1.13"}),
   SMOOTH_QUARTZ_SLAB(7, new String[]{"STEP"}),
   SMOOTH_QUARTZ_STAIRS(0, new String[0]),
   SMOOTH_RED_SANDSTONE(2, new String[]{"RED_SANDSTONE"}),
   SMOOTH_RED_SANDSTONE_SLAB(0, new String[]{"STONE_SLAB2"}),
   SMOOTH_RED_SANDSTONE_STAIRS(0, new String[0]),
   SMOOTH_SANDSTONE(2, new String[]{"SANDSTONE"}),
   SMOOTH_SANDSTONE_SLAB(0, new String[]{"STEP"}),
   SMOOTH_SANDSTONE_STAIRS(0, new String[0]),
   SMOOTH_STONE(0, new String[]{"STEP"}),
   SMOOTH_STONE_SLAB(0, new String[]{"STEP"}),
   SNOW(0, new String[0]),
   SNOWBALL(0, new String[]{"SNOW_BALL"}),
   SNOW_BLOCK(0, new String[0]),
   SOUL_SAND(0, new String[0]),
   SPAWNER(0, new String[]{"MOB_SPAWNER"}),
   SPECTRAL_ARROW(0, new String[0]),
   SPIDER_EYE(0, new String[0]),
   SPIDER_SPAWN_EGG(52, new String[]{"MONSTER_EGG"}),
   SPLASH_POTION(0, new String[0]),
   SPONGE(0, new String[0]),
   SPRUCE_BOAT(0, new String[]{"BOAT_SPRUCE"}),
   SPRUCE_BUTTON(0, new String[]{"WOOD_BUTTON"}),
   SPRUCE_DOOR(0, new String[]{"SPRUCE_DOOR_ITEM"}),
   SPRUCE_FENCE(0, new String[0]),
   SPRUCE_FENCE_GATE(0, new String[0]),
   SPRUCE_LEAVES(1, new String[]{"LEAVES"}),
   SPRUCE_LOG(1, new String[]{"LOG"}),
   SPRUCE_PLANKS(1, new String[]{"WOOD"}),
   SPRUCE_PRESSURE_PLATE(0, new String[]{"WOOD_PLATE"}),
   SPRUCE_SAPLING(1, new String[]{"SAPLING"}),
   SPRUCE_SIGN(0, new String[]{"SIGN"}),
   SPRUCE_SLAB(1, new String[]{"WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"}),
   SPRUCE_STAIRS(0, new String[]{"SPRUCE_WOOD_STAIRS"}),
   SPRUCE_TRAPDOOR(0, new String[]{"TRAP_DOOR"}),
   SPRUCE_WALL_SIGN(0, new String[]{"SIGN_POST", "WALL_SIGN"}),
   SPRUCE_WOOD(1, new String[]{"LOG"}),
   SQUID_SPAWN_EGG(94, new String[]{"MONSTER_EGG"}),
   STICK(0, new String[0]),
   STICKY_PISTON(0, new String[]{"PISTON_BASE", "PISTON_STICKY_BASE"}),
   STONE(0, new String[0]),
   STONECUTTER(0, new String[]{"1.14"}),
   STONE_AXE(0, new String[0]),
   STONE_BRICKS(0, new String[]{"SMOOTH_BRICK"}),
   STONE_BRICK_SLAB(4, new String[]{"STEP", "STONE_SLAB", "DOUBLE_STEP"}),
   STONE_BRICK_STAIRS(0, new String[]{"SMOOTH_STAIRS"}),
   STONE_BRICK_WALL(0, new String[0]),
   STONE_BUTTON(0, new String[0]),
   STONE_HOE(0, new String[0]),
   STONE_PICKAXE(0, new String[0]),
   STONE_PRESSURE_PLATE(0, new String[]{"STONE_PLATE"}),
   STONE_SHOVEL(0, new String[]{"STONE_SPADE"}),
   STONE_SLAB(0, new String[]{"STEP", "DOUBLE_STEP"}),
   STONE_STAIRS(0, new String[0]),
   STONE_SWORD(0, new String[0]),
   STRAY_SPAWN_EGG(6, new String[]{"MONSTER_EGG"}),
   STRING(0, new String[0]),
   STRIPPED_ACACIA_LOG(0, new String[]{"LOG_2"}),
   STRIPPED_ACACIA_WOOD(0, new String[]{"LOG_2"}),
   STRIPPED_BIRCH_LOG(2, new String[]{"LOG"}),
   STRIPPED_BIRCH_WOOD(2, new String[]{"LOG"}),
   STRIPPED_DARK_OAK_LOG(0, new String[]{"LOG"}),
   STRIPPED_DARK_OAK_WOOD(0, new String[]{"LOG"}),
   STRIPPED_JUNGLE_LOG(3, new String[]{"LOG"}),
   STRIPPED_JUNGLE_WOOD(3, new String[]{"LOG"}),
   STRIPPED_OAK_LOG(0, new String[]{"LOG"}),
   STRIPPED_OAK_WOOD(0, new String[]{"LOG"}),
   STRIPPED_SPRUCE_LOG(1, new String[]{"LOG"}),
   STRIPPED_SPRUCE_WOOD(1, new String[]{"LOG"}),
   STRUCTURE_BLOCK(0, new String[0]),
   STRUCTURE_VOID(0, new String[]{"1.10", "BARRIER/1.9"}),
   SUGAR(0, new String[0]),
   SUGAR_CANE(0, new String[]{"SUGAR_CANE_BLOCK"}),
   SUNFLOWER(0, new String[]{"DOUBLE_PLANT"}),
   SUSPICIOUS_STEW(0, new String[]{"1.14", "MUSHROOM_STEW"}),
   SWEET_BERRIES(0, new String[]{"1.14"}),
   SWEET_BERRY_BUSH(0, new String[]{"1.14", "GRASS"}),
   TALL_GRASS(2, new String[]{"DOUBLE_PLANT"}),
   TALL_SEAGRASS(2, new String[]{"1.13", "TALL_GRASS"}),
   TERRACOTTA(0, new String[]{"HARD_CLAY"}),
   TIPPED_ARROW(0, new String[0]),
   TNT(0, new String[0]),
   TNT_MINECART(0, new String[]{"EXPLOSIVE_MINECART"}),
   TORCH(0, new String[0]),
   TOTEM_OF_UNDYING(0, new String[]{"TOTEM"}),
   TRADER_LLAMA_SPAWN_EGG(103, new String[]{"1.14", "MONSTER_EGG"}),
   TRAPPED_CHEST(0, new String[0]),
   TRIDENT(0, new String[]{"1.13"}),
   TRIPWIRE(0, new String[0]),
   TRIPWIRE_HOOK(0, new String[0]),
   TROPICAL_FISH(2, new String[]{"RAW_FISH"}),
   TROPICAL_FISH_BUCKET(0, new String[]{"1.13", "BUCKET", "WATER_BUCKET"}),
   TROPICAL_FISH_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   TUBE_CORAL(0, new String[]{"1.13"}),
   TUBE_CORAL_BLOCK(0, new String[]{"1.13"}),
   TUBE_CORAL_FAN(0, new String[]{"1.13"}),
   TUBE_CORAL_WALL_FAN(0, new String[0]),
   TURTLE_EGG(0, new String[]{"1.13", "EGG"}),
   TURTLE_HELMET(0, new String[]{"1.13", "IRON_HELMENT"}),
   TURTLE_SPAWN_EGG(0, new String[]{"1.13", "MONSTER_EGG"}),
   VEX_SPAWN_EGG(35, new String[]{"MONSTER_EGG"}),
   VILLAGER_SPAWN_EGG(120, new String[]{"MONSTER_EGG"}),
   VINDICATOR_SPAWN_EGG(36, new String[]{"MONSTER_EGG"}),
   VINE(0, new String[0]),
   VOID_AIR(0, new String[]{"AIR"}),
   WALL_TORCH(0, new String[]{"TORCH"}),
   WANDERING_TRADER_SPAWN_EGG(0, new String[]{"1.14", "VILLAGER_SPAWN_EGG"}),
   WATER(0, new String[]{"STATIONARY_WATER"}),
   WATER_BUCKET(0, new String[0]),
   WET_SPONGE(1, new String[]{"SPONGE"}),
   WHEAT(0, new String[]{"CROPS"}),
   WHEAT_SEEDS(0, new String[]{"SEEDS"}),
   WHITE_BANNER(15, new String[]{"BANNER", "STANDING_BANNER"}),
   WHITE_BED(0, new String[]{"BED", "BED_BLOCK"}),
   WHITE_CARPET(0, new String[]{"CARPET"}),
   WHITE_CONCRETE(0, new String[]{"CONCRETE"}),
   WHITE_CONCRETE_POWDER(0, new String[]{"CONCRETE_POWDER"}),
   WHITE_DYE(15, new String[]{"1.14", "INK_SACK", "BONE_MEAL"}),
   WHITE_GLAZED_TERRACOTTA(0, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "WHITE_TERRACOTTA"}),
   WHITE_SHULKER_BOX(0, new String[0]),
   WHITE_STAINED_GLASS(0, new String[]{"STAINED_GLASS"}),
   WHITE_STAINED_GLASS_PANE(0, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   WHITE_TERRACOTTA(0, new String[]{"HARD_CLAY", "TERRACOTTA"}),
   WHITE_TULIP(6, new String[]{"RED_ROSE"}),
   WHITE_WALL_BANNER(15, new String[]{"WALL_BANNER"}),
   WHITE_WOOL(0, new String[]{"WOOL"}),
   WITCH_SPAWN_EGG(66, new String[]{"MONSTER_EGG"}),
   WITHER_ROSE(0, new String[]{"1.14", "BLACK_DYE"}),
   WITHER_SKELETON_SKULL(1, new String[]{"SKULL", "SKULL_ITEM"}),
   WITHER_SKELETON_SPAWN_EGG(5, new String[]{"MONSTER_EGG"}),
   WITHER_SKELETON_WALL_SKULL(1, new String[]{"SKULL", "SKULL_ITEM"}),
   WOLF_SPAWN_EGG(95, new String[]{"MONSTER_EGG"}),
   WOODEN_AXE(0, new String[]{"WOOD_AXE"}),
   WOODEN_HOE(0, new String[]{"WOOD_HOE"}),
   WOODEN_PICKAXE(0, new String[]{"WOOD_PICKAXE"}),
   WOODEN_SHOVEL(0, new String[]{"WOOD_SPADE"}),
   WOODEN_SWORD(0, new String[]{"WOOD_SWORD"}),
   WRITABLE_BOOK(0, new String[]{"BOOK_AND_QUILL"}),
   WRITTEN_BOOK(0, new String[0]),
   YELLOW_BANNER(11, new String[]{"BANNER", "STANDING_BANNER"}),
   YELLOW_BED(4, new String[]{"BED", "BED_BLOCK"}),
   YELLOW_CARPET(4, new String[]{"CARPET"}),
   YELLOW_CONCRETE(4, new String[]{"CONCRETE"}),
   YELLOW_CONCRETE_POWDER(4, new String[]{"CONCRETE_POWDER"}),
   YELLOW_DYE(11, new String[]{"INK_SACK", "DANDELION_YELLOW"}),
   YELLOW_GLAZED_TERRACOTTA(4, new String[]{"1.12", "HARD_CLAY", "STAINED_CLAY", "YELLOW_TERRACOTTA"}),
   YELLOW_SHULKER_BOX(0, new String[0]),
   YELLOW_STAINED_GLASS(4, new String[]{"STAINED_GLASS"}),
   YELLOW_STAINED_GLASS_PANE(4, new String[]{"THIN_GLASS", "STAINED_GLASS_PANE"}),
   YELLOW_TERRACOTTA(4, new String[]{"HARD_CLAY", "STAINED_CLAY"}),
   YELLOW_WALL_BANNER(11, new String[]{"WALL_BANNER"}),
   YELLOW_WOOL(4, new String[]{"WOOL"}),
   ZOMBIE_HEAD(2, new String[]{"SKULL", "SKULL_ITEM"}),
   ZOMBIE_HORSE_SPAWN_EGG(29, new String[]{"MONSTER_EGG"}),
   ZOMBIE_PIGMAN_SPAWN_EGG(57, new String[]{"MONSTER_EGG"}),
   ZOMBIE_SPAWN_EGG(54, new String[]{"MONSTER_EGG"}),
   ZOMBIE_VILLAGER_SPAWN_EGG(27, new String[]{"MONSTER_EGG"}),
   ZOMBIE_WALL_HEAD(2, new String[]{"SKULL", "SKULL_ITEM"});

   public static final String[] DAMAGEABLE = new String[]{"HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SWORD", "AXE", "PICKAXE", "SHOVEL", "HOE", "ELYTRA", "TRIDENT", "HORSE_ARMOR", "BARDING", "SHEARS", "FLINT_AND_STEEL", "BOW", "FISHING_ROD", "CARROT_ON_A_STICK", "CARROT_STICK"};
   public static final HashMap<XMaterial, XMaterial> DUPLICATED = new HashMap<XMaterial, XMaterial>() {
      {
         this.put(XMaterial.MELON, XMaterial.MELON_SLICE);
         this.put(XMaterial.CARROT, XMaterial.CARROTS);
         this.put(XMaterial.POTATO, XMaterial.POTATOES);
         this.put(XMaterial.BEETROOT, XMaterial.BEETROOTS);
         this.put(XMaterial.BROWN_MUSHROOM, XMaterial.BROWN_MUSHROOM_BLOCK);
         this.put(XMaterial.BRICK, XMaterial.BRICKS);
         this.put(XMaterial.RED_MUSHROOM, XMaterial.RED_MUSHROOM_BLOCK);
         this.put(XMaterial.MAP, XMaterial.FILLED_MAP);
         this.put(XMaterial.NETHER_BRICK, XMaterial.NETHER_BRICKS);
      }
   };
   public static final XMaterial[] VALUES = values();
   public static final String[] LEGACY_VALUES = (String[])Arrays.stream(VALUES).map((m) -> {
      return m.legacy;
   }).flatMap(Arrays::stream).toArray((x$0) -> {
      return new String[x$0];
   });
   private static final HashMap<String, XMaterial> CACHED_SEARCH = new HashMap();
   private static XMaterial.MinecraftVersion version;
   private static Boolean isNewVersion;
   private final byte data;
   private final String[] legacy;

   private XMaterial(int data, String... legacy) {
      this.data = (byte)data;
      this.legacy = legacy;
   }

   public static boolean isNewVersion() {
      if (XMaterial.isNewVersion != null) {
         return XMaterial.isNewVersion;
      }
      return XMaterial.isNewVersion = isVersionOrHigher(MinecraftVersion.VERSION_1_13);
   }

   public static boolean isOneEight() {
      return getVersion() == XMaterial.MinecraftVersion.VERSION_1_8;
   }

   @NotNull
   public static XMaterial.MinecraftVersion getVersion() {
      return version != null ? version : (version = valueOfVersion(Bukkit.getVersion()));
   }

   @NotNull
   private static XMaterial requestOldXMaterial(@NotNull String name, byte data) {
      XMaterial cached = (XMaterial)CACHED_SEARCH.get(name + "," + data);
      if (cached != null) {
         return cached;
      } else {
         Optional<XMaterial> search = data == -1 ? Arrays.stream(VALUES).filter((mat) -> {
            return mat.matchAnyLegacy(name);
         }).findFirst() : Arrays.stream(VALUES).filter((mat) -> {
            return mat.matchAnyLegacy(name) && mat.data == data;
         }).findFirst();
         if (search.isPresent()) {
            XMaterial found = (XMaterial)search.get();
            CACHED_SEARCH.put(found.legacy[0] + "," + found.getData(), found);
            return found;
         } else {
            return AIR;
         }
      }
   }

   public static boolean contains(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      String formatted = format(name);
      return Arrays.stream(VALUES).anyMatch((mat) -> {
         return mat.name().equals(formatted);
      });
   }

   public static boolean containsLegacy(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      String formatted = format(name);
      return Arrays.stream(LEGACY_VALUES).anyMatch((mat) -> {
         return parseLegacyVersionMaterialName(mat).equals(formatted);
      });
   }

   @NotNull
   public static XMaterial matchXMaterial(@NotNull Material material) {
      return matchXMaterial(material.name());
   }

   @NotNull
   public static XMaterial matchXMaterial(@NotNull String name) {
      return name.contains(":") ? matchXMaterial(name.substring(0, name.indexOf(":")), Byte.parseByte(StringUtils.replace(name.substring(name.indexOf(":") + 1), " ", ""))) : matchXMaterial(name, (byte)-1);
   }

   @NotNull
   public static Material parseMaterial(@NotNull String name, byte data) {
      return matchXMaterial(name, data).parseMaterial();
   }

   @NotNull
   public static XMaterial matchXMaterial(@NotNull String name, byte data) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      name = format(name);
      if (!contains(name) || data > 0 || !isNewVersion() && isDuplicated(name)) {
         return isDuplicated(name) && !isNewVersion() ? requestDuplicatedXMaterial(name, data) : requestOldXMaterial(name, data);
      } else {
         return valueOf(name);
      }
   }

   @NotNull
   public static XMaterial matchXMaterial(int id, byte data) {
      return (XMaterial)Arrays.stream(VALUES).filter((mat) -> {
         return mat.getId() == id && mat.data == data;
      }).findFirst().orElse(AIR);
   }

   @NotNull
   private static XMaterial requestDuplicatedXMaterial(@NotNull String name, byte data) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      XMaterial mat = requestOldXMaterial(name, data);
      return mat.name().endsWith("S") ? valueOf(name) : mat;
   }

   @NotNull
   public static XMaterial getNewXMaterialIfDuplicated(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      return (XMaterial)DUPLICATED.entrySet().stream().filter((m) -> {
         return ((XMaterial)m.getKey()).name().equals(format(name));
      }).findFirst().map(Entry::getValue).orElse(AIR);
   }

   @NotNull
   public static XMaterial getXMaterialIfDuplicated(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      String formatted = format(name);
      Optional<Entry<XMaterial, XMaterial>> mat = DUPLICATED.entrySet().stream().filter((m) -> {
         return ((XMaterial)m.getKey()).name().equals(formatted) || ((XMaterial)m.getValue()).name().equals(formatted);
      }).findFirst();
      if (mat.isPresent()) {
         Entry<XMaterial, XMaterial> found = (Entry)mat.get();
         return formatted.equals(((XMaterial)found.getKey()).name()) ? (XMaterial)found.getValue() : (XMaterial)found.getKey();
      } else {
         return AIR;
      }
   }

   @NotNull
   private static String format(@NotNull String name) {
      return StringUtils.replace(StringUtils.replace(name.toUpperCase(), "MINECRAFT:", ""), "-", "_").trim().replaceAll("\\s+", "_").replaceAll("\\d+", "").replaceAll("\\W+", "");
   }

   @NotNull
   private static String parseLegacyVersionMaterialName(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      return !name.contains("/") ? name : name.substring(0, name.indexOf(47));
   }

   public static boolean isVersionOrHigher(@NotNull XMaterial.MinecraftVersion version) {
      XMaterial.MinecraftVersion current = getVersion();
      if (version == current) {
         return true;
      } else if (version == XMaterial.MinecraftVersion.UNKNOWN) {
         return false;
      } else if (current == XMaterial.MinecraftVersion.UNKNOWN) {
         return true;
      } else {
         int ver = Integer.parseInt(StringUtils.replace(StringUtils.replace(version.name(), "VERSION_", ""), "_", ""));
         int currentVer = Integer.parseInt(StringUtils.replace(StringUtils.replace(current.name(), "VERSION_", ""), "_", ""));
         return currentVer >= ver;
      }
   }

   @NotNull
   public static String toWord(@NotNull Material material) {
      return toWord(material.name());
   }

   @NotNull
   private static String toWord(@NotNull String name) {
      StringBuilder translated = new StringBuilder();
      String[] separator = name.split("_");
      if (separator.length == 0) {
         translated.append(name.substring(1).toLowerCase());
         return translated.toString();
      } else {
         Arrays.stream(separator).forEach((s) -> {
            translated.append(s.charAt(0)).append(s.substring(1).toLowerCase()).append(" ");
         });
         return translated.toString();
      }
   }

   public static boolean isVersionOrHigher(@NotNull String version) {
      int currentVer = Integer.parseInt(getExactMajorVersion(StringUtils.replace(Bukkit.getVersion(), ".", "")));
      int versionNumber = Integer.parseInt(StringUtils.replace(version, ".", ""));
      return currentVer >= versionNumber;
   }

   @NotNull
   public static String getExactMajorVersion(@NotNull String version) {
      if (version.contains("SNAPSHOT") || version.contains("-R")) {
         version = version.substring(0, version.indexOf("-"));
      }

      if (version.contains("git")) {
         version = StringUtils.replace(version.substring(version.indexOf("MC:") + 4), ")", "");
      }

      if (version.split(Pattern.quote(".")).length > 2) {
         version = version.substring(0, version.lastIndexOf("."));
      }

      return version;
   }

   @NotNull
   private static MinecraftVersion valueOfVersion(@NotNull String version) {
      version = getExactMajorVersion(version);
      if (version.equals("1.10") || version.equals("1.11") || version.equals("1.12")) {
         return MinecraftVersion.VERSION_1_9;
      }
      version = StringUtils.replace(version, ".", "_");
      if (!version.startsWith("VERSION_")) {
         version = "VERSION_" + version;
      }
      final String check = version;
      return Arrays.stream(MinecraftVersion.VALUES).anyMatch(v -> v.name().equals(check)) ? MinecraftVersion.valueOf(version) : MinecraftVersion.UNKNOWN;
   }

   public static boolean isDamageable(@NotNull final String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      final Stream<String> stream = Arrays.stream(XMaterial.DAMAGEABLE);
      Objects.requireNonNull(name);
      return stream.anyMatch(name::contains);
   }

   public static boolean isDuplicated(@NotNull String name) {
      Validate.notEmpty(name, "Material name cannot be null or empty");
      String formatted = format(name);
      return DUPLICATED.entrySet().stream().anyMatch((x) -> {
         return ((XMaterial)x.getKey()).name().equals(formatted);
      });
   }

   @NotNull
   public String toWord() {
      return toWord(this.parseMaterial().name());
   }

   public int getId() {
      return this.isNew() ? -1 : this.parseMaterial().getId();
   }

   public boolean isDuplicated() {
      return DUPLICATED.containsKey(this);
   }

   public boolean matchAnyLegacy(String name) {
      String formatted = format(name);
      return Arrays.asList(this.legacy).contains(formatted);
   }

   @NotNull
   public XMaterial getXMaterialIfDuplicated() {
      Optional<Entry<XMaterial, XMaterial>> mat = DUPLICATED.entrySet().stream().filter((m) -> {
         return m.getKey() == this;
      }).findFirst();
      return (XMaterial)mat.map(Entry::getValue).orElse(AIR);
   }

   public boolean isDamageable() {
      return isDamageable(this.name());
   }

   public byte getData() {
      return this.data;
   }

   @NotNull
   public String[] getLegacy() {
      return this.legacy;
   }

   @NotNull
   public ItemStack parseItem() {
      return this.parseItem(false);
   }

   @NotNull
   public ItemStack parseItem(boolean suggest) {
      Material material = this.parseMaterial(suggest);
      return isNewVersion() ? new ItemStack(material) : new ItemStack(material, 1, (short)this.data);
   }

   @NotNull
   public Material parseMaterial() {
      return this.parseMaterial(false);
   }

   @NotNull
   public Material parseMaterial(boolean suggest) {
      Material newMat = Material.getMaterial(this.name());
      return newMat == null || !isNewVersion() && this.isDuplicated() ? this.requestOldMaterial(suggest) : newMat;
   }

   @NotNull
   private Material requestOldMaterial(boolean suggest) {
      boolean isNew = this.getVersionIfNew() != XMaterial.MinecraftVersion.UNKNOWN;

      for(int i = this.legacy.length - 1; i >= 0; --i) {
         String legacyName = this.legacy[i];
         Material oldMat;
         if (legacyName.contains("/")) {
            oldMat = Material.getMaterial(parseLegacyVersionMaterialName(legacyName));
            if (oldMat != null) {
               return oldMat;
            }
         } else {
            if (isNew) {
               if (!suggest) {
                  return Material.AIR;
               }

               oldMat = Material.getMaterial(legacyName);
               if (oldMat != null) {
                  return oldMat;
               }
            }

            oldMat = Material.getMaterial(legacyName);
            if (oldMat != null) {
               return oldMat;
            }
         }
      }

      return Material.AIR;
   }

   @NotNull
   public String[] getSuggestions() {
      return this.legacy.length != 0 && this.legacy[0].contains(".") ? (String[])Arrays.stream(this.legacy).filter((mat) -> {
         return !mat.contains(".");
      }).toArray((x$0) -> {
         return new String[x$0];
      }) : new String[0];
   }

   public boolean isSupported() {
      return Arrays.stream(Material.values()).anyMatch((mat) -> {
         return mat.name().equals(this.name()) || this.matchAnyLegacy(mat.name());
      });
   }

   @NotNull
   public XMaterial.MinecraftVersion getVersionIfNew() {
      return this.isNew() ? valueOfVersion(this.legacy[0]) : XMaterial.MinecraftVersion.UNKNOWN;
   }

   public boolean isNew() {
      return this.legacy.length != 0 && this.legacy[0].contains(".");
   }

   @NotNull
   public XMaterial suggestOldMaterialIfNew() {
      if (this.getVersionIfNew() != XMaterial.MinecraftVersion.UNKNOWN && this.legacy.length != 1) {
         for(int i = this.legacy.length - 1; i >= 0; --i) {
            String legacyName = this.legacy[i];
            if (!legacyName.contains("/")) {
               XMaterial mat = matchXMaterial(parseLegacyVersionMaterialName(legacyName), this.data);
               if (this != mat) {
                  return mat;
               }
            }
         }

         return AIR;
      } else {
         return AIR;
      }
   }

   public static enum MinecraftVersion {
      VERSION_1_8,
      VERSION_1_9,
      VERSION_1_13,
      VERSION_1_14,
      UNKNOWN;

      public static final XMaterial.MinecraftVersion[] VALUES = values();
   }
}
