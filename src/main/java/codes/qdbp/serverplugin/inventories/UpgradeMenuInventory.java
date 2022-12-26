package codes.qdbp.serverplugin.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UpgradeMenuInventory {

    Inventory upgradeMenuInventory;

    public UpgradeMenuInventory() {
        this.upgradeMenuInventory = Bukkit.createInventory(null, 9, Component.text("Upgrade Menu"));

        //Item that transfers to unbreakable upgrade menu
        ItemStack unbreakableUpgradeMenuItem = new ItemStack(Material.ANVIL);
        ItemMeta unbreakableUpgradeMenuItemMeta = unbreakableUpgradeMenuItem.getItemMeta();
        unbreakableUpgradeMenuItemMeta.displayName(Component.text("Upgrade to UNBREAKABLE"));
        unbreakableUpgradeMenuItemMeta.lore(List.of(
                Component.text("Make your tools"),
                Component.text("last forever!")
        ));
        unbreakableUpgradeMenuItem.setItemMeta(unbreakableUpgradeMenuItemMeta);


        //Item that transfers to fast digging upgrade menu
        ItemStack efficiencyUpgradeMenuItem = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta efficiencyUpgradeMenuItemMeta = efficiencyUpgradeMenuItem.getItemMeta();
        efficiencyUpgradeMenuItemMeta.displayName(Component.text("Upgrade to FASTER DIGGING"));
        efficiencyUpgradeMenuItemMeta.lore(List.of(
                Component.text("Make your tools"),
                Component.text("dig even faster!")
        ));
        efficiencyUpgradeMenuItem.setItemMeta(efficiencyUpgradeMenuItemMeta);


        //Item that transfers to running speed upgrade menu
        ItemStack movementSpeedUpgradeMenuItem = new ItemStack(Material.FEATHER);
        ItemMeta movementSpeedUpgradeMenuItemMeta = movementSpeedUpgradeMenuItem.getItemMeta();
        movementSpeedUpgradeMenuItemMeta.displayName(Component.text("Upgrade to FASTER MOVEMENT SPEED"));
        movementSpeedUpgradeMenuItemMeta.lore(List.of(
                Component.text("Makes your movement"),
                Component.text("even faster")
        ));
        movementSpeedUpgradeMenuItem.setItemMeta(movementSpeedUpgradeMenuItemMeta);

        //Set items to inventory
        this.upgradeMenuInventory.setItem(2, unbreakableUpgradeMenuItem);
        this.upgradeMenuInventory.setItem(4, efficiencyUpgradeMenuItem);
        this.upgradeMenuInventory.setItem(6, movementSpeedUpgradeMenuItem);
    }

    public Inventory getUpgradeMenuInventory() {
        return upgradeMenuInventory;
    }
}
