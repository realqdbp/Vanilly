@file:JvmName("UtilsKt")

package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import com.google.gson.Gson
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionType
import org.bukkit.util.Vector
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


@Suppress("PropertyName")
class T (val tag_name: String? = null)

fun checkPluginUpToDate(plugin: Serverplugin): Boolean {

    val request = HttpRequest.newBuilder(URI("https://api.github.com/repos/realqdbp/Serverplugin/releases/latest")).build()
    val httpClient = HttpClient.newHttpClient()
    val response = httpClient.sendAsync(request, BodyHandlers.ofString()).get()

    @Suppress("UnstableApiUsage")
    return Gson().fromJson(response.body(), T::class.java).tag_name == "v${plugin.pluginMeta.version}"
}


var afkPlayers = mutableMapOf<UUID, AfkPlayer>()

fun Player.endAfk() {
    Bukkit.getScheduler().cancelTask(afkPlayers[this.uniqueId]?.afkTask?.taskId ?: return)
    afkPlayers.remove(this.uniqueId)
    this.isInvulnerable = false
    this.playerListName(null)
}

fun Player.setAfk(reason: String = "", plugin: Serverplugin) {
    afkPlayers[this.uniqueId] = AfkPlayer(this, reason, plugin)
}

fun Player.changeAfkMessage(reason: String) {
    afkPlayers[this.uniqueId]?.reason = reason
    this.sendMessage("AFK message changed!")
}


var freecamPlayers = mutableMapOf<UUID, FreecamPlayer>()

fun Player.startFreecam() {
    freecamPlayers[this.uniqueId] = FreecamPlayer(this, this.location)
}

fun Player.endFreecam() {
    this.teleport(freecamPlayers[this.uniqueId]?.location ?: return)
    freecamPlayers.remove(this.uniqueId)
    this.gameMode = GameMode.SURVIVAL
}

fun createInvisTaggedFrame(plugin: Serverplugin): ItemStack {
    val item = ItemStack(Material.ITEM_FRAME)
    val itemMeta = item.itemMeta
    itemMeta.displayName(Component.text("Invisible Item Frame", TextColor.color(0x2a9d8f)))
    itemMeta.persistentDataContainer.set(plugin.invisItemFrameNSK, PersistentDataType.BYTE, 1)
    item.setItemMeta(itemMeta)
    return item
}


// https://minecraft.fandom.com/wiki/Food#Foods ("food points", "saturation restored")
val foodMap = hashMapOf(
    Material.APPLE to listOf(4, 2.4f),
    Material.BAKED_POTATO to listOf(5, 6f),
    Material.BEETROOT to listOf(1, 1.2f),
    Material.BEETROOT_SOUP to listOf(6, 7.2f),
    Material.BREAD to listOf(5, 6f),
    Material.CARROT to listOf(3, 3.6f),
    Material.COOKED_CHICKEN to listOf(6, 7.2f),
    Material.COOKED_COD to listOf(5, 6f),
    Material.COOKED_MUTTON to listOf(6, 9.6f),
    Material.COOKED_PORKCHOP to listOf(8, 12.8f),
    Material.COOKED_RABBIT to listOf(5, 6f),
    Material.COOKED_SALMON to listOf(6, 9.6f),
    Material.COOKIE to listOf(2, 0.4f),
    Material.DRIED_KELP to listOf(1, 0.6f),
    Material.GLOW_BERRIES to listOf(2, 0.4f),
    Material.MELON_SLICE to listOf(2, 1.2f),
    Material.MUSHROOM_STEW to listOf(6, 7.2f),
    Material.PUMPKIN_PIE to listOf(8, 4.8f),
    Material.RABBIT_STEW to listOf(10, 12f),
    Material.COOKED_BEEF to listOf(8, 12.8f),
    Material.SWEET_BERRIES to listOf(2, 0.4f),
    Material.GOLDEN_CARROT to listOf(6, 14.4f)
)

val upgradeMenuHandlers = mutableMapOf<UUID,Listener>()

fun createItem(material: Material, title: String, vararg lores: String): ItemStack {
    val item = ItemStack(material)
    item.editMeta { meta ->
        meta.displayName(Component.text(title))
        meta.lore(lores.map { Component.text(it) })
    }
    return item
}

fun createItem(item: ItemStack, vararg lores: String): ItemStack {
    item.editMeta { meta -> meta.lore(lores.map { Component.text(it) }) }
    return item
}


/**
 * @param items Itemstack, row, yOffset
 */
fun createInventory(holder: InventoryHolder?, title: String, vararg items: Triple<ItemStack, Int, Int>): Inventory {
    if (items.isEmpty()) return Bukkit.createInventory(null, 9, Component.text("FAILED TO BUILD INVENTORY"))
    if (items.any { it.third !in -4..4 }) return Bukkit.createInventory(null, 9, Component.text("FAILED TO BUILD INVENTORY"))
    if (items.any { it.second == 0 }) return Bukkit.createInventory(null, 9, Component.text("FAILED TO BUILD INVENTORY"))

    val inventory = Bukkit.createInventory(holder, items.maxBy{ it.second }.second*9, Component.text(title))
    items.forEach { inventory.setItem((4 + ((it.second - 1) * 9)) + it.third, it.first) }
    return inventory
}

fun createInventory(title: String, items: Array<ItemStack>): Inventory {
    val start = items.size/2
    return createInventory(
        null,
        title,
        *items.mapIndexed { index, itemStack -> Triple(itemStack, 1, -start+index) }.toTypedArray(),
    )
}

