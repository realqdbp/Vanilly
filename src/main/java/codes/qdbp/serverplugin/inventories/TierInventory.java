package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.misc.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TierInventory {

    Inventory tierInventory;

    public TierInventory() {

        tierInventory = Bukkit.createInventory(null, 9, Component.text("--- Choose Tier"));

        ItemStack mk1 = Utils.createItem(Material.IRON_BLOCK, "MK1");
        ItemStack mk2 = Utils.createItem(Material.GOLD_BLOCK, "MK2");
        ItemStack mk3 = Utils.createItem(Material.DIAMOND_BLOCK, "MK3");
        ItemStack mk4 = Utils.createItem(Material.EMERALD_BLOCK, "MK4");
        ItemStack mk5 = Utils.createItem(Material.NETHERITE_BLOCK, "MK5");

        tierInventory.setItem(0, mk1);
        tierInventory.setItem(2, mk2);
        tierInventory.setItem(4, mk3);
        tierInventory.setItem(6, mk4);
        tierInventory.setItem(8, mk5);
    }


    public Inventory getTierInventory() {
        return tierInventory;
    }
}
