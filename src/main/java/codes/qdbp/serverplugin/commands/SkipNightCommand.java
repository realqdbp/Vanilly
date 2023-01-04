package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.SleepForward;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkipNightCommand implements CommandExecutor {

    private final Serverplugin plugin;

    public SkipNightCommand(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        new SleepForward(player, true).runTaskTimer(plugin, 0, 0);

        return true;
    }
}
