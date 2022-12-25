package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.*;
import codes.qdbp.serverplugin.inventories.UnbreakableUpgradeMenuInventory;
import codes.qdbp.serverplugin.inventories.UpgradeMenuInventory;
import codes.qdbp.serverplugin.misc.FoodMap;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Serverplugin extends JavaPlugin {

    private static Plugin plugin;
    private static Inventory upgradeMenuInventory;
    private static Inventory unbreakableUpgradeMenuInventory;
    private static HashMap<Material, List<Float>> foodMap;

    @Override
    public void onEnable() {
        plugin = this;
        upgradeMenuInventory = new UpgradeMenuInventory().getUpgradeMenuInventory();
        unbreakableUpgradeMenuInventory = new UnbreakableUpgradeMenuInventory().getUnbreakableUpgradeMenuInventory();
        foodMap = FoodMap.getFoodMap();

        getServer().getPluginManager().registerEvents(new ListenerClass(), this);
        Objects.requireNonNull(this.getCommand("tode")).setExecutor(new TodeCommand());
        Objects.requireNonNull(this.getCommand("features")).setExecutor(new InfoMessageCommand());
        Objects.requireNonNull(this.getCommand("backpack")).setExecutor(new BackpackCommand());
        Objects.requireNonNull(this.getCommand("afk")).setExecutor(new AFKCommand());
        Objects.requireNonNull(this.getCommand("freecam")).setExecutor(new FreecamCommand());
        Objects.requireNonNull(this.getCommand("switchworld")).setExecutor(new SwitchWorldCommand());
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(new CraftCommand());
        Objects.requireNonNull(this.getCommand("enchy")).setExecutor(new EnchantCrazy());
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new EnderChestCommand());
        Objects.requireNonNull(this.getCommand("enchantUnbreakable")).setExecutor(new EnchantUnbreakableCommand());
        Objects.requireNonNull(this.getCommand("upgrade")).setExecutor(new OpenUpgradeMenuCommand());
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling Plugin");
    }

    public static HashMap<Material, List<Float>> getFoodMap() {
        return foodMap;
    }

    public static Inventory getUpgradeMenuInventory() {
        return upgradeMenuInventory;
    }

    public static Inventory getUnbreakableUpgradeMenuInventory() {
        return unbreakableUpgradeMenuInventory;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
