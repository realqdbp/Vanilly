package codes.qdbp.serverplugin.menusystem.menus

import codes.qdbp.serverplugin.menusystem.Menu
import codes.qdbp.serverplugin.menusystem.PlayerMenuManager
import codes.qdbp.serverplugin.misc.createItem
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent

class DeathMenu(playerMenuManager: PlayerMenuManager, override val menuName: String = "DeathMenu Test", override val slots: Int = 9) : Menu(playerMenuManager) {
    override fun handleMenu(e: InventoryClickEvent) {
        when (e.currentItem?.type) {
            Material.EMERALD -> {
                e.whoClicked.closeInventory()
                e.whoClicked.health = 0.0
            }

            Material.BARRIER -> {
                e.whoClicked.sendPlainMessage("Cringe")
                e.whoClicked.closeInventory()
            }
            else -> {}
        }
    }

    override fun setMenuItems() {

        inv?.setItem(3, createItem(Material.EMERALD, "Yes"))
        inv?.setItem(5, createItem(Material.BARRIER, "No"))
        setFillerGlass()

    }
}