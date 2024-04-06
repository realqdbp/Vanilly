package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.menus.PlayersDeathsMenu
import codes.qdbp.serverplugin.misc.menuManager
import codes.qdbp.serverplugin.misc.string
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.currentItem == null) return
        val player = event.whoClicked as Player

        val inventoryTitle = PlainTextComponentSerializer.plainText().serialize(event.view.title())

        when {
            inventoryTitle.contains("Deaths") -> {
                event.isCancelled = true
            }

            inventoryTitle == "Change Efficiency" -> {
                event.isCancelled = true
                player.inventory.itemInMainHand.editMeta { meta ->
                    meta.addEnchant(Enchantment.DIG_SPEED, PlainTextComponentSerializer.plainText().serialize(event.currentItem!!.displayName()).removeSurrounding("[", "]").toIntOrNull() ?: 0, true)
                }
            }
        }
    }
}