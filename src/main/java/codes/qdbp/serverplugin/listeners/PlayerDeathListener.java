package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class PlayerDeathListener implements Listener {

    private final Serverplugin plugin;

    public PlayerDeathListener(Serverplugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        plugin.getConfig().set("Player." + player.getName() + ".tode", plugin.getConfig().getInt("Player." + player.getName() + ".tode") + 1);
        plugin.saveConfig();
    }

}
