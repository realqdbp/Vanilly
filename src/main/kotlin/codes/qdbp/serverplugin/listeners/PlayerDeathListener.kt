package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.EntityEffect
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PlayerDeathListener(val plugin: Serverplugin) : Listener {


    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {

        val player = event.player


        // USING TOTEM FROM INV
        if (player.inventory.contains(Material.TOTEM_OF_UNDYING)) {
            event.isCancelled = true
            player.inventory.setItem(player.inventory.first(Material.TOTEM_OF_UNDYING), ItemStack(Material.AIR))
            player.playSound(player.location, Sound.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1f, 1f)
            player.playEffect(EntityEffect.TOTEM_RESURRECT)
            return
        }

        val storage = plugin.deathStorage.storage

        val deaths = storage.getInt("${player.name}.deaths") + 1
        val death = "Tod: $deaths"
        val cause = "Grund: ${PlainTextComponentSerializer.plainText().serialize(event.deathMessage()!!)}"
        val irlTime = "Datum: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}"
        val mcTime = "MC Tag: ${player.world.fullTime / 24000}, Zeit: ${player.world.fullTime % 24000}"
        val position = "Position: X[${player.location.blockX}] Y[${player.location.blockY}] Z[${player.location.blockZ}]"
        val info = "${player.name}.death.$deaths"

        storage.set("${player.name}.deaths", deaths)
        storage.set("$info.death", death)
        storage.set("$info.cause", cause)
        storage.set("$info.irlTime", irlTime)
        storage.set("$info.mcTime", mcTime)
        storage.set("$info.position", position)

        plugin.deathStorage.save()


    }
}