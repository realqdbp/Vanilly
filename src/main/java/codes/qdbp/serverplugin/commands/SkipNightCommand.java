package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.SleepForward;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkipNightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        new SleepForward(player, true).runTaskTimer(Serverplugin.getPlugin(), 0, 2);

        return true;
    }
}
