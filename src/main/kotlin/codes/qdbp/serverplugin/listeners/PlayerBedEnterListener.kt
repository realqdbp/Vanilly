package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.sleepForward
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class PlayerBedEnterListener(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onPlayerBedEnter(event: PlayerBedEnterEvent) {
        event.player.sleepForward(plugin, false)
    }
}