package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener(val plugin: Serverplugin) : Listener {


    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player

        if (freecamPlayers.containsKey(player.uniqueId)) {
            player.endFreecam()
        }

        if (afkPlayers.containsKey(player.uniqueId)) {
            player.endAfk()
        }
    }
}