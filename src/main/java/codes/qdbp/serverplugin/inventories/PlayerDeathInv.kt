package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun Player.openPlayerDeathsInventory(plugin: Serverplugin, clickedItem: ItemStack) {
    val storage = plugin.deathStorage.storage

    val headName = PlainTextComponentSerializer.plainText().serialize(clickedItem.displayName())
    val playerName = headName.substring(1, headName.indexOf("'"))

    val inventory = Bukkit.createInventory(null, 54, Component.text("$playerName deaths"))

    val deathIcon = ItemStack(Material.SKELETON_SKULL)

    var i = storage.getInt("$playerName.deaths")
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
    this.openInventory(inventory)
}