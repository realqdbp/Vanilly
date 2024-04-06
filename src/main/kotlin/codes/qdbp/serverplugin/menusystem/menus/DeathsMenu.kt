package codes.qdbp.serverplugin.menusystem.menus

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.PagedMenu
import codes.qdbp.serverplugin.menusystem.PlayerMenuManager
import codes.qdbp.serverplugin.misc.string
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class DeathsMenu(
    val plugin: Serverplugin,
    private val playerMenuManager: PlayerMenuManager,
    override val menuName: String = "Todesübersicht",
    override val slots: Int = 54
) : PagedMenu(playerMenuManager) {

    private val storage = plugin.deathStorage.storage
    private val headItem = ItemStack(Material.PLAYER_HEAD)
    private val whitelistedPlayerHeads = Bukkit.getServer().whitelistedPlayers.map {
        val meta = headItem.itemMeta as SkullMeta
        meta.owningPlayer = it
        meta.lore(listOf(Component.text("${storage.getInt("${it.name}.deaths")} Tode")))
        headItem.itemMeta = meta
        headItem
    }

    override fun handleMenu(event: InventoryClickEvent) {
        val clickedMaterial = event.currentItem?.type ?: return
        val clickedItemName = event.currentItem?.displayName()?.string() ?: return

        when (clickedMaterial) {
            Material.DARK_OAK_BUTTON -> {
                when (clickedItemName) {
                    "Left" -> {
                        if (page == 0) return
                        page -= 1
                        open()
                    }

                    "Right" -> {
                        if (index + 1 > whitelistedPlayerHeads.size) return
                        page += 1
                        open()
                    }
                }
            }

            Material.BARRIER -> close()
            Material.PLAYER_HEAD -> {
                val playerName = event.currentItem!!.displayName().string().substring(0, clickedItemName.indexOf("'"))
                PlayersDeathsMenu(plugin, playerName, playerMenuManager).open()
            }

            else -> {}
        }
    }

    override fun setMenuItems() {
        addMenuBorder()

        (0..maxItemsPerPage).forEach {
            index = maxItemsPerPage * page + it

            if (index >= whitelistedPlayerHeads.size) return
            inv?.addItem(whitelistedPlayerHeads[index])
        }

        open()
    }
}