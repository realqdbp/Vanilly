package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun Player.openBackpack(plugin: Serverplugin) {
    val storage = plugin.backpackStorage.storage

    val inventory = Bukkit.createInventory(this, 54, Component.text("${this.name}'s Backpack"))

    if (!storage.contains(this.name, false)) {
        openInventory(inventory)
        return
    }

    @Suppress("UNCHECKED_CAST")
    inventory.contents = storage.get(this.name) as Array<out ItemStack?>

    this.openInventory(inventory)
}