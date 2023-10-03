package codes.qdbp.serverplugin.recipes

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe


data class LightRecipe(val plugin: Serverplugin){

    val lightRecipe =
        ShapedRecipe(NamespacedKey(plugin, "light"), ItemStack(Material.LIGHT))
            .shape(
                "ttt",
                "tit",
                "ttt"
            )
            .setIngredient('t', Material.TORCH)
            .setIngredient('i', Material.IRON_BLOCK)

}