package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.Objects

fun Player.openDeathsOverview(plugin: Serverplugin) {
    val storage = plugin.deathStorage.storage

    val inventory = Bukkit.createInventory(null, InventoryType.CHEST, Component.text("Players Deaths Overview"))

    val headItem = ItemStack(Material.PLAYER_HEAD)
    val senderHeadMeta = headItem.itemMeta as SkullMeta

    senderHeadMeta.playerProfile = this.playerProfile
    senderHeadMeta.lore(listOf(Component.text("${storage.getInt("${this.name}.deaths")} Tode")))
    headItem.itemMeta = senderHeadMeta

    inventory.addItem(headItem)


    Bukkit.getServer().whitelistedPlayers.forEach {
        if (Objects.equals(it.name, this.name)) return@forEach
        val merta = headItem.itemMeta as SkullMeta
        merta.owningPlayer = it
        merta.lore(listOf(Component.text("${storage.getInt("${it.name}.deaths")} Tode")))
        headItem.itemMeta = merta
        inventory.addItem(headItem)
    }

    this.openInventory(inventory)
}