package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.*;
import codes.qdbp.serverplugin.inventories.EfficiencyUpgradeMenuInventory;
import codes.qdbp.serverplugin.inventories.UnbreakableUpgradeMenuInventory;
import codes.qdbp.serverplugin.inventories.UpgradeMenuInventory;
import codes.qdbp.serverplugin.recipes.LightRecipe;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Serverplugin extends JavaPlugin {

    private static Plugin plugin;
    private static Inventory upgradeMenuInventory;
    private static Inventory unbreakableUpgradeMenuInventory;
    private static Inventory efficiencyUpgradeMenuInventory;


    @Override
    public void onEnable() {
        plugin = this;
        upgradeMenuInventory = new UpgradeMenuInventory().getUpgradeMenuInventory();
        unbreakableUpgradeMenuInventory = new UnbreakableUpgradeMenuInventory().getUnbreakableUpgradeMenuInventory();
        efficiencyUpgradeMenuInventory = new EfficiencyUpgradeMenuInventory().getEfficiencyUpgradeMenuInventory();

        /*
        Commands
         */
        getServer().getPluginManager().registerEvents(new ListenerClass(), this);
        Objects.requireNonNull(this.getCommand("tode")).setExecutor(new TodeCommand());
        Objects.requireNonNull(this.getCommand("features")).setExecutor(new InfoMessageCommand());
        Objects.requireNonNull(this.getCommand("backpack")).setExecutor(new BackpackCommand());
        Objects.requireNonNull(this.getCommand("afk")).setExecutor(new AFKCommand());
        Objects.requireNonNull(this.getCommand("freecam")).setExecutor(new FreecamCommand());
        Objects.requireNonNull(this.getCommand("switchworld")).setExecutor(new SwitchWorldCommand());
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new EnderChestCommand());
        Objects.requireNonNull(this.getCommand("upgrade")).setExecutor(new OpenUpgradeMenuCommand());
        Objects.requireNonNull(this.getCommand("skipNight")).setExecutor(new SkipNightCommand());
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(new CraftCommand());

        /*
        Recipes
         */
        Bukkit.addRecipe(new LightRecipe(new NamespacedKey(this, "light")).getLightRecipe());
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling Plugin");
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

    public static Plugin getPlugin() {
        return plugin;
    }
}
