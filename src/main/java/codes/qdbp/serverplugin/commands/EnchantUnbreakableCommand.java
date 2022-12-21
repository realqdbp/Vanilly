package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class EnchantUnbreakableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        itemMeta.setUnbreakable(true);
        player.getInventory().getItemInMainHand().setItemMeta(itemMeta);

        return true;
    }
}
