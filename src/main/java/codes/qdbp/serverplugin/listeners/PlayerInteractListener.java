package codes.qdbp.serverplugin.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();

        ItemStack eventItem = event.getItem();
        if (eventItem == null) return;
        if (eventItem.equals(player.getInventory().getItemInOffHand())) return;
        if (!(eventItem.getType().equals(Material.ELYTRA) || eventItem.getType().toString().toLowerCase().contains("chestplate"))) return;

        ItemStack chestPlateSlotItem = player.getInventory().getChestplate();
        if (eventItem.getType().equals(Material.ELYTRA)) {
            if (chestPlateSlotItem != null && !chestPlateSlotItem.getType().equals(Material.ELYTRA)) {
                player.getInventory().setChestplate(eventItem);
                player.getInventory().setItemInMainHand(chestPlateSlotItem);
            }
        } else {
            if (eventItem.getEnchantments().containsKey(Enchantment.BINDING_CURSE)) return;
            if (chestPlateSlotItem != null && chestPlateSlotItem.getType().equals(Material.ELYTRA)) {
                player.getInventory().setChestplate(eventItem);
                player.getInventory().setItemInMainHand(chestPlateSlotItem);
            }
        }
    }

}