fun calcItemsNeeded(type: String, clazz: ItemStack, tier: Int): Array<ItemStack> {
    return when (type) {
        "UNBREAKABLE" -> {
            arrayOf(
                clazz,
                ItemStack(Material.NETHERITE_INGOT, 10),
                createItem(
                    Material.OAK_SIGN,
                    "Upgrade Information",
                    "Upgrade to",
                    "UNBREAKABLE",
                    "Click your items to use them!",
                ),
                ItemStack(Material.NETHER_STAR, 1),
                ItemStack(Material.OBSIDIAN, 32)
            )
        }

        "EFFICIENCY" -> {
            val speedPotion = ItemStack(Material.POTION)
            val sign = createItem(
                Material.OAK_SIGN,
                "Upgrade Information",
                "Upgrade",
                "EFFICIENCY",
                "to ${tier + 5}",
                "Click your items to use them!",
            )
            val item = createItem(clazz, "With Efficiency", "level ${tier + 4}")
            when (tier) {
                1 -> {
                    speedPotion.editMeta { meta ->
                        meta as PotionMeta
                        meta.basePotionType = PotionType.SPEED
                    }
                    arrayOf(
                        item,
                        ItemStack(Material.NETHERITE_INGOT, 1),
                        sign,
                        ItemStack(Material.FEATHER, 4),
                        speedPotion
                    )
                }

                2 -> {
                    speedPotion.editMeta { meta ->
                        meta as PotionMeta
                        meta.basePotionType = PotionType.LONG_SWIFTNESS
                    }
                    arrayOf(
                        item,
                        ItemStack(Material.NETHERITE_INGOT, 1),
                        sign,
                        ItemStack(Material.FEATHER, 8),
                        speedPotion
                    )
                }

                3 -> {
                    speedPotion.editMeta { meta ->
                        meta as PotionMeta
                        meta.basePotionType = PotionType.LONG_SWIFTNESS
                    }
                    arrayOf(
                        item,
                        ItemStack(Material.NETHERITE_INGOT, 1),
                        ItemStack(Material.FEATHER, 16),
                        sign,
                        speedPotion,
                        ItemStack(Material.HONEY_BOTTLE, 4)
                    )
                }

                4 -> {
                    speedPotion.editMeta { meta ->
                        meta as PotionMeta
                        meta.basePotionType = PotionType.STRONG_SWIFTNESS
                    }
                    arrayOf(
                        item,
                        ItemStack(Material.NETHERITE_INGOT, 1),
                        ItemStack(Material.FEATHER, 32),
                        sign,
                        speedPotion,
                        ItemStack(Material.HONEY_BOTTLE, 8),
                        ItemStack(Material.END_CRYSTAL, 1)
                    )
                }

                5 -> {
                    speedPotion.editMeta { meta ->
                        meta as PotionMeta
                        meta.basePotionType = PotionType.STRONG_SWIFTNESS
                    }
                    arrayOf(
                        item,
                        ItemStack(Material.NETHERITE_INGOT, 1),
                        ItemStack(Material.FEATHER, 64),
                        speedPotion,
                        sign,
                        ItemStack(Material.HONEY_BOTTLE, 16),
                        ItemStack(Material.END_CRYSTAL, 3),
                        ItemStack(Material.NETHER_STAR, 1)
                    )
                }
                else -> emptyArray()
            }
        }
        else -> emptyArray()
    }
}


fun returnItemsOnFail(player: Player) {
    val inventory = player.openInventory.topInventory
    for (i in 9..<inventory.size) {
        player.inventory.addItem(inventory.getItem(i) ?: continue)
    }
}

/**
 * Important: Sign needs to be at: odd - middle, even - middle + 1
 */
fun createConfirmInventory(items: Array<ItemStack>): Inventory {
    val start = items.size/2
    return createInventory(
        null,
        "Confirm Upgrade",
        *items.mapIndexed { index, itemStack -> Triple(itemStack, 1, -start+index) }.toTypedArray(),
        Triple(ItemStack(Material.AIR), 2, 0)
    )
}

fun checkConfirmItemsCorrect(confirmInv: Inventory): Boolean {
    val itemsNeeded = confirmInv.filterIndexed { index, itemStack -> index <= 8  && itemStack?.type != Material.OAK_SIGN}.filterNotNull().toMutableList()
    val itemsOwned = confirmInv.filterIndexed { index, _ -> index in 9..17}.filterNotNull().toMutableList()

    if (itemsNeeded.isEmpty() || itemsOwned.isEmpty()) return false

    // A bit weird at movementspeed but should still work
    if (itemsNeeded.first().type == itemsOwned.first().type) {
        itemsNeeded.remove(itemsNeeded.first())
        itemsOwned.remove(itemsOwned.first())

        if (itemsNeeded == itemsOwned) return true
    }
    return false
}

fun giveUpgrade(confirmInv: Inventory, plugin: Serverplugin): ItemStack {
    val upgrade = confirmInv.filterIndexed { index, itemStack -> index <= 8 && itemStack?.type == Material.OAK_SIGN}.firstOrNull()?.lore()?.get(1)
        ?.let { PlainTextComponentSerializer.plainText().serialize(it).removeSurrounding("[", "]") } ?: return ItemStack(Material.AIR)
    val item = confirmInv.filterIndexed { index, _ -> index in 9..17}.filterNotNull().toMutableList().first()
    return when (upgrade) {
        "UNBREAKABLE" -> {
            item.editMeta { meta -> meta.isUnbreakable = true }
            item
        }
        "EFFICIENCY" -> {
            item.editMeta { meta ->
                meta.persistentDataContainer.set(plugin.efficiencyUpgradeNSK, PersistentDataType.INTEGER, item.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1)
                meta.addEnchant(Enchantment.DIG_SPEED, item.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1, true)
            }
            item
        }
        else -> {
            ItemStack(Material.AIR)
        }
    }
}