package codes.qdbp.serverplugin.listeners

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.block.data.type.Door
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.block.BlockFace.*
import org.bukkit.block.ShulkerBox
import org.bukkit.block.data.Openable
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.meta.BlockStateMeta

class PlayerDoorInteractListener : Listener {

    @EventHandler
    fun onPlayerDoorInteract(event: PlayerInteractEvent) {


        if (event.player.inventory.itemInMainHand.type.toString().contains("SHULKER_BOX")) {

            if (event.action.isRightClick && event.clickedBlock?.type == null) {
                val shulkerMeta = event.player.inventory.itemInMainHand.itemMeta as BlockStateMeta
                val shulker = shulkerMeta.blockState as ShulkerBox
                val inventory = Bukkit.createInventory(event.player, InventoryType.SHULKER_BOX, Component.text("Shulker Manual"))
                inventory.contents = shulker.inventory.contents
                event.player.openInventory(inventory)
            }
        }

        if (!(event.action.isRightClick)) return
        val clickedBlock: Block = event.clickedBlock ?: return
        if (!(clickedBlock.type.toString().contains("DOOR"))) return
        if (clickedBlock.type.toString().contains("TRAP")) return
        if (clickedBlock.type.toString().contains("IRON")) return

        val clickedDoor = clickedBlock.blockData as Door
        var relativeBlock: Block
        val relativeDoor: Door

        when (clickedDoor.facing) {
            NORTH, SOUTH -> {
                relativeBlock = clickedBlock.getRelative(-1, 0, 0)
                when {
                    relativeBlock.type.toString().matches(Regex(".*DOOR*.")) -> {}
                    clickedBlock.getRelative(1, 0, 0).type.toString().matches(Regex(".*DOOR*.")) -> {
                        relativeBlock = clickedBlock.getRelative(1, 0, 0)
                    }
                    else -> return
                }
                relativeDoor = relativeBlock.blockData as Door
                if (relativeDoor.facing == WEST || relativeDoor.facing == EAST) return
                if (relativeDoor.hinge == clickedDoor.hinge) return
                if (relativeDoor.half != clickedDoor.half) return
            }

            WEST, EAST -> {
                relativeBlock = clickedBlock.getRelative(0, 0, -1)
                when {
                    relativeBlock.type.toString().matches(Regex(".*DOOR*.")) -> {}
                    clickedBlock.getRelative(0, 0, 1).type.toString().matches(Regex(".*DOOR*.")) -> {
                        relativeBlock = clickedBlock.getRelative(0, 0, 1)
                    }
                    else -> return
                }

                relativeDoor = relativeBlock.blockData as Door
                if (relativeDoor.facing == NORTH || relativeDoor.facing == SOUTH) return
                if (relativeDoor.hinge == clickedDoor.hinge) return
                if (relativeDoor.half != clickedDoor.half) return
            }
            else -> return
        }

        val relativeBlockState = relativeBlock.state
        val relativeDoorOpenable = relativeBlockState.blockData as Openable
        val clickedDoorOpenable = clickedBlock.blockData as Openable

        if (clickedDoorOpenable.isOpen) {
            relativeDoorOpenable.isOpen = false
            relativeBlockState.blockData = relativeDoorOpenable
            relativeBlockState.update()
        } else {
            relativeDoorOpenable.isOpen = true
            relativeBlockState.blockData = relativeDoorOpenable
            relativeBlockState.update()
        }

    }
}