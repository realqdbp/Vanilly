package codes.qdbp.serverplugin.misc

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player

data class FreecamPlayer(
    val player: Player,
    val location: Location,
){
    init {
        player.gameMode = GameMode.SPECTATOR
        player.sendMessage(text("Du kannst mit ").append(text("/sw ", color(0x2a9d8f))).append(text("zwischen den Welten wechseln")))
    }
}