package codes.qdbp.serverplugin.misc

import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player

data class FreecamPlayer(
    val player: Player,
    val location: Location,
){
    init {
        player.gameMode = GameMode.SPECTATOR
        player.sendMessage("Du kannst mit ${NamedTextColor.AQUA}/sw ${NamedTextColor.WHITE}zwischen den Welten wechseln")
    }
}