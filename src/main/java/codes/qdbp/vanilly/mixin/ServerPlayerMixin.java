package codes.qdbp.vanilly.mixin;

import codes.qdbp.vanilly.AFKKt;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(at = @At("HEAD"), method = "resetLastActionTime")
    private void onResetLastActionTime(CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        if (!AFKKt.isAFK(player)) return;
        AFKKt.endAFK(player);
    }
}
