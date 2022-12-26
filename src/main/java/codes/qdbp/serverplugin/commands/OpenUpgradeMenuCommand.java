package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenUpgradeMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;
        player.openInventory(Serverplugin.getUpgradeMenuInventory());


        return false;
    }
}
