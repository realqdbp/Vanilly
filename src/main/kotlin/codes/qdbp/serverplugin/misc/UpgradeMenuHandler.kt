package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.inventories.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

data class UpgradeMenuHandler(
    val plugin: Serverplugin,
    var player: Player? = null,
    var upgradeType: String? = null,
    var upgradeClass: ItemStack? = null,
    var upgradeTier: Int? = null,
) : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.currentItem == null) return
        val invTitle = event.view.title().compact()
        when (invTitle) {
            Component.text("Choose Upgrade") -> {
                event.isCancelled = true
                if (event.view.bottomInventory == event.clickedInventory) return
                when (event.currentItem) {
                    ItemStack(Material.AIR) -> {}
                    else -> {
                        upgradeType = PlainTextComponentSerializer.plainText().serialize(event.currentItem?.displayName() ?: Component.text("")).removeSurrounding("[", "]")
                    }
                }
            }

            Component.text("Choose Tool to Upgrade") -> {
                event.isCancelled = true
                if (event.view.bottomInventory == event.clickedInventory) return
                when (event.currentItem) {
                    ItemStack(Material.AIR) -> {}
                    else -> {
                        upgradeClass = event.currentItem
                    }
                }
            }

            Component.text("Choose Tier") -> {
                event.isCancelled = true
                when (event.currentItem) {
                    ItemStack(Material.AIR) -> {}
                    else -> {
                        upgradeTier = PlainTextComponentSerializer.plainText().serialize(event.currentItem?.displayName() ?: Component.text("")).removeSurrounding("[", "]").toIntOrNull() ?: 0
                    }
                }
            }

            Component.text("Confirm Upgrade") -> {
                event.isCancelled = true
                if (event.rawSlot <= 17) return
                val clickedItemStack = event.currentItem ?: return
                if (clickedItemStack.itemMeta.isUnbreakable) return
                val itemsNeeded = calcItemsNeeded(upgradeType!!, upgradeClass!!, upgradeTier ?: 0)
                var foundItem = itemsNeeded.find { it.type == clickedItemStack.type } ?: return

                if (clickedItemStack.amount - foundItem.amount < 0) return

                //set items to below the requested ones
                val slotFromFoundItem = event.inventory.indexOfFirst { it == foundItem } //should always be the first ones found
                if (event.inventory.getItem(slotFromFoundItem + 9) == null) {

                    if (slotFromFoundItem == event.inventory.indexOfFirst { it != null }) {
                        foundItem = clickedItemStack
                    }
                    event.inventory.setItem(slotFromFoundItem+9, foundItem)
                    clickedItemStack.subtract(foundItem.amount)
                }
                return // because of global handle() after when, and we want to avoid that shit
            }
        }
        handle()
    }

    private fun handle() {
        val stage: Int = if (upgradeType.isNullOrEmpty()) {
            0
        } else if (upgradeClass == null) {
            1
        } else if (upgradeTier == null) {
            2
        } else 3

        when (stage) {
            0 -> player?.openInventory(UpgradeTypeMenu.inventory)
            1 -> {
                if (upgradeType == "UNBREAKABLE") player?.openInventory(UpgradeClassMenu.inventory)
                else player?.openInventory(createInventory(null, "WIP"))
            }
            2 -> {
                if (upgradeType == "UNBREAKABLE") {
                    val items = calcItemsNeeded(upgradeType!!, upgradeClass!!, 0)
                    player?.openInventory(createConfirmInventory(items))
                }
                else player?.openInventory(createInventory(null, "WIP"))
            }
            3 -> player?.openInventory(createInventory(
                null,
                "Confirm Upgrade",

            ))
        }
    }

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
        upgradeMenuHandlers[player!!.uniqueId] = this
        player?.openInventory(UpgradeTypeMenu.inventory)
    }
}