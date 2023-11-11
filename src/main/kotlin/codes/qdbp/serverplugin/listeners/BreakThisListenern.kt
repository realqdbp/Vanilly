package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.createInvisTaggedFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.hanging.HangingBreakEvent

class BreakThisListenern(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onBreak(event: HangingBreakEvent) {
        val entity = event.entity

        if (entity.persistentDataContainer.has(plugin.invisItemFrameNSK)) {
            event.isCancelled = true


            entity.world.dropItem(entity.location, createInvisTaggedFrame(plugin))
            entity.remove()
        }


    }

}