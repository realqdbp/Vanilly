package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class AfkPlayer(
    val player: Player,
    var reason: String,
    private val plugin: Serverplugin
){
    var afkTime = 0
    val afkTask = object: BukkitRunnable(){
        override fun run() {
            ++afkTime
            val time = afkTime.toDuration(DurationUnit.SECONDS).toComponents { hours, minutes, seconds, _ -> "${hours}h:${minutes}m:${seconds}s" }
            player.playerListName(
                text(player.name, color(0xe9c46a))
                    .append(text(" [", color(0x264653)))
                    .append(text("AFK", color(0x2a9d8f)))
                    .append(text(" - ", color(0x264653)))
                    .append(text(time, color(0x2a9d8f)))
                    .append(text(reason, color(0x2a9d8f)))
                    .append(text("]", color(0x264653)))
            )
        }
    }

    init {
        player.isInvulnerable = true
        player.isCollidable = false

        afkTask.runTaskTimer(plugin, 0, 20)
        player.sendPlainMessage("Du bist jetzt AFK!")
    }
}