package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.AfkPlayer
import codes.qdbp.serverplugin.misc.afkPlayers
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent

class PlayerKickListener(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onPlayerKick(event: PlayerKickEvent) {

        if (event.reason().toString().contains("multiplayer.disconnect.idling")) {
            event.isCancelled = true

            val player = event.player
            if (afkPlayers.containsKey(player.uniqueId)) return

            afkPlayers[player.uniqueId] = AfkPlayer(player, "", plugin)
        }
    }
}