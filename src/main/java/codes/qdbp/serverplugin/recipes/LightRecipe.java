package codes.qdbp.serverplugin.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class LightRecipe {

    ShapedRecipe lightRecipe;

    public LightRecipe(NamespacedKey namespacedKey) {
        ItemStack light = new ItemStack(Material.LIGHT, 1);

        this.lightRecipe = new ShapedRecipe(namespacedKey, light);

        this.lightRecipe.shape(
                "ttt",
                "tdt",
                "ttt"
        );
        this.lightRecipe.setIngredient('t', Material.TORCH);
        this.lightRecipe.setIngredient('d', Material.DIAMOND);
    }

    public ShapedRecipe getLightRecipe() {
        return lightRecipe;
    }
}

