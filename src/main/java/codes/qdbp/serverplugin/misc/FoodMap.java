package codes.qdbp.serverplugin.misc;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FoodMap {

    private static final HashMap<Material, List<Float>> foodMap = new HashMap<>();


    public static HashMap<Material, List<Float>> getFoodMap() {

        /*
        Arrays.asList(hunger amount, saturation amount)
        https://minecraft.fandom.com/wiki/Food#Foods ("food points", "saturation restored")
        */
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

        return foodMap;
    }
}
