package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener


class PlayerItemFrameChange(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onPlayerItemFrameChange(event: PlayerItemFrameChangeEvent) {

        val itemFrame = event.itemFrame

        when (event.action) {
            PlayerItemFrameChangeEvent.ItemFrameChangeAction.PLACE -> {
                if (itemFrame.persistentDataContainer.has(plugin.invisItemFrameNSK)) {
                    itemFrame.isVisible = false
                }
            }

            PlayerItemFrameChangeEvent.ItemFrameChangeAction.REMOVE -> {
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