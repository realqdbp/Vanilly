package codes.qdbp.serverplugin.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ConfirmInventory {

    Inventory confirmInventory;

    public ConfirmInventory(Component informationItemName, Material informationItemMaterial) {

        this.confirmInventory = Bukkit.createInventory(null, 9, Component.text("Confirm"));

        //Create Items for this Inventory
        ItemStack abort = new ItemStack(Material.RED_WOOL);
        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemStack informationItem = new ItemStack(informationItemMaterial);


        //Create Descriptions on Items with ItemMeta
        ItemMeta abortMeta = abort.getItemMeta();
        abortMeta.displayName(Component.text("ABORT"));
        abortMeta.lore(List.of(
                Component.text("This aborts the"),
                Component.text("current action.")
        ));
        abort.setItemMeta(abortMeta);

        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.displayName(Component.text("CONFIRM"));
        confirmMeta.lore(List.of(
                Component.text("This confirms the"),
                Component.text("current action.")
        ));
        confirm.setItemMeta(confirmMeta);

        ItemMeta informationItemMeta = informationItem.getItemMeta();
        informationItemMeta.displayName(informationItemName);
        informationItem.setItemMeta(informationItemMeta);

        //Set items into Inventory
        this.confirmInventory.setItem(0, abort);
        this.confirmInventory.setItem(1, abort);
        this.confirmInventory.setItem(2, abort);

        this.confirmInventory.setItem(4, informationItem);

        this.confirmInventory.setItem(6, confirm);
        this.confirmInventory.setItem(7, confirm);
        this.confirmInventory.setItem(8, confirm);
    }

    public Inventory getConfirmInventory() {
        return confirmInventory;
    }
}
