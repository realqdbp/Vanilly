package codes.qdbp.vanilly.mixin;

import codes.qdbp.vanilly.mixincode.ConsumeMultiple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Consumable.class)
public class ConsumableMixin {

    @Shadow @Final private List<ConsumeEffect> onConsumeEffects;

    @Inject(method = "onConsume", at = @At("TAIL"))
    private void consumeMultiple(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (this.onConsumeEffects.isEmpty()) ConsumeMultiple.INSTANCE.consumeMultiple(user, stack);
    }
}
