package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //TODO Check if advancements made for it to have permission to have enderchest opened

        if (!(sender instanceof Player player)) return false;

        player.openInventory(player.getEnderChest());

        return true;
    }
}
