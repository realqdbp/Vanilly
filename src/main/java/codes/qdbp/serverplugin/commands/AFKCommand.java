package codes.qdbp.serverplugin.commands;

import net.kyori.adventure.text.Component;
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

public class AFKCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                player.sendMessage("Du bist jetzt AFK!");
                player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + args[0] + ChatColor.WHITE + "]"));
                config.set("Player." + player.getName() + ".afk", true);
                try {config.save(configFile);} catch (IOException e) {e.printStackTrace();}
                player.setInvulnerable(true);
            } else return false;

        }
        return true;
    }
}
