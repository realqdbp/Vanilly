package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.*;
import codes.qdbp.serverplugin.enchantments.HasteEnchantment;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.*;


public class Serverplugin extends JavaPlugin {

    private static final HashMap<Material, List<Float>> foodMap = new HashMap<>(); //food, sat
    private static Plugin plugin;
    public static ItemStack item;

    public static HasteEnchantment hasteEnchantment;


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
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(new CraftCommand());
        Objects.requireNonNull(this.getCommand("enchantcrazy")).setExecutor(new ChestplateCommand());
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new EnderChestCommand());
        Objects.requireNonNull(this.getCommand("enchantUnbreakable")).setExecutor(new EnchantUnbreakableCommand());


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


        hasteEnchantment = new HasteEnchantment("Sheesh");
        registerEnchantment(hasteEnchantment);

    }

    @Override
    public void onDisable() {

        //unregister enchantment
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if (byKey.containsKey(hasteEnchantment.getKey())) {
                byKey.remove(hasteEnchantment.getKey());
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if (byName.containsKey(hasteEnchantment.getName())) {
                byName.remove(hasteEnchantment.getName());
            }
        } catch (Exception ignored) {

        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }

    }

    public static HashMap<Material, List<Float>> getFoodMap() {
        return foodMap;
    }

    public static Plugin getPlugin() {
        return plugin;
    }



}
