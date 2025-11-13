package codes.qdbp.vanilly.mixin;

import codes.qdbp.vanilly.AFKKt;
import net.minecraft.Util;
import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockChangedAckPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.TickThrottler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.TimeUnit;

// ServerGameblabla tick()
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class PlayerAfkKickMixin extends ServerCommonPacketListenerImpl {

    public PlayerAfkKickMixin(MinecraftServer minecraftServer, Connection connection, CommonListenerCookie commonListenerCookie) {
        super(minecraftServer, connection, commonListenerCookie);
    }

    @Shadow
    private int ackBlockChangesUpTo;

    @Shadow
    protected abstract boolean tickPlayer();

    @Shadow
    @Final
    private TickThrottler chatSpamThrottler;

    @Shadow
    @Final
    private TickThrottler dropSpamThrottler;

    @Shadow
    public ServerPlayer player;


    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void onPlayerAfkKick(CallbackInfo callbackInfo) {
        if (this.ackBlockChangesUpTo > -1) {
            this.send(new ClientboundBlockChangedAckPacket(this.ackBlockChangesUpTo));
            this.ackBlockChangesUpTo = -1;
        }

        if (this.server.isPaused() || !this.tickPlayer()) {
            this.keepConnectionAlive();
            this.chatSpamThrottler.tick();
            this.dropSpamThrottler.tick();
            if (this.player.getLastActionTime() > 0L
                    && this.server.playerIdleTimeout() > 0
                    && Util.getMillis() - this.player.getLastActionTime() > TimeUnit.MINUTES.toMillis(this.server.playerIdleTimeout())
                    && !this.player.wonGame) {
                AFKKt.startAFK(this.player);
                callbackInfo.cancel();
                // this.disconnect(Component.translatable("multiplayer.disconnect.idling"));
            }
        }
    }
}