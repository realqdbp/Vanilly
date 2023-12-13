package codes.qdbp.serverplugin.inventories

import codes.qdbp.serverplugin.misc.createInventory
import codes.qdbp.serverplugin.misc.createItem
import org.bukkit.Material


object UpgradeTypeMenu {
        val anvil = createItem(Material.ANVIL, "UNBREAKABLE", "Make your tools", "last forever!")
        val diamondPickaxe = createItem(Material.DIAMOND_PICKAXE, "HASTE", "WIP")
        val feather = createItem(Material.FEATHER, "MOVEMENTSPEED", "WIP")
        val inventory = createInventory(
                null,
                "Choose Upgrade",
                Triple(anvil, 1, -2),
                Triple(diamondPickaxe, 1, 0),
                Triple(feather, 1, 2),
        )
}


object UpgradeClassMenu {
        val helmet = createItem(Material.NETHERITE_HELMET, "Upgrade Helmet")
        val chestplate = createItem(Material.NETHERITE_CHESTPLATE, "Upgrade Chestplate")
        val leggings = createItem(Material.NETHERITE_LEGGINGS, "Upgrade Leggings")
        val boots = createItem(Material.NETHERITE_BOOTS, "Upgrade Boots")
        val sword = createItem(Material.NETHERITE_SWORD, "Upgrade Sword")
        val shovel = createItem(Material.NETHERITE_SHOVEL, "Upgrade Shovel")
        val pickaxe = createItem(Material.NETHERITE_PICKAXE, "Upgrade Pickaxe")
        val axe = createItem(Material.NETHERITE_AXE, "Upgrade Axe")
        val hoe = createItem(Material.NETHERITE_HOE, "Upgrade Hoe")
        val elytra = createItem(Material.ELYTRA, "Upgrade Elytra")
        val inventory = createInventory(
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
                Triple(elytra, 3, 0),
        )
}


object UpgradeTierMenu {
        val mkI = createItem(Material.IRON_BLOCK, "1","MKI")
        val mkII = createItem(Material.GOLD_BLOCK, "2", "MKII")
        val mkIII = createItem(Material.DIAMOND_BLOCK, "3", "MKIII")
        val mkIV = createItem(Material.EMERALD_BLOCK, "4", "MKIV")
        val mkV = createItem(Material.NETHERITE_BLOCK, "5", "MKV")
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
