package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.inventories.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
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
        val currentItem = event.currentItem ?: return
        val invTitle = event.view.title().compact()
        when (invTitle) {
            Component.text("Choose Upgrade") -> {
                event.isCancelled = true
                if (event.view.bottomInventory == event.clickedInventory) return
                if (currentItem != ItemStack(Material.AIR)) upgradeType = PlainTextComponentSerializer.plainText().serialize(currentItem.displayName()).removeSurrounding("[", "]")
            }

            Component.text("Choose Tool to Upgrade") -> {
                event.isCancelled = true
                if (event.view.bottomInventory == event.clickedInventory) return
                if (currentItem != ItemStack(Material.AIR)) upgradeClass = currentItem
            }

            Component.text("Choose Tier") -> {
                event.isCancelled = true
                when (currentItem) {
                    ItemStack(Material.AIR) -> {}
                    else -> {
                        upgradeTier = PlainTextComponentSerializer.plainText().serialize(currentItem.displayName()).removeSurrounding("[", "]").toIntOrNull() ?: 0
                    }
                }
            }

            Component.text("Confirm Upgrade") -> {
                event.isCancelled = true
                if (event.rawSlot <= 17) return
                if (upgradeType == "UNBREAKABLE" && currentItem.itemMeta.isUnbreakable) return


                if (upgradeType == "EFFICIENCY") {
                    val item = event.view.topInventory.filterIndexed { index, _ -> index <= 8}.filterNotNull().toMutableList().first()
                    if (currentItem.type == item.type) {
                        player?.sendPlainMessage(currentItem.getEnchantmentLevel(Enchantment.DIG_SPEED).toString())
                        player?.sendPlainMessage((upgradeTier?.plus(4).toString()))
                        if (currentItem.getEnchantmentLevel(Enchantment.DIG_SPEED) != (upgradeTier?.plus(4) ?: return)) {
                            return
                        }
                    }
                }

                val itemsNeeded = calcItemsNeeded(upgradeType!!, upgradeClass!!, upgradeTier ?: 0)
                var foundItem = itemsNeeded.find { it.type == currentItem.type } ?: return

                if (currentItem.amount - foundItem.amount < 0) return

                //set items to below the requested ones
                val slotFromFoundItem = event.inventory.indexOfFirst { it == foundItem } //should always be the first ones found
                if (event.inventory.getItem(slotFromFoundItem + 9) == null) {

                    if (slotFromFoundItem == event.inventory.indexOfFirst { it != null }) {
                        foundItem = currentItem
                    }
                    event.inventory.setItem(slotFromFoundItem + 9, foundItem)
                    currentItem.subtract(foundItem.amount)
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
                when (upgradeType) {
                    "UNBREAKABLE" -> {
                        player?.openInventory(UpgradeClassMenu.unbreakableInventory)
                    }
                    "EFFICIENCY" -> {
                        player?.openInventory(UpgradeClassMenu.efficiencyInventory)
                    }
                    else -> player?.openInventory(createInventory(null, "WIP"))
                }

            }
            2 -> {
                when (upgradeType) {
                    "UNBREAKABLE" -> {
                        val items = calcItemsNeeded(upgradeType!!, upgradeClass!!, 0)
                        player?.openInventory(createConfirmInventory(items))
                    }
                    "EFFICIENCY" -> {
                        player?.openInventory(UpgradeTierMenu.inventory)
                    }
                    else -> player?.openInventory(createInventory(null, "WIP"))
                }
            }
            3 -> {
                when (upgradeType) {
                    "EFFICIENCY" -> {
                        val items = calcItemsNeeded(upgradeType!!, upgradeClass!!, upgradeTier ?: 0)
                        player?.openInventory(createConfirmInventory(items))
                    }
                    else -> player?.openInventory(createInventory(null, "WIP"))
                }
            }
        }
    }

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
        upgradeMenuHandlers[player!!.uniqueId] = this
        player?.openInventory(UpgradeTypeMenu.inventory)
    }
}