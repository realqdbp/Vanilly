package codes.qdbp.serverplugin.listeners

import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.data.type.Leaves
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.LeavesDecayEvent

class LeavesDecayListener : Listener {

    @EventHandler
    fun onLeavesDecay(event: LeavesDecayEvent) {
        val blocks = mutableSetOf<Block>()
        val blockX = event.block.x
        val blockY = event.block.y
        val blockZ = event.block.z
        val radius = 3
        (blockX-radius..blockX+radius).forEach { x ->
            (blockY-radius..blockY+radius).forEach { y ->
                (blockZ - radius..blockZ + radius).forEach { z ->
                    val block = Location(event.block.world, x.toDouble(), y.toDouble(), z.toDouble()).block
                    if (block.type.toString().contains("LEAVES")) blocks.add(block)
                }
            }
        }
        blocks.forEach {
            val leaves = it.blockData as Leaves
            if (!leaves.isPersistent && leaves.distance > 6) it.breakNaturally()
        }
    }
}