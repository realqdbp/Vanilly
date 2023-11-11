package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.sleepForward
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SkipNightCmd(val plugin: Serverplugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        sender.sleepForward(plugin, true)

        return true
    }
}