package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.Statistic
import org.bukkit.entity.Player
import org.bukkit.entity.Pose
import org.bukkit.scheduler.BukkitRunnable

fun Player.sleepForward(plugin: Serverplugin, byCommand: Boolean) {

    object : BukkitRunnable() {
        val player = this@sleepForward

        override fun run() {
            val sleepingPlayers = player.world.players.filter { it.pose == Pose.SLEEPING }
            if (13000 >= player.world.time) {
                player.setStatistic(Statistic.TIME_SINCE_REST, 0)
                if (!(player.world.isClearWeather)) player.world.clearWeatherDuration = 1
                cancel()
                return
            } else if (player.world.isThundering) {
                if (player.world.time > 23800) {
                    player.setStatistic(Statistic.TIME_SINCE_REST, 0)
                    if (!(player.world.isClearWeather)) player.world.clearWeatherDuration = 1
                    cancel()
                    return
                }
            } else if (sleepingPlayers.isEmpty() && !byCommand) {
                cancel()
                return
            }

            if (player.world.time < 23940) player.world.time += 50
        }
    }.runTaskTimer(plugin, 0, 0)
}