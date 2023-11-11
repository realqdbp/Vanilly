package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.afkPlayers
import codes.qdbp.serverplugin.misc.changeAfkMessage
import codes.qdbp.serverplugin.misc.setAfk
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AfkCmd(val plugin: Serverplugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        //Not already AFK
        if (!afkPlayers.containsKey(sender.uniqueId)) {
            sender.setAfk(args?.joinToString(separator = ",", prefix = " ") ?: "", plugin)
            return true
        }

        //Already afk
        sender.changeAfkMessage(args?.joinToString(separator = ",", prefix = " ") ?: "")

        return true
    }
}