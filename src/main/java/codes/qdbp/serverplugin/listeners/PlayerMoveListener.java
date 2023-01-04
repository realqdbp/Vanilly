package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Serverplugin.afkPlayerList.contains(player.getUniqueId())) {
            player.setInvulnerable(false);
            player.sendMessage("Du bist nicht mehr AFK");
            Serverplugin.afkPlayerList.remove(player.getUniqueId());
            Bukkit.getScheduler().cancelTask(Serverplugin.afkPlayerRunningTasksMap.get(player.getUniqueId()));
            Serverplugin.afkPlayerRunningTasksMap.remove(player.getUniqueId());
            player.playerListName(null);
        }
    }
}
