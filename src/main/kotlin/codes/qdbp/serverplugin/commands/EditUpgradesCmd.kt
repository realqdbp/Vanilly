package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.createInventory
import codes.qdbp.serverplugin.misc.createItem
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class EditUpgradesCmd(val plugin: Serverplugin) : CommandExecutor{
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val item = sender.inventory.itemInMainHand
        if (!(item.itemMeta.persistentDataContainer.has(plugin.efficiencyUpgradeNSK))) return false
        if (item.getEnchantmentLevel(Enchantment.DIG_SPEED) >= 5) {
            val levels = item.itemMeta.persistentDataContainer.get(plugin.efficiencyUpgradeNSK, PersistentDataType.INTEGER)
            val items = mutableListOf<ItemStack>()
            for (i in 5..levels!!) {
                items.add(createItem(item.type, "$i"))
            }
            val inventory = createInventory("Change Efficiency", items.toTypedArray())
            sender.openInventory(inventory)
        }
        return true
    }
}