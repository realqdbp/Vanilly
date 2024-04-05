package codes.qdbp.serverplugin.menusystem

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class Menu(private var playerMenuManager: PlayerMenuManager) : InventoryHolder {

    protected var inv: Inventory? = null

    protected var fillerGlass: ItemStack = makeItem(Material.GRAY_STAINED_GLASS_PANE, " ")

    abstract val menuName: String?

    abstract val slots: Int

    abstract fun handleMenu(e: InventoryClickEvent)

    abstract fun setMenuItems()

    fun open() {
        inv = Bukkit.createInventory(this, slots, Component.text(menuName!!))

        this.setMenuItems()

        playerMenuManager.owner.openInventory(inv!!)
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

    fun makeItem(material: Material?, displayName: String?, vararg lore: String?): ItemStack {
        val item = ItemStack(material!!)
        val itemMeta = item.itemMeta
        itemMeta.setDisplayName(displayName)

        itemMeta.lore = lore.asList()
        item.setItemMeta(itemMeta)

        return item
    }
}