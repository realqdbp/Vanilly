package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.misc.createInventory
import codes.qdbp.serverplugin.misc.createItem
import org.bukkit.Material


object UpgradeTypeMenu {
        private val anvil = createItem(Material.ANVIL, "UNBREAKABLE", "Make your tools", "last forever!")
        private val diamondPickaxe = createItem(Material.DIAMOND_PICKAXE, "EFFICIENCY", "Make your tools", "go NNYYYYUUUU!!!")
        private val feather = createItem(Material.FEATHER, "MOVEMENTSPEED", "WIP")
        val inventory = createInventory(
                null,
                "Choose Upgrade",
                Triple(anvil, 1, -2),
                Triple(diamondPickaxe, 1, 0),
                Triple(feather, 1, 2),
        )
}

object UpgradeClassMenu {
        private val helmet = createItem(Material.NETHERITE_HELMET, "Upgrade Helmet")
        private val chestplate = createItem(Material.NETHERITE_CHESTPLATE, "Upgrade Chestplate")
        private val leggings = createItem(Material.NETHERITE_LEGGINGS, "Upgrade Leggings")
        private val boots = createItem(Material.NETHERITE_BOOTS, "Upgrade Boots")
        private val sword = createItem(Material.NETHERITE_SWORD, "Upgrade Sword")
        private val shovel = createItem(Material.NETHERITE_SHOVEL, "Upgrade Shovel")
        private val pickaxe = createItem(Material.NETHERITE_PICKAXE, "Upgrade Pickaxe")
        private val axe = createItem(Material.NETHERITE_AXE, "Upgrade Axe")
        private val hoe = createItem(Material.NETHERITE_HOE, "Upgrade Hoe")
        private val elytra = createItem(Material.ELYTRA, "Upgrade Elytra")
        private val bow = createItem(Material.BOW, "Upgrade Bow")
        private val crossbow = createItem(Material.CROSSBOW, "Upgrade Crossbow")
        private val trident = createItem(Material.TRIDENT, "Upgrade Trident")
        private val shears = createItem(Material.SHEARS, "Upgrade Shears")
        val unbreakableInventory = createInventory(
                null,
                "Choose Tool to Upgrade",
                Triple(helmet, 1, -3),
                Triple(chestplate, 1, -1),
                Triple(leggings, 1, 1),
                Triple(boots, 1, 3),
                Triple(sword, 2, -4),
                Triple(shovel, 2, -2),
                Triple(pickaxe, 2, 0),
                Triple(axe, 2, 2),
                Triple(hoe, 2, 4),
                Triple(bow, 3, -3),
                Triple(elytra, 3, -1),
                Triple(shears, 3, 0),
                Triple(trident, 3, 1),
                Triple(crossbow, 3, 3),
        )
        val efficiencyInventory = createInventory(
                null,
                "Choose Tool to Upgrade",
                Triple(shovel, 1, -3),
                Triple(pickaxe, 1, -1),
                Triple(shears, 1, 0),
                Triple(axe, 1, 1),
                Triple(hoe, 1, 3),
        )
}


object UpgradeTierMenu {
        private val mkI = createItem(Material.IRON_BLOCK, "1","MKI")
        private val mkII = createItem(Material.GOLD_BLOCK, "2", "MKII")
        private val mkIII = createItem(Material.DIAMOND_BLOCK, "3", "MKIII")
        private val mkIV = createItem(Material.EMERALD_BLOCK, "4", "MKIV")
        private val mkV = createItem(Material.NETHERITE_BLOCK, "5", "MKV")
        val inventory = createInventory(
                null,
                "Choose Tier",
                Triple(mkI, 1, -4),
                Triple(mkII, 1, -2),
                Triple(mkIII, 1, 0),
                Triple(mkIV, 1, 2),
                Triple(mkV, 1, 4),
        )
}
