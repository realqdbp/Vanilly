package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.AFKPlayerTimeUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickListener implements Listener {

    private final Serverplugin plugin;

    public PlayerKickListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (event.reason().toString().contains("multiplayer.disconnect.idling")) {
            event.setCancelled(true);

            Player player = event.getPlayer();

            if (!Serverplugin.afkPlayerList.contains(player.getUniqueId())) {
                Serverplugin.afkPlayerList.add(player.getUniqueId());
                player.setInvulnerable(true);
                AFKPlayerTimeUtility.justTime(plugin, player);
                player.sendMessage("You're now afk");
            }
        } else {
            event.setCancelled(false);
        }
    }

}
