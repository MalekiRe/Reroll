package com.smashingmods.reroll.config;

import com.smashingmods.reroll.Reroll;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Config {
    private static final String CATEGORY_REROLL = "reroll";
    private static final String CATEGORY_COMPAT = "compat";

    public static boolean requireItem;
    public static String[] rerollItems;
    public static int minDistance;
    public static boolean rerollAllTogether;
    public static boolean useCurrentDim;
    public static int overrideDim;
    public static boolean initialInventory;
    public static boolean setNewInventory;
    public static boolean resetEnderChest;
    public static boolean sendInventoryToChest;
    public static boolean rerollOnDeath;
    public static boolean startLocked;
    public static int cooldown;
    public static String blockSpawnName;
    public static boolean enableBlockSearchSpawn;

    public static Block spawnBlock = null;

    public static int timeisupTimer;

    public static void readConfig() {
        Configuration config = Reroll.CONFIG;
        try {
            config.load();
            initRerollConfig(config);
        } catch (Exception e) {
            com.smashingmods.reroll.Reroll.LOGGER.error("Problem loading config file:\n", e);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }


    private static void initRerollConfig(Configuration config) {
        config.addCustomCategoryComment(CATEGORY_REROLL, "Reroll Command Configuration");
        requireItem = config.getBoolean("Require Dice", CATEGORY_REROLL, true, "Using Reroll requires Reroll Dice. Disables the /reroll command for non-OP players.");
        rerollItems = config.getStringList("Reroll Inventory", CATEGORY_REROLL, new String[]{"reroll:dice;1"}, "A list of items that will be added to a player's inventory after using the reroll command.\nYou can add any existing item per line like this: \"minecraft:torch;16\".\nNote that you can only have as many items as there are inventory slots.");
        minDistance = config.getInt("Reroll Minimum Distance", CATEGORY_REROLL, 512, 256, 10240, "Determines the minimum distance to teleport when you reroll.");
        rerollAllTogether = config.getBoolean("Reroll All Together", CATEGORY_REROLL, false, "Should '/reroll all' send all players to the same location?");
        useCurrentDim = config.getBoolean("Reroll Dimension", CATEGORY_REROLL, false, "Should reroll spawn location be set in the player's current dimension? If set to false, the override value will be used.");
        overrideDim = config.getInt("Override Dimension", CATEGORY_REROLL, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, "Override the dimension used for the reroll spawn location");
        initialInventory = config.getBoolean("Initial Inventory", CATEGORY_REROLL, true, "Should players get an initial inventory when they first join a world?");
        setNewInventory = config.getBoolean("Initial Inventory per Reroll", CATEGORY_REROLL, true, "Should players get an initial inventory every time they reroll?");
        resetEnderChest = config.getBoolean("Reset Ender Chest", CATEGORY_REROLL, true, "Should players have their ender chest cleared too?");
        sendInventoryToChest = config.getBoolean("Send to Chest", CATEGORY_REROLL, false, "Should player inventory be dropped into a chest where they rerolled?");
        rerollOnDeath = config.getBoolean("Reroll On Death [Hardcore Mode]", CATEGORY_REROLL, false, "Reroll players if they die to simulate a hardcore experience.");
        startLocked = config.getBoolean("Lock Reroll", CATEGORY_REROLL, true, "This is a safety feature to lock the use of reroll at the start. Users are required to use /reroll unlock to use reroll just in case.");
        cooldown = config.getInt("Reroll Cooldown", CATEGORY_REROLL, 60, 30, Integer.MAX_VALUE, "Cooldown time to use reroll dice.");

        blockSpawnName = config.getString("Block Spawn", CATEGORY_REROLL, "minecraft:leaves", "sets the resource location name for the block you try to spawn on");
        enableBlockSearchSpawn = config.getBoolean("Enable Block Search Spawn", CATEGORY_REROLL, false, "whether or not you automatically search for a block to spawn on");
        config.addCustomCategoryComment(CATEGORY_COMPAT, "Mod Compatibility Configuration");
        timeisupTimer = config.getInt("Time is up Timer", CATEGORY_COMPAT, 12000, 1200, Integer.MAX_VALUE, "Set the Timer value after a reroll.");
    }
}
