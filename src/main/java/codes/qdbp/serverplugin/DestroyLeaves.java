package codes.qdbp.serverplugin;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class DestroyLeaves {

    public static void destroyLeaves(Block block) {
        Bukkit.getScheduler().runTaskLater(Serverplugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (block.getRelative(BlockFace.UP).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.UP).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.UP));
                }
                if (block.getRelative(BlockFace.DOWN).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.DOWN).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.DOWN));
                }
                if (block.getRelative(BlockFace.NORTH).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.NORTH).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.NORTH));
                }
                if (block.getRelative(BlockFace.SOUTH).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.SOUTH).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.SOUTH));
                }
                if (block.getRelative(BlockFace.WEST).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.WEST).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.WEST));
                }
                if (block.getRelative(BlockFace.EAST).getType().toString().toLowerCase().contains("leave")) {
                    block.getRelative(BlockFace.EAST).breakNaturally(true);
                    destroyLeaves(block.getRelative(BlockFace.EAST));
                }

                //if no block found, look for most top leave block
                for (int i = 1; i <= 15; ++i) {
                    if (block.getRelative(BlockFace.UP, i).getType().toString().toLowerCase().contains("leave")) destroyLeaves(block.getRelative(BlockFace.UP, i-1));
                }
            }
        }, 10);
    }

    public static boolean noLogsInWay(Block block, int distance) {
        boolean answer = true;

        for (int i = 1; i <= distance; ++i) {
            if (block.getRelative(BlockFace.UP, i).getType().toString().toLowerCase().contains("log")) answer = false;
            if (block.getRelative(BlockFace.DOWN, i).getType().toString().toLowerCase().contains("log")) answer = false;
        }

        return answer;
    }
}
