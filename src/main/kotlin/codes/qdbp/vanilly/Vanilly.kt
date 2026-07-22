package codes.qdbp.vanilly

import codes.qdbp.vanilly.commands.infoCmd
import codes.qdbp.vanilly.commands.skipNightCmd
import codes.qdbp.vanilly.components.SkipNight
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("Vanilly")
object Vanilly : ModInitializer {

	override fun onInitialize() {

		logger.debug("Registering Commands...")
		CommandRegistrationCallback.EVENT.register { dispatcher , _, _ ->
			dispatcher.register(infoCmd)
			dispatcher.register(skipNightCmd)
		}

		logger.debug("Registering ServerTickEvents...")
		ServerTickEvents.END_SERVER_TICK.register(SkipNight::skipNight)

        logger.info("Vanilly started!")
	}
}