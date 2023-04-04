package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class FreecamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;


        if (Serverplugin.freecamPlayerLocation.containsKey(player.getUniqueId())) {

            backportPlayer(player);

        }else{
            Serverplugin.freecamPlayerLocation.put(player.getUniqueId(), player.getLocation());

            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("Mit /sw zwischen den Welten wechseln");
        }


        return true;
    }

    public static void backportPlayer(Player player) {

        player.setGameMode(GameMode.SURVIVAL);

        player.teleport(Serverplugin.freecamPlayerLocation.get(player.getUniqueId()));

        Serverplugin.freecamPlayerLocation.remove(player.getUniqueId());
        Serverplugin.freecamPlayerLocation.remove(player.getUniqueId());
    }
}
