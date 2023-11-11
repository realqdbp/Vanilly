package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.logging.Level


class PlayerItemFrameChange(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onPlayerItemFrameChange(event: PlayerItemFrameChangeEvent) {

        val itemFrame = event.itemFrame

        plugin.logger.log(Level.INFO, "here")
        when (event.action) {
            PlayerItemFrameChangeEvent.ItemFrameChangeAction.PLACE -> {
                plugin.logger.log(Level.INFO, "place")
                if (itemFrame.persistentDataContainer.has(plugin.invisItemFrameNSK)) {
                    itemFrame.isVisible = false
                }
            }

            PlayerItemFrameChangeEvent.ItemFrameChangeAction.REMOVE -> {
                plugin.logger.log(Level.INFO, "remove")
                if (itemFrame.persistentDataContainer.has(plugin.invisItemFrameNSK)) {
                    itemFrame.isVisible = true
                }
            }

            PlayerItemFrameChangeEvent.ItemFrameChangeAction.ROTATE -> {
                return
            }
        }
    }
}