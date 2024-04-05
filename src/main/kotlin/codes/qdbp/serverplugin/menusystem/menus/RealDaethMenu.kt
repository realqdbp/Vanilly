package codes.qdbp.serverplugin.menusystem.menus

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.PagedMenu
import codes.qdbp.serverplugin.menusystem.PlayerMenuManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class RealDaethMenu(val plugin: Serverplugin, playerMenuManager: PlayerMenuManager, override val menuName: String = "REAL DEATH MENU", override val slots: Int = 54) : PagedMenu(playerMenuManager) {
    override fun handleMenu(e: InventoryClickEvent) {

    }

    override fun setMenuItems() {

        val deaths = 88
        val deathIcon = ItemStack(Material.SKELETON_SKULL)

        (0..deaths).forEach {
            val itemMeta = deathIcon.itemMeta
        }

        while (storage.get("$playerName.death.$i.cause") != null) { //doesn't matter what subobject is null
            val itemMeta = deathIcon.itemMeta
            itemMeta.lore(listOf(
                Component.text("${storage.get("$playerName.death.$i.death")}"),
                Component.text("${storage.get("$playerName.death.$i.cause")}"),
                Component.text("${storage.get("$playerName.death.$i.irlTime")}"),
                Component.text("${storage.get("$playerName.death.$i.mcTime")}"),
                Component.text("${storage.get("$playerName.death.$i.position")}")
            ))

            deathIcon.itemMeta = itemMeta
            inventory.addItem(deathIcon)
            --i
        }
    }
}