package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.commands.FreecamCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (Serverplugin.freecamPlayerLocation.containsKey(player.getUniqueId())) {
            FreecamCommand.backportPlayer(player);
        } else if (Serverplugin.afkPlayerList.contains(player.getUniqueId())) {
            player.setInvulnerable(false);
            Serverplugin.afkPlayerList.remove(player.getUniqueId());
            Bukkit.getScheduler().cancelTask(Serverplugin.afkPlayerRunningTasksMap.get(player.getUniqueId()));
            Serverplugin.afkPlayerRunningTasksMap.remove(player.getUniqueId());
            player.playerListName(null);
        }
    }
}
