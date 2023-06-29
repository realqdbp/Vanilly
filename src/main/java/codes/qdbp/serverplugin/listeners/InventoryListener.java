package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.inventories.PlayerDeathInventory;
import codes.qdbp.serverplugin.misc.Upgrades;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public class InventoryListener implements Listener {

    private final Serverplugin plugin;


    public InventoryListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        Component inventoryTitle = event.getView().title().compact();

        if (inventoryTitle.equals(Component.text("--- Death Overview"))) {
            event.setCancelled(true);
            handleDeathInventory(player, event.getCurrentItem());
        } else if (inventoryTitle.toString().contains(" deaths")) {
            event.setCancelled(true);
        }
    }


    private void handleDeathInventory(Player player, ItemStack clickedItem) {
        player.closeInventory();
        player.openInventory(new PlayerDeathInventory(clickedItem, plugin).getPlayerDeathInventory());
    }




}
