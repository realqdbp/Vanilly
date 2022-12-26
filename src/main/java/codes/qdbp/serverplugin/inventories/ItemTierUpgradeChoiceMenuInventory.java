package codes.qdbp.serverplugin.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemTierUpgradeChoiceMenuInventory {

    Inventory itemTierUpgradeChoiceMenuInventory;

    public ItemTierUpgradeChoiceMenuInventory() {

        this.itemTierUpgradeChoiceMenuInventory = Bukkit.createInventory(null, 9, Component.text("Choice Upgrade Tier"));

        //Create Items for this Inventory
        ItemStack ironTierItem = new ItemStack(Material.IRON_BLOCK);
        ItemStack goldTierItem = new ItemStack(Material.GOLD_BLOCK);
        ItemStack diamondTierItem = new ItemStack(Material.DIAMOND_BLOCK);
        ItemStack emeraldTierItem = new ItemStack(Material.EMERALD_BLOCK);
        ItemStack netheriteTierItem = new ItemStack(Material.NETHERITE_BLOCK);

        //Create Descriptions on Items with ItemMeta
        ItemMeta ironTierItemMeta = ironTierItem.getItemMeta();
        ironTierItemMeta.displayName(Component.text("Upgrade to MK6"));
        ironTierItemMeta.lore(setItemDescription(6));
        ironTierItem.setItemMeta(ironTierItemMeta);

        ItemMeta goldTierItemMeta = goldTierItem.getItemMeta();
        goldTierItemMeta.displayName(Component.text("Upgrade to MK7"));
        goldTierItemMeta.lore(setItemDescription(7));
        goldTierItem.setItemMeta(goldTierItemMeta);

        ItemMeta diamondTierItemMeta = diamondTierItem.getItemMeta();
        diamondTierItemMeta.displayName(Component.text("Upgrade to MK8"));
        diamondTierItemMeta.lore(setItemDescription(8));
        diamondTierItem.setItemMeta(diamondTierItemMeta);

        ItemMeta emeraldTierItemMeta = emeraldTierItem.getItemMeta();
        emeraldTierItemMeta.displayName(Component.text("Upgrade to MK9"));
        emeraldTierItemMeta.lore(setItemDescription(9));
        emeraldTierItem.setItemMeta(emeraldTierItemMeta);

        ItemMeta netheriteTierItemMeta = netheriteTierItem.getItemMeta();
        netheriteTierItemMeta.displayName(Component.text("Upgrade to MK10"));
        netheriteTierItemMeta.lore(setItemDescription(10));
        netheriteTierItem.setItemMeta(netheriteTierItemMeta);

        //Set items in correct slots from inventory
        this.itemTierUpgradeChoiceMenuInventory.setItem(0, ironTierItem);
        this.itemTierUpgradeChoiceMenuInventory.setItem(2, goldTierItem);
        this.itemTierUpgradeChoiceMenuInventory.setItem(4, diamondTierItem);
        this.itemTierUpgradeChoiceMenuInventory.setItem(6, emeraldTierItem);
        this.itemTierUpgradeChoiceMenuInventory.setItem(8, netheriteTierItem);
    }

    private List<Component> setItemDescription (int tier) {
        return switch (tier) {
            case 6 -> List.of(
                    Component.text("REQUIREMENTS:"),
                    Component.text("The tool x 1 with Efficiency Upgrade " + (tier - 1)),
                    Component.text("Netherite Ingot x 1"),
                    Component.text("Feather x 4 (Stacked)"),
                    Component.text("Speed Potion MK1"),
                    Component.text("Empty Inventory Slot x 1")
            );
            case 7 -> List.of(
                    Component.text("REQUIREMENTS:"),
                    Component.text("The tool x 1 with Efficiency Upgrade " + (tier - 1)),
                    Component.text("Netherite Ingot x 1"),
                    Component.text("Feather x 8 (Stacked)"),
                    Component.text("Speed Potion MK2"),
                    Component.text("Empty Inventory Slot x 1")
            );
            case 8 -> List.of(
                    Component.text("REQUIREMENTS:"),
                    Component.text("The tool x 1 with Efficiency Upgrade " + (tier - 1)),
                    Component.text("Netherite Ingot x 1"),
                    Component.text("Feather x 16 (Stacked)"),
                    Component.text("Speed Potion MK2"),
                    Component.text("Honey x 4 (Stacked)"),
                    Component.text("Empty Inventory Slot x 1")
            );
            case 9 -> List.of(
                    Component.text("REQUIREMENTS:"),
                    Component.text("The tool x 1 with Efficiency Upgrade " + (tier - 1)),
                    Component.text("Netherite Ingot x 1"),
                    Component.text("Feather x 32 (Stacked)"),
                    Component.text("Speed Potion MK3"),
                    Component.text("Honey x 8 (Stacked)"),
                    Component.text("End Crystal x 1"),
                    Component.text("Empty Inventory Slot x 1")
            );
            case 10 -> List.of(
                    Component.text("REQUIREMENTS:"),
                    Component.text("The tool x 1 with Efficiency Upgrade " + (tier - 1)),
                    Component.text("Netherite Ingot x 1"),
                    Component.text("Feather x 64 (Stacked)"),
                    Component.text("Speed Potion MK3"),
                    Component.text("Honey x 16 (Stacked)"),
                    Component.text("End Crystal x 3"),
                    Component.text("Nether Star x 1"),
                    Component.text("Empty Inventory Slot x 1")
            );
            default -> null;
        };
    }

    public Inventory getItemTierUpgradeChoiceMenuInventory() {
        return itemTierUpgradeChoiceMenuInventory;
    }
}
