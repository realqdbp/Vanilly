package codes.qdbp.serverplugin.menusystem.menus

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.PagedMenu
import codes.qdbp.serverplugin.menusystem.PlayerMenuManager
import codes.qdbp.serverplugin.misc.createItem
import codes.qdbp.serverplugin.misc.string
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent


class PlayersDeathsMenu(
    plugin: Serverplugin,
    playerName: String,
    playerMenuManager: PlayerMenuManager,
    override var menuName: String = "${playerName}s Tode",
    override val slots: Int = 54
) : PagedMenu(playerMenuManager) {

    private val storage = plugin.deathStorage.storage
    private val deaths = storage.getInt("$playerName.deaths")

    private val deathList = (1..deaths).map {
        createItem(
            Material.SKELETON_SKULL, "$it",
            "${storage.get("$playerName.death.$it.death")}",
            "${storage.get("$playerName.death.$it.cause")}",
            "${storage.get("$playerName.death.$it.irlTime")}",
            "${storage.get("$playerName.death.$it.mcTime")}",
            "${storage.get("$playerName.death.$it.position")}",
        )
    }

    override fun handleMenu(event: InventoryClickEvent) {

        val clickedItemName = event.currentItem?.displayName()?.string() ?: ""
        val clickedMaterial = event.currentItem?.type

        when (clickedMaterial) {
            Material.DARK_OAK_BUTTON -> {
                when (clickedItemName) {
                    "Left" -> {
                        if (page == 0) return
                        page -= 1
                        open()
                    }

                    "Right" -> {
                        if (index + 1 > deathList.size) return
                        page += 1
                        open()
                    }
                }
            }

            Material.BARRIER -> close()
            else -> { }
        }
    }

    override fun setMenuItems() {
        addMenuBorder()

        (0..maxItemsPerPage).forEach {
            index = maxItemsPerPage * page + it

            if (index >= deathList.size) return@forEach
            inv?.addItem(deathList[index])
        }

        setFillerGlass()
    }
}