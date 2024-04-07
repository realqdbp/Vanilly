package codes.qdbp.serverplugin.menusystem.menus

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.Menu
import codes.qdbp.serverplugin.menusystem.PlayerMenuManager
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack

class BackpackMenu(val plugin: Serverplugin, val private: Boolean, val playerMenuManager: PlayerMenuManager, override val menuName: String = "Backpack", override val slots: Int = 54) : Menu(playerMenuManager) {
    private val storage = plugin.backpackStorage.storage
    private val items = if (private) storage.getList(playerMenuManager.owner.name) else storage.getList("P-U-B-L-I-C")

    override fun handleMenu(event: InventoryClickEvent) {
        event.isCancelled = false
    }

    override fun setMenuItems() {
        items?.forEach { inv?.addItem((it ?: ItemStack(Material.AIR)) as ItemStack) }
    }

    override fun handleCloseMenu(event: InventoryCloseEvent) {
        storage.set(if (private) playerMenuManager.owner.name else "P-U-B-L-I-C", event.inventory.contents.asList())
        plugin.backpackStorage.save()
    }
}