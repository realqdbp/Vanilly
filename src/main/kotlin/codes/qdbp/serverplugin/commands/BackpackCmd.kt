package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.menusystem.menus.BackpackMenu
import codes.qdbp.serverplugin.misc.menuManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class BackpackCmd(val plugin: Serverplugin) : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?) =
        if (args?.size == 1) mutableListOf("all") else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        BackpackMenu(plugin, if (args.isNullOrEmpty()) true else args[0] != "all", sender.menuManager()).open()
        return true
    }
}
