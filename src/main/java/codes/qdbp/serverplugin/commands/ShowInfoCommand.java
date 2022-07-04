package codes.qdbp.serverplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ShowInfoCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        Player player = (Player) sender;

        if (config.getBoolean("Player." + player.getName() + ".dontShowInfoMessage")) {
            config.set("Player." + player.getName() + ".dontShowInfoMessage", false);
            try {config.save(configFile);} catch (IOException e) {e.printStackTrace();}
            player.sendMessage(ChatColor.AQUA + "Showing InfoMessage");
        } else {
            config.set("Player." + player.getName() + ".dontShowInfoMessage", true);
            try {config.save(configFile);} catch (IOException e) {e.printStackTrace();}
            player.sendMessage(ChatColor.AQUA + "Hiding InfoMessage");
        }

        return true;
    }
}
