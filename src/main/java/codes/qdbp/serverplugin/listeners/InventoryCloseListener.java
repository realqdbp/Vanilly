package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class InventoryCloseListener implements Listener{

    private final Serverplugin plugin;

    public InventoryCloseListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        if (event.getView().title().equals(Component.text(player.getName() + "'s Backpack"))) {
            plugin.getConfig().set("Player." + player.getName() + ".backpackInventory", player.getOpenInventory().getTopInventory().getContents());
            plugin.saveConfig();
        }
    }

}
