package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.inventories.openBackpack
import codes.qdbp.serverplugin.inventories.openPublicBackpack
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class BackpackCmd(val plugin: Serverplugin) : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?) =
        if (args?.size == 1) mutableListOf("all") else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        if (args != null && args.isNotEmpty() && args[0] == "all") sender.openPublicBackpack(plugin)
        else sender.openBackpack(plugin)
        return true
    }
}
