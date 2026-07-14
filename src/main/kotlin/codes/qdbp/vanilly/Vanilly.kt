package codes.qdbp.vanilly

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Vanilly : ModInitializer {
    val logger: Logger = LoggerFactory.getLogger("Vanilly")

	override fun onInitialize() {

        logger.info("initialized successfully")
	}
}