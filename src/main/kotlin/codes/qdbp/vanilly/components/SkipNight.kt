package codes.qdbp.vanilly.components

import net.minecraft.core.registries.Registries
import net.minecraft.server.MinecraftServer
import net.minecraft.world.clock.WorldClocks

object SkipNight {
    var shouldForward = false

    fun skipNight(server: MinecraftServer) {
        if (!shouldForward) return
        val world = server.overworld()

        val worldClockHolder = world
            .registryAccess()
            .lookupOrThrow(Registries.WORLD_CLOCK)
            .getOrThrow(WorldClocks.OVERWORLD)

        if (world.skyDarken >= 4) {
            if (world.isThundering) world.resetWeatherCycle()
            world.clockManager().addTicks(worldClockHolder, 100)
        }
        else shouldForward = false
    }
}