package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.menusystem.Menu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class MenuListener : Listener {

    @EventHandler
    fun onMenuClick(event: InventoryClickEvent) {

        val holder = event.clickedInventory?.holder ?: return
        if (event.currentItem == null) return

        if (holder is Menu) {
            event.isCancelled = true

            holder.handleMenu(event)
        }
    }

    @EventHandler
    fun onMenuClose(event: InventoryCloseEvent) {

        val holder = event.inventory.holder ?: return

        if (holder is Menu) holder.handleCloseMenu(event)
    }
}