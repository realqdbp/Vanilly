package codes.qdbp.vanilly.mixin;

import codes.qdbp.vanilly.AFKKt;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract boolean isAlive();

    @Shadow
    public abstract boolean onClimbable();

    @Inject(at = @At("TAIL"), method = "isPushable", cancellable = true)
    private void isPushable(CallbackInfoReturnable<Boolean> cir) {
        if ((((LivingEntity) (Object) this)) instanceof ServerPlayer player) {
            if (AFKKt.isAFK(player)) {
                cir.setReturnValue(false);
            }
        }
    }
}
