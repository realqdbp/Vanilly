package codes.qdbp.serverplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class SwitchWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (sender instanceof Player player) {

            if (config.getBoolean("Player." + player.getName() + ".Freecam.state")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("ov") || args[0].equalsIgnoreCase("overworld") || args[0].equalsIgnoreCase("o")) {
                        if (player.getBedSpawnLocation() != null) {
                            player.teleport(player.getBedSpawnLocation());
                        } else {
                            player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
                        }
                    } else if (args[0].equalsIgnoreCase("nether") || args[0].equalsIgnoreCase("n")) {
                        player.teleport(new Location(Bukkit.getWorld("world_nether"), (player.getLocation().getX() / 8), player.getLocation().getY(), (player.getLocation().getZ() / 8)));

                    } else if (args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("e")) {
                        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 0d, 70d, 0d));
                    }
                } else return false;
            } else player.sendMessage(ChatColor.RED + "Du musst im Freecam modus sein!");
        }
        return true;
    }
}
