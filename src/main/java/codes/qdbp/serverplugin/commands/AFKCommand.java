package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.AFKPlayerTimeUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AFKCommand implements CommandExecutor {


    private final Serverplugin plugin;

    public AFKCommand(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        if (args.length == 0) {
            if (Serverplugin.afkPlayerList.contains(player.getUniqueId())) return true;
            Serverplugin.afkPlayerList.add(player.getUniqueId());
            player.setInvulnerable(true);
            AFKPlayerTimeUtility.justTime(plugin, player);
            player.sendMessage("You're now afk");
        } else {
            if (Serverplugin.afkPlayerList.contains(player.getUniqueId())) {
                Bukkit.getScheduler().cancelTask(Serverplugin.afkPlayerRunningTasksMap.get(player.getUniqueId()));
                Serverplugin.afkPlayerRunningTasksMap.remove(player.getUniqueId());
                String message = Arrays.toString(args).replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "");
                AFKPlayerTimeUtility.textAndTime(plugin, player, message);
                player.sendMessage("Changed Status Message.");
            } else {
                Serverplugin.afkPlayerList.add(player.getUniqueId());
                player.setInvulnerable(true);
                String message = Arrays.toString(args).replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "");
                AFKPlayerTimeUtility.textAndTime(plugin, player, message);
                player.sendMessage("You're now afk");
            }
        }
        return true;
    }
}
