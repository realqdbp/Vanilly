package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.listeners.*;
import codes.qdbp.serverplugin.commands.*;
import codes.qdbp.serverplugin.misc.FoodMap;
import codes.qdbp.serverplugin.recipes.LightRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Serverplugin extends JavaPlugin {

    private static final double currentPluginVersion = 2.3;

    public static ArrayList<UUID> afkPlayerList;

    public static HashMap<UUID, Integer> afkPlayerRunningTasksMap;

    public static HashMap<UUID, String> afkPlayerTimes;

    public static HashMap<UUID, Location> freecamPlayerLocation;


    @Override
    public void onEnable() {

        /*
        Add feature toggles to Config
         */
        getConfig().addDefault("Feature-Toggles.useAFK", true);
        getConfig().addDefault("Feature-Toggles.useBackpack", true);
        getConfig().addDefault("Feature-Toggles.useCraft", true);
        getConfig().addDefault("Feature-Toggles.useEnderchest", true);
        getConfig().addDefault("Feature-Toggles.useFreecam", true);
        getConfig().addDefault("Feature-Toggles.useUpgrade", true);
        getConfig().addDefault("Feature-Toggles.useSkipnight", true);
        getConfig().addDefault("Feature-Toggles.useSwitchworld", true);
        getConfig().addDefault("Feature-Toggles.useDeaths", true);
        getConfig().addDefault("Feature-Toggles.useEating", true);
        getConfig().addDefault("Feature-Toggles.useSleep", true);
        getConfig().addDefault("Feature-Toggles.useElytrachange", true);
        getConfig().addDefault("Feature-Toggles.useLightRecipe", true);
        getConfig().addDefault("Feature-Toggles.useDoubleOpenDoors", true);
        getConfig().options().copyDefaults(true);
        saveConfig();


        /*
        On - Off toggle
         */
        boolean useAFK = getConfig().getBoolean("Feature-Toggles.useAFK");
        boolean useBackpack = getConfig().getBoolean("Feature-Toggles.useBackpack");
        boolean useCraft = getConfig().getBoolean("Feature-Toggles.useCraft");
        boolean useEnderchest = getConfig().getBoolean("Feature-Toggles.useEnderchest");
        boolean useFreecam = getConfig().getBoolean("Feature-Toggles.useFreecam");
//        boolean useUpgrade = getConfig().getBoolean("Feature-Toggles.useUpgrade");
        boolean useSkipnight = getConfig().getBoolean("Feature-Toggles.useSkipnight");
        boolean useSwitchworld = getConfig().getBoolean("Feature-Toggles.useSwitchworld");
        boolean useDeaths = getConfig().getBoolean("Feature-Toggles.useDeaths");
        boolean useEating = getConfig().getBoolean("Feature-Toggles.useEating");
        boolean useSleep = getConfig().getBoolean("Feature-Toggles.useSleep");
        boolean useElytrachange = getConfig().getBoolean("Feature-Toggles.useElytrachange");
        boolean useLightRecipe = getConfig().getBoolean("Feature-Toggles.useLightRecipe");
        boolean useDoubleOpenDoors = getConfig().getBoolean("Feature-Toggles.useDoubleOpenDoors");

        freecamPlayerLocation = new HashMap<>();
        afkPlayerList = new ArrayList<>();
        afkPlayerRunningTasksMap = new HashMap<>();
        afkPlayerTimes = new HashMap<>();

        /*
        Commands
         */
        Objects.requireNonNull(getCommand("features")).setExecutor(new FeaturesCommand());
        Objects.requireNonNull(getCommand("info")).setExecutor(new InfoCommand());
        if (useAFK) Objects.requireNonNull(getCommand("afk")).setExecutor(new AFKCommand(this));
        if (useBackpack) Objects.requireNonNull(getCommand("backpack")).setExecutor(new BackpackCommand(this));
        if (useCraft) Objects.requireNonNull(getCommand("craft")).setExecutor(new CraftCommand());
        if (useEnderchest) Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderChestCommand());
        if (useFreecam) Objects.requireNonNull(getCommand("freecam")).setExecutor(new FreecamCommand());
//        if (useUpgrade) Objects.requireNonNull(getCommand("upgrade")).setExecutor(new OpenUpgradeMenuCommand());
        if (useSkipnight) Objects.requireNonNull(getCommand("skipnight")).setExecutor(new SkipNightCommand(this));
        if (useSwitchworld) Objects.requireNonNull(getCommand("switchworld")).setExecutor(new SwitchWorldCommand());
        if (useDeaths) Objects.requireNonNull(getCommand("tode")).setExecutor(new TodeCommand(this));


        /*
        Listeners
         */
        if (useBackpack) getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        if (useDeaths) getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        if (useDeaths) getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        if (useElytrachange) getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        if (useEating) getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(FoodMap.getFoodMap()), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        if (useAFK) getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
        if (useSleep) getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        if (useAFK) getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        if (useAFK || useFreecam) getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        if (useDoubleOpenDoors) getServer().getPluginManager().registerEvents(new PlayerDoorInteractListener(), this);


        /*
        Recipes
         */
        if (useLightRecipe) Bukkit.addRecipe(new LightRecipe(new NamespacedKey(this, "light")).getLightRecipe());
    }

    @Override
    public void onDisable() {
        freecamPlayerLocation.forEach((key, value) -> FreecamCommand.backportPlayer(Objects.requireNonNull(Bukkit.getPlayer(key))));
    }

    public static double getCurrentPluginVersion() {
        return currentPluginVersion;
    }
}
