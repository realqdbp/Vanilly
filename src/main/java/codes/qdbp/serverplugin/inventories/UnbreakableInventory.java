package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.misc.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UnbreakableInventory {

    Inventory unbreakableInventory;

    public UnbreakableInventory() {
        unbreakableInventory = Bukkit.createInventory(null, 27, Component.text("--- Unbreakable Upgrades"));


        ItemStack helmet = Utils.createItem(Material.NETHERITE_HELMET, "Upgrade Helmet");
        ItemStack chestplate = Utils.createItem(Material.NETHERITE_CHESTPLATE, "Upgrade Chestplate");
        ItemStack leggings = Utils.createItem(Material.NETHERITE_LEGGINGS, "Upgrade Leggings");
        ItemStack boots = Utils.createItem(Material.NETHERITE_BOOTS, "Upgrade Boots");
        ItemStack sword = Utils.createItem(Material.NETHERITE_SWORD, "Upgrade Sword");
        ItemStack shovel = Utils.createItem(Material.NETHERITE_SHOVEL, "Upgrade Shovel");
        ItemStack pickaxe = Utils.createItem(Material.NETHERITE_PICKAXE, "Upgrade Pickaxe");
        ItemStack axe = Utils.createItem(Material.NETHERITE_AXE, "Upgrade Axe");
        ItemStack hoe = Utils.createItem(Material.NETHERITE_HOE, "Upgrade Hoe");
        ItemStack elytra = Utils.createItem(Material.ELYTRA, "Upgrade Elytra");

        unbreakableInventory.setItem(1, helmet);
        unbreakableInventory.setItem(3, chestplate);
        unbreakableInventory.setItem(5, leggings);
        unbreakableInventory.setItem(7, boots);
        unbreakableInventory.setItem(9, sword);
        unbreakableInventory.setItem(11, shovel);
        unbreakableInventory.setItem(13, pickaxe);
        unbreakableInventory.setItem(15, axe);
        unbreakableInventory.setItem(17, hoe);
        unbreakableInventory.setItem(22, elytra);

    }

    public Inventory getUnbreakableInventory() {
        return unbreakableInventory;
    }
}
