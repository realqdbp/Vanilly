package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.misc.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeInventory {

    Inventory upgradeInventory;

    public UpgradeInventory() {
        upgradeInventory = Bukkit.createInventory(null, 9, Component.text("--- Upgrade Menu"));


        ItemStack unbreakableUpgradeItem = Utils.createItem(Material.ANVIL, "Upgrade to UNBREAKABLE", Component.text("Make your tools"), Component.text("last forever!"));

        ItemStack efficiencyUpgradeItem = Utils.createItem(Material.DIAMOND_PICKAXE, "Upgrade to FASTER DIGGING", Component.text("WIP"));

        ItemStack movementspeedUpgradeItem = Utils.createItem(Material.FEATHER, "Upgrade to FASTER MOVEMENT", Component.text("WIP"));

        upgradeInventory.setItem(2, unbreakableUpgradeItem);
        upgradeInventory.setItem(4, efficiencyUpgradeItem);
        upgradeInventory.setItem(6, movementspeedUpgradeItem);
    }

    public Inventory getUpgradeInventory() {
        return upgradeInventory;
    }
}
