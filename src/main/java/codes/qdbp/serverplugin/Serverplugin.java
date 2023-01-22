package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.listeners.*;
import codes.qdbp.serverplugin.commands.*;
import codes.qdbp.serverplugin.inventories.EfficiencyUpgradeMenuInventory;
import codes.qdbp.serverplugin.inventories.UnbreakableUpgradeMenuInventory;
import codes.qdbp.serverplugin.inventories.UpgradeMenuInventory;
import codes.qdbp.serverplugin.misc.FoodMap;
import codes.qdbp.serverplugin.recipes.LightRecipe;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Serverplugin extends JavaPlugin {


    private static Inventory upgradeMenuInventory;
    private static Inventory unbreakableUpgradeMenuInventory;
    private static Inventory efficiencyUpgradeMenuInventory;

    public static HashMap<UUID, ArmorStand> freecamPlayerMap;

    public static ArrayList<UUID> afkPlayerList;

    public static HashMap<UUID, Integer> afkPlayerRunningTasksMap;

    public static HashMap<UUID, String> afkPlayerTimes;

    @Override
    public void onEnable() {

        upgradeMenuInventory = new UpgradeMenuInventory().getUpgradeMenuInventory();
        unbreakableUpgradeMenuInventory = new UnbreakableUpgradeMenuInventory().getUnbreakableUpgradeMenuInventory();
        efficiencyUpgradeMenuInventory = new EfficiencyUpgradeMenuInventory().getEfficiencyUpgradeMenuInventory();
        freecamPlayerMap = new HashMap<>();
        afkPlayerList = new ArrayList<>();
        afkPlayerRunningTasksMap = new HashMap<>();
        afkPlayerTimes = new HashMap<>();

        /*
        Commands
         */
        Objects.requireNonNull(getCommand("tode")).setExecutor(new TodeCommand(this));
        Objects.requireNonNull(getCommand("features")).setExecutor(new FeaturesCommand());
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new BackpackCommand(this));
        Objects.requireNonNull(getCommand("afk")).setExecutor(new AFKCommand(this));
        Objects.requireNonNull(getCommand("freecam")).setExecutor(new FreecamCommand(this));
        Objects.requireNonNull(getCommand("switchworld")).setExecutor(new SwitchWorldCommand());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderChestCommand());
        Objects.requireNonNull(getCommand("upgrade")).setExecutor(new OpenUpgradeMenuCommand());
        Objects.requireNonNull(getCommand("skipnight")).setExecutor(new SkipNightCommand(this));
        Objects.requireNonNull(getCommand("craft")).setExecutor(new CraftCommand());
        Objects.requireNonNull(getCommand("info")).setExecutor(new InfoCommand());

        /*
        Listeners
         */
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(FoodMap.getFoodMap()), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        /*
        Recipes
         */
        Bukkit.addRecipe(new LightRecipe(new NamespacedKey(this, "light")).getLightRecipe());
    }

    @Override
    public void onDisable() {

        freecamPlayerMap.forEach((key, value) -> FreecamCommand.backportPlayer(this, Objects.requireNonNull(Bukkit.getPlayer(key)), true));
    }

    public static Inventory getUpgradeMenuInventory() {
        return upgradeMenuInventory;
    }

    public static Inventory getUnbreakableUpgradeMenuInventory() {
        return unbreakableUpgradeMenuInventory;
    }

    public static Inventory getEfficiencyUpgradeMenuInventory() {
        return efficiencyUpgradeMenuInventory;
    }


}
