package codes.qdbp.serverplugin.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class EnchantCrazy implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;


        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        itemMeta.addEnchant(Enchantment.DIG_SPEED, 10, true);
        player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
        return true;
    }
}
