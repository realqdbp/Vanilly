package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.misc.foodMap
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent

class PlayerItemConsumeListener : Listener {

    @EventHandler
    fun onPlayerItemConsume(event: PlayerItemConsumeEvent) {
        val player = event.player
        val item = player.activeItem

        if (!(foodMap.containsKey(item.type) && item.amount > 1)) return

        val foodLevel = foodMap[item.type]!![0] as Int
        val saturation = foodMap[item.type]!![1] as Float

        while (item.amount > 0 && player.foodLevel < 20) {
            player.foodLevel += foodLevel
            player.saturation += saturation
            --item.amount
        }
        event.isCancelled = true
    }
}