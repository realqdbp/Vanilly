package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.persistence.PersistentDataType

class HangingPlaceEvent(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onHangingPlaceEvent(event: HangingPlaceEvent) {
        if (event.entity.type != EntityType.ITEM_FRAME) return
        if (event.itemStack?.itemMeta?.persistentDataContainer?.has(plugin.invisItemFrameNSK) ?: return) {
            event.entity.persistentDataContainer.set(plugin.invisItemFrameNSK, PersistentDataType.BYTE, 1)
        }
    }


}