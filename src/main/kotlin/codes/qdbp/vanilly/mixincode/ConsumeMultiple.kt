package codes.qdbp.vanilly.mixincode

import net.minecraft.core.component.DataComponents
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.food.FoodConstants
import net.minecraft.world.item.ItemStack
import kotlin.math.ceil
import kotlin.math.min

object ConsumeMultiple {
    fun consumeMultiple(user: LivingEntity, stack: ItemStack) {
        if (user !is ServerPlayer) return
        val food = stack.get(DataComponents.FOOD) ?: return

        val maxPossibleAmount = ceil((FoodConstants.MAX_FOOD - user.foodData.foodLevel) / food.nutrition.toDouble()).toInt()
        if (maxPossibleAmount <= 0) return

        val amount = min(maxPossibleAmount, stack.count)

        user.foodData.foodLevel = ((user.foodData.foodLevel + food.nutrition) * amount).coerceIn(0, 20)
        user.foodData.setSaturation(((user.foodData.saturationLevel + food.saturation) * amount).coerceIn(0f, user.foodData.foodLevel.toFloat()))

        stack.consume(amount, user)
    }
}