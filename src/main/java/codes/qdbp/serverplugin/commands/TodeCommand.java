package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TodeCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Tode von " + player.getName() + ": " + config.getInt("Player." + player.getName()+".tode"));
        }

        if (args.length == 1) {
            player.sendMessage("Tode von " + args[0] + ": " + config.getInt("Player." + args[0]+".tode"));
        }

        return true;
    }
}
