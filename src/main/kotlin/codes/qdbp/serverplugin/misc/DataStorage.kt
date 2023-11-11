package codes.qdbp.serverplugin.misc

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

data class DataStorage(val file: File) {

    val storage = YamlConfiguration.loadConfiguration(file)

    fun save() {
        storage.save(file)
    }
}