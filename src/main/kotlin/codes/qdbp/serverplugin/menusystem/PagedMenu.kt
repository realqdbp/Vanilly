package codes.qdbp.serverplugin.menusystem

import codes.qdbp.serverplugin.misc.createItem
import org.bukkit.Material


abstract class PagedMenu(playerMenuManager: PlayerMenuManager) : Menu(playerMenuManager) {

    protected var page: Int = 0

    protected val maxItemsPerPage: Int = 28

    protected var index: Int = 0


    fun addMenuBorder() {
        inventory.setItem(48, createItem(Material.DARK_OAK_BUTTON, "Left"))

        inventory.setItem(49, createItem(Material.BARRIER, "Close"))

        inventory.setItem(50, createItem(Material.DARK_OAK_BUTTON, "Right"))

        for (i in 0..9) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.fillerGlass)
            }
        }

        inventory.setItem(17, super.fillerGlass)
        inventory.setItem(18, super.fillerGlass)
        inventory.setItem(26, super.fillerGlass)
        inventory.setItem(27, super.fillerGlass)
        inventory.setItem(35, super.fillerGlass)
        inventory.setItem(36, super.fillerGlass)

        for (i in 44..53) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.fillerGlass)
            }
        }
    }


}