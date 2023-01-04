package codes.qdbp.serverplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class PlayerItemConsumeListener implements Listener {

    private final HashMap<Material, List<Float>> foodMap;

    public PlayerItemConsumeListener(HashMap<Material, List<Float>> foodMap) {
        this.foodMap = foodMap;
    }


    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();

        if (foodMap.containsKey(item.getType()) && item.getAmount() > 1) {

            float foodlevel = foodMap.get(player.getActiveItem().getType()).get(0);
            float saturation = foodMap.get(player.getActiveItem().getType()).get(1);

            while (item.getAmount() > 0 && player.getFoodLevel() < 20) {
                player.setFoodLevel((int) (player.getFoodLevel() + foodlevel));
                player.setSaturation(player.getSaturation() + saturation);
                item.setAmount(item.getAmount() - 1);
            }

            event.setCancelled(true);
        }
    }

}
