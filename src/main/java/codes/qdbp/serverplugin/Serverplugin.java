package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class Serverplugin extends JavaPlugin {

    private static final HashMap<Material, List<Float>> foodMap = new HashMap<>(); //food, sat
    private static Plugin plugin;


    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new ListenerClass(), this);
        Objects.requireNonNull(this.getCommand("tode")).setExecutor(new TodeCommand());
        Objects.requireNonNull(this.getCommand("features")).setExecutor(new InfoMessageCommand());
        Objects.requireNonNull(this.getCommand("backpack")).setExecutor(new BackpackCommand());
        Objects.requireNonNull(this.getCommand("afk")).setExecutor(new AFKCommand());
        Objects.requireNonNull(this.getCommand("freecam")).setExecutor(new FreecamCommand());
        Objects.requireNonNull(this.getCommand("switchworld")).setExecutor(new SwitchWorldCommand());

        foodMap.put(Material.APPLE, Arrays.asList(4f, 2.4f));
        foodMap.put(Material.BAKED_POTATO, Arrays.asList(5f, 6f));
        foodMap.put(Material.BEETROOT, Arrays.asList(1f, 1.2f));
        foodMap.put(Material.BEETROOT_SOUP, Arrays.asList(6f, 7.2f));
        foodMap.put(Material.BREAD, Arrays.asList(5f, 6f));
        foodMap.put(Material.CARROT, Arrays.asList(3f, 3.6f));
        foodMap.put(Material.COOKED_CHICKEN, Arrays.asList(6f, 7.2f));
        foodMap.put(Material.COOKED_COD, Arrays.asList(56f, 6f));
        foodMap.put(Material.COOKED_MUTTON, Arrays.asList(6f, 9.6f));
        foodMap.put(Material.COOKED_PORKCHOP, Arrays.asList(8f, 12.8f));
        foodMap.put(Material.COOKED_RABBIT, Arrays.asList(5f, 6f));
        foodMap.put(Material.COOKED_SALMON, Arrays.asList(6f, 9.6f));
        foodMap.put(Material.COOKIE, Arrays.asList(2f, 0.4f));
        foodMap.put(Material.DRIED_KELP, Arrays.asList(1f, 0.6f));
        foodMap.put(Material.GLOW_BERRIES, Arrays.asList(2f, 0.4f));
        foodMap.put(Material.MELON_SLICE, Arrays.asList(2f, 1.2f));
        foodMap.put(Material.MUSHROOM_STEW, Arrays.asList(6f, 7.2f));
        foodMap.put(Material.PUMPKIN_PIE, Arrays.asList(8f, 4.8f));
        foodMap.put(Material.RABBIT_STEW, Arrays.asList(10f, 12f));
        foodMap.put(Material.COOKED_BEEF, Arrays.asList(8f, 12.8f));
        foodMap.put(Material.SWEET_BERRIES, Arrays.asList(2f, 0.4f));
        foodMap.put(Material.GOLDEN_CARROT, Arrays.asList(6f, 14.4f));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HashMap<Material, List<Float>> getFoodMap() {
        return foodMap;
    }

    public static Plugin getPlugin() {
        return plugin;
    }


}
