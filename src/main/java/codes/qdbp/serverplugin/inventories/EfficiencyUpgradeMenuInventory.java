package codes.qdbp.serverplugin.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class EfficiencyUpgradeMenuInventory {

    Inventory efficiencyUpgradeMenuInventory;

    public EfficiencyUpgradeMenuInventory() {
        this.efficiencyUpgradeMenuInventory = Bukkit.createInventory(null, 9, Component.text("Efficiency Upgrades"));

        //Create Items for this Inventory
        ItemStack netheriteShovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemStack netheritePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE);
        ItemStack netheriteHoe = new ItemStack(Material.NETHERITE_HOE);

        //Create Descriptions on Items with ItemMeta
        ItemMeta netheriteShovelMeta = netheriteShovel.getItemMeta();
        netheriteShovelMeta.displayName(Component.text("Upgrade Shovel"));
        netheriteShovel.setItemMeta(netheriteShovelMeta);

        ItemMeta netheritePickaxeMeta = netheritePickaxe.getItemMeta();
        netheritePickaxeMeta.displayName(Component.text("Upgrade Pickaxe"));
        netheritePickaxe.setItemMeta(netheritePickaxeMeta);

        ItemMeta netheriteAxeMeta = netheriteAxe.getItemMeta();
        netheriteAxeMeta.displayName(Component.text("Upgrade Axe"));
        netheriteAxe.setItemMeta(netheriteAxeMeta);

        ItemMeta netheriteHoeMeta = netheriteHoe.getItemMeta();
        netheriteHoeMeta.displayName(Component.text("Upgrade Hoe"));
        netheriteHoe.setItemMeta(netheriteHoeMeta);

        //Set items in correct slots from inventory
        this.efficiencyUpgradeMenuInventory.setItem(1, netheriteShovel);
        this.efficiencyUpgradeMenuInventory.setItem(3, netheritePickaxe);
        this.efficiencyUpgradeMenuInventory.setItem(5, netheriteAxe);
        this.efficiencyUpgradeMenuInventory.setItem(7, netheriteHoe);
    }

    public Inventory getEfficiencyUpgradeMenuInventory() {
        return efficiencyUpgradeMenuInventory;
    }
}
