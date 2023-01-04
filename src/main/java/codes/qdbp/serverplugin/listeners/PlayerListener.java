package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.SleepForward;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerListener implements Listener {

    private final Serverplugin plugin;

    public PlayerListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerGetIntoBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        new SleepForward(player, false).runTaskTimer(plugin, 0, 0);
    }

}
