package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ConfirmInventory {

    Inventory confirmInventory;

    public ConfirmInventory(UUID uuid) {
        confirmInventory = Bukkit.createInventory(null, 27, Component.text("--- Confirm Transaction"));

        Upgrades upgrade = Serverplugin.menuUtilityMap.get(uuid).getCurrentUpgrade();
        UpgradeOption upgradeChoice = Serverplugin.menuUtilityMap.get(uuid).getCurrentUpgradeChoice();
        UpgradeTiers upgradeTier = UpgradeTiers.MK1;
        if (upgrade != Upgrades.UNBREAKABLE) upgradeTier = Serverplugin.menuUtilityMap.get(uuid).getCurrentUpgradeTier();

        ItemStack upgradeInformation = Utils.createItem(Material.OAK_SIGN, "Upgrade Information", Component.text(upgrade.toString()), Component.text(upgradeChoice.toString()), Component.text(upgradeTier.toString()), Component.text("Mirror the Items from Above, below them"));
        confirmInventory.setItem(4, upgradeInformation);

        switch (upgrade) {

            case MOVEMENTSPEED, EFFICIENCY -> {
                ItemStack wip = Utils.createItem(Material.BARRIER, "WIP");
                confirmInventory.setItem(13, wip);
            }

            case UNBREAKABLE -> {

                ItemStack itemDepot1;
                switch (upgradeChoice) {
                    case HELMET -> itemDepot1 = new ItemStack(Material.NETHERITE_HELMET);
                    case CHESTPLATE -> itemDepot1 = new ItemStack(Material.NETHERITE_CHESTPLATE);
                    case LEGGINGS -> itemDepot1 = new ItemStack(Material.NETHERITE_LEGGINGS);
                    case BOOTS -> itemDepot1 = new ItemStack(Material.NETHERITE_BOOTS);
                    case SWORD -> itemDepot1 = new ItemStack(Material.NETHERITE_SWORD);
                    case SHOVEL -> itemDepot1 = new ItemStack(Material.NETHERITE_SHOVEL);
                    case PICKAXE -> itemDepot1 = new ItemStack(Material.NETHERITE_PICKAXE);
                    case AXE -> itemDepot1 = new ItemStack(Material.NETHERITE_AXE);
                    case HOE -> itemDepot1 = new ItemStack(Material.NETHERITE_HOE);
                    case ELYTRA -> itemDepot1 = new ItemStack(Material.ELYTRA);
                    default -> {
                        return;
                    }
                }

                ItemStack itemDepot2 = new ItemStack(Material.NETHERITE_INGOT, 10);
                ItemStack itemDepot3 = new ItemStack(Material.NETHER_STAR);
                ItemStack itemDepot4 = new ItemStack(Material.OBSIDIAN, 32);
                ItemStack filler = Utils.createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "-");

                confirmInventory.setItem(10, itemDepot1);
                confirmInventory.setItem(12, itemDepot2);
                confirmInventory.setItem(14, itemDepot3);
                confirmInventory.setItem(16, itemDepot4);
                for (int i = 0; i <= 26; ++i) {
                    if (i == 4 ||
                            i == 10 || i == 12 || i == 14 || i == 16 ||
                            i == 19 || i == 21 || i == 23 || i == 25) continue;
                    confirmInventory.setItem(i, filler);
                }

            }

        }

    }

    public Inventory getConfirmInventory() {
        return confirmInventory;
    }
}
