package codes.qdbp.vanilly.mixin;

import codes.qdbp.vanilly.mixincode.TickAdjacentLeaves;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void tickAdjacent(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
        TickAdjacentLeaves.INSTANCE.tickAdjacentLeaves(level, pos);
    }
}