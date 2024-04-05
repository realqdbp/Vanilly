package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.menusystem.menus.DeathMenu
import codes.qdbp.serverplugin.misc.menuManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {


            DeathMenu(sender.menuManager()).open()


        }
        return true
    }
}