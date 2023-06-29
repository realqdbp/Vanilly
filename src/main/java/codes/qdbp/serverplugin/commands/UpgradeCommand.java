package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.inventories.UpgradeInventory;
import codes.qdbp.serverplugin.misc.UpgradeMenuUtility;
import codes.qdbp.serverplugin.misc.UpgradeMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (!(sender instanceof Player player)) return false;

        Serverplugin.menuUtilityMap.put(player.getUniqueId(), new UpgradeMenuUtility());
        Serverplugin.menuUtilityMap.get(player.getUniqueId()).updateCurrentMenu(UpgradeMenus.MAIN);
        player.openInventory(new UpgradeInventory().getUpgradeInventory());
        return true;
    }
}
