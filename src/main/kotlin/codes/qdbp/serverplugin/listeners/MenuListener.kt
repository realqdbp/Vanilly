package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.menusystem.Menu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MenuListener : Listener {

    @EventHandler
    fun onMenuClick(event: InventoryClickEvent) {

        val player = event.whoClicked

        val holder = event.clickedInventory?.holder ?: return
        if (event.currentItem == null) return
        if (holder is Menu) {
            event.isCancelled = true



            holder.handleMenu(event)



        }

    }

}