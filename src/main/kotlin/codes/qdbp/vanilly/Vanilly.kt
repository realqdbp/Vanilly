package codes.qdbp.vanilly

import codes.qdbp.vanilly.commands.infoCmd
import io.github.oshai.kotlinlogging.KotlinLogging
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback

val logger = KotlinLogging.logger { }
object Vanilly : ModInitializer {

	override fun onInitialize() {

		CommandRegistrationCallback.EVENT.register { dispatcher , _, _ ->
			dispatcher.register(infoCmd)
		}

        logger.info { "Vanilly started!" }
	}
}