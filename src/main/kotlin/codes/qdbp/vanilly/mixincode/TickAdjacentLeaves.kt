package codes.qdbp.vanilly.mixincode

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties.DISTANCE
import net.minecraft.world.level.block.state.properties.BlockStateProperties.PERSISTENT

object TickAdjacentLeaves {
    private val decayingLeaves = ArrayDeque<Pair<ServerLevel, BlockPos>>()

    fun tickAdjLeaves(level: ServerLevel, position: BlockPos) {
        val state = level.getBlockState(position)
        if (decayingLeaves.contains(level to position)) return
        if (!(state.block is LeavesBlock && !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7)) return

        decayingLeaves.add(level to position)

        Direction.entries.forEach { tickAdjLeaves(level, position.relative(it)) }
    }

    fun decayLeaves() {
        val (level, position) = decayingLeaves.removeFirstOrNull() ?: return
        level.destroyBlock(position, true)
    }
}