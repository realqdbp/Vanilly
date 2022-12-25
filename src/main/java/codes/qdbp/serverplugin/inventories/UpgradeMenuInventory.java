package codes.qdbp.serverplugin.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeMenuInventory {

    Inventory upgradeMenuInventory;

    public UpgradeMenuInventory() {
        this.upgradeMenuInventory = Bukkit.createInventory(null, 9, Component.text("Upgrade Menu"));

        //Item that transfers to unbreakable upgrade menu
        ItemStack unbreakableUpgradeMenuItem = new ItemStack(Material.ANVIL);
        unbreakableUpgradeMenuItem.getItemMeta().displayName(Component.text("Upgrade to UNBREAKABLE"));

        //Item that transfers to fast digging upgrade menu
        ItemStack fastDiggingUpgradeMenuItem = new ItemStack(Material.NETHERITE_PICKAXE);
        fastDiggingUpgradeMenuItem.getItemMeta().displayName(Component.text("Upgrade to FASTER DIGGING"));

        //Item that transfers to running speed upgrade menu
        ItemStack movementSpeedUpgradeMenuItem = new ItemStack(Material.FEATHER);
        movementSpeedUpgradeMenuItem.getItemMeta().displayName(Component.text("Upgrade to FASTER MOVEMENT SPEED"));

        //Set items to inventory
        this.upgradeMenuInventory.setItem(2, unbreakableUpgradeMenuItem);
        this.upgradeMenuInventory.setItem(4, fastDiggingUpgradeMenuItem);
        this.upgradeMenuInventory.setItem(6, movementSpeedUpgradeMenuItem);
    }

    public Inventory getUpgradeMenuInventory() {
        return upgradeMenuInventory;
    }
}
