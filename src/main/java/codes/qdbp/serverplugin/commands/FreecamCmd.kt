package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.misc.endFreecam
import codes.qdbp.serverplugin.misc.freecamPlayers
import codes.qdbp.serverplugin.misc.startFreecam
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FreecamCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        //Already in Freecam
        if (freecamPlayers.containsKey(sender.uniqueId)) {
            sender.endFreecam()
            return true
        }

        sender.startFreecam()
        return true
    }
}