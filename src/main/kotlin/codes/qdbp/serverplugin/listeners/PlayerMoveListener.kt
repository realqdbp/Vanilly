package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.misc.afkPlayers
import codes.qdbp.serverplugin.misc.endAfk
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class PlayerMoveListener : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        if (afkPlayers.containsKey(player.uniqueId)) {
            val time = afkPlayers[player.uniqueId]?.afkTime?.toDuration(DurationUnit.SECONDS)?.toComponents { hours, minutes, seconds, _ -> "${hours}h:${minutes}m:${seconds}s" } ?: ""
            player.sendMessage("Du warst $time afk.")
            player.endAfk()
        }
    }
}