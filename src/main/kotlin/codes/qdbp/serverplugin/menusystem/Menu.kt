package codes.qdbp.serverplugin.menusystem

import codes.qdbp.serverplugin.misc.createItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class Menu(private var playerMenuManager: PlayerMenuManager) : InventoryHolder {

    protected var inv: Inventory? = null

    protected var fillerGlass: ItemStack = createItem(Material.GRAY_STAINED_GLASS_PANE, " ")

    abstract val menuName: String

    abstract val slots: Int

    abstract fun handleMenu(event: InventoryClickEvent)

    abstract fun setMenuItems()

    open fun handleCloseMenu(event: InventoryCloseEvent) { }

    fun open() {
        inv = Bukkit.createInventory(this, slots, Component.text(menuName))

        setMenuItems()

        playerMenuManager.owner.openInventory(inv!!)
    }

    fun close() {
        playerMenuManager.owner.closeInventory()
    }


    override fun getInventory(): Inventory {
        return inv!!
    }

    fun setFillerGlass() {
        playerMenuManager.owner.sendPlainMessage("${inv!!.getItem(23)}")
        (0..<slots).forEach {
            if (inv!!.getItem(it) == null) inv!!.setItem(it, fillerGlass)
        }
    }
}