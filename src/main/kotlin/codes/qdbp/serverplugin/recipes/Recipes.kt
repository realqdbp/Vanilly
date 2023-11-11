package codes.qdbp.serverplugin.recipes

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.createInvisTaggedFrame
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe


data class Recipes(val plugin: Serverplugin){

    val lightRecipe =
        ShapedRecipe(NamespacedKey(plugin, "light"), ItemStack(Material.LIGHT))
            .shape(
                "ttt",
                "tit",
                "ttt"
            )
            .setIngredient('t', Material.TORCH)
            .setIngredient('i', Material.IRON_BLOCK)


    val invisItemFrameRecipe =
        ShapelessRecipe(NamespacedKey(plugin, "invisibleItemFrame"), createInvisTaggedFrame(plugin))
            .addIngredient(Material.ITEM_FRAME)
            .addIngredient(Material.GLASS_PANE)

}