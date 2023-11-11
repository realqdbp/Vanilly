package codes.qdbp.serverplugin.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;


public class PlayerDoorInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractWithDoor(PlayerInteractEvent event) {

        if (!(event.getAction().isRightClick())) return;
        if (event.getClickedBlock() == null) return;

        Block currentBlock = event.getClickedBlock();
        if (!(currentBlock.getType().toString().contains("DOOR"))) return;
        if (currentBlock.getType().toString().contains("IRON")) return;

        Door currentDoor = (Door) currentBlock.getBlockData();

        Block relativeBlock = currentBlock.getRelative(-1, 0, 0);
        Door relativeDoor;
        switch (currentDoor.getFacing()) {
            case NORTH -> {
                if (relativeBlock.getType().toString().matches(".*DOOR.*")) {
                } else if (currentBlock.getRelative(1, 0, 0).getType().toString().matches(".*DOOR.*")) {
                    relativeBlock = currentBlock.getRelative(1, 0, 0);
                } else {
                    return;
                }

                relativeDoor = (Door) relativeBlock.getBlockData();
                if (!(relativeDoor.getFacing().equals(BlockFace.NORTH))) return;
                if (relativeDoor.getHinge().equals(currentDoor.getHinge())) return;
                if (!(relativeDoor.getHalf().equals(currentDoor.getHalf()))) return;
            }
            case SOUTH -> {
                relativeBlock = currentBlock.getRelative(-1, 0, 0);

                if (relativeBlock.getType().toString().matches(".*DOOR.*")) {
                } else if (currentBlock.getRelative(1, 0, 0).getType().toString().matches(".*DOOR.*")) {
                    relativeBlock = currentBlock.getRelative(1, 0, 0);
                } else {
                    return;
                }


                relativeDoor = (Door) relativeBlock.getBlockData();
                if (!(relativeDoor.getFacing().equals(BlockFace.SOUTH))) return;
                if (relativeDoor.getHinge().equals(currentDoor.getHinge())) return;
                if (!(relativeDoor.getHalf().equals(currentDoor.getHalf()))) return;
            }
            case WEST -> {
                relativeBlock = currentBlock.getRelative(0, 0, -1);




                if (relativeBlock.getType().toString().matches(".*DOOR.*")) {
                } else if (currentBlock.getRelative(0, 0, 1).getType().toString().matches(".*DOOR.*")) {
                    relativeBlock = currentBlock.getRelative(0, 0, 1);
                } else {
                    return;
                }


                relativeDoor = (Door) relativeBlock.getBlockData();
                if (!(relativeDoor.getFacing().equals(BlockFace.WEST))) return;
                if (relativeDoor.getHinge().equals(currentDoor.getHinge())) return;
                if (!(relativeDoor.getHalf().equals(currentDoor.getHalf()))) return;

            }
            case EAST -> {
                relativeBlock = currentBlock.getRelative(0, 0, -1);


                if (relativeBlock.getType().toString().matches(".*DOOR.*")) {
                } else if (currentBlock.getRelative(0, 0, 1).getType().toString().matches(".*DOOR.*")) {
                    relativeBlock = currentBlock.getRelative(0, 0, 1);
                } else {
                    return;
                }

                relativeDoor = (Door) relativeBlock.getBlockData();
                if (!(relativeDoor.getFacing().equals(BlockFace.EAST))) return;
                if (relativeDoor.getHinge().equals(currentDoor.getHinge())) return;
                if (!(relativeDoor.getHalf().equals(currentDoor.getHalf()))) return;
            }
        }

        BlockState relativeBlockState = relativeBlock.getState();
        Openable relativeDoorOpenable = (Openable) relativeBlockState.getBlockData();


        Openable currentDoorOpenable = (Openable) currentBlock.getBlockData();
        if (currentDoorOpenable.isOpen()) {
            relativeDoorOpenable.setOpen(false);
            relativeBlockState.setBlockData(relativeDoorOpenable);
            relativeBlockState.update();
        } else if (!currentDoorOpenable.isOpen()) {
            relativeDoorOpenable.setOpen(true);
            relativeBlockState.setBlockData(relativeDoorOpenable);
            relativeBlockState.update();
        }
    }
}