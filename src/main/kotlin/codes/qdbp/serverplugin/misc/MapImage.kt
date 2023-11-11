package codes.qdbp.serverplugin.misc

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView
import org.bukkit.persistence.PersistentDataType
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.imageio.ImageIO


@SerializableAs("MapImage")
class MapImage(val plugin: Serverplugin, mapView: MapView) : MapRenderer() {
    private val outputStream = ByteArrayOutputStream()
    private lateinit var image: BufferedImage
    private val storage = plugin.mapImageStorage.storage

    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        canvas.drawImage(0, 0, image)
    }


    private val map = ItemStack(Material.FILLED_MAP)
    private val mapMeta = map.itemMeta as MapMeta



    fun save() {
        ImageIO.write(image, "png", outputStream)
        storage.set("mapIDs", storage.getIntegerList("mapIDs").add(mapMeta.mapId))
        plugin.mapImageStorage.save()
        mapMeta.persistentDataContainer.set(NamespacedKey(plugin, "imageBytes"), PersistentDataType.BYTE_ARRAY, outputStream.toByteArray())
        map.itemMeta = mapMeta
    }

    fun new(url: URL) {
        image = ImageIO.read(url)
    }

    init {
        mapView.addRenderer(this)
    }
}