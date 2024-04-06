package codes.qdbp.serverplugin.menusystem

import codes.qdbp.serverplugin.misc.createItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
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

    fun open() {
        inv = Bukkit.createInventory(this, slots, Component.text(menuName))

        this.setMenuItems()

        playerMenuManager.owner.openInventory(inv!!)
    }

    fun close() {
        playerMenuManager.owner.closeInventory()
    }


    override fun getInventory(): Inventory {
        return inv!!
    }

    fun setFillerGlass() {
        for (i in 0 until slots) {
            if (inv!!.getItem(i) == null) {
                inv!!.setItem(i, fillerGlass)
            }
        }
    }
}