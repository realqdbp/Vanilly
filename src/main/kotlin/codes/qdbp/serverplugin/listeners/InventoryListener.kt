package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.inventories.openPlayerDeathsInventory
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
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

        val invTitle = event.view.title().compact()

        when (invTitle) {
            Component.text("Players Deaths Overview") -> {
                event.isCancelled = true
                player.closeInventory()
                player.openPlayerDeathsInventory(plugin, event.currentItem!!)
            }

            Component.text("${player.name}'s Deaths") -> {
                event.isCancelled = true
            }

            Component.text("Change Efficiency") -> {
                event.isCancelled = true
                player.inventory.itemInMainHand.editMeta { meta ->
                    meta.addEnchant(Enchantment.DIG_SPEED, PlainTextComponentSerializer.plainText().serialize(event.currentItem!!.displayName()).removeSurrounding("[", "]").toIntOrNull() ?: 0, true)
                }
            }
        }
    }
}