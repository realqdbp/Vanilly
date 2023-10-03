package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

class InventoryCloseListener(val plugin: Serverplugin) : Listener {
    private val storage = plugin.backpackStorage.storage


    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player

        if (event.view.title() != Component.text("${player.name}'s Backpack")) return

        val items = event.inventory.contents
        storage.set(player.name, items)
        plugin.backpackStorage.save()
    }
}