package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.misc.freecamPlayers
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class SwitchWorldCmd : TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?) =
        if (args?.size == 1) mutableListOf("overworld", "nether", "end") else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!freecamPlayers.containsKey(sender.uniqueId)) return false
        if (args?.size != 1) return false

        when (args[0].lowercase()) {
            "o", "ov", "overworld" -> {
                sender.teleport(sender.bedSpawnLocation ?: Bukkit.getWorld("world")!!.spawnLocation)
            }
            "n", "nether" -> {
                sender.teleport(Location(Bukkit.getWorld("world_nether"), (sender.x / 8), sender.y, (sender.z / 8)))
            }
            "e", "end" -> {
                sender.teleport(Location(Bukkit.getWorld("world_the_end"), 0.0, 70.0, 0.0))
            }
        }
        return true
    }
}