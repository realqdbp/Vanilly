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
import java.util.Arrays;

public class AFKCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (!(sender instanceof Player player)) return false;
        if (!(args.length >= 1)) return false;


        if (config.getBoolean("Player." + player.getName() + ".afk")) {
            player.sendMessage("Changed Status Message.");
            player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + Arrays.toString(args) + ChatColor.WHITE + "]"));
        } else {
            config.set("Player." + player.getName() + ".afk", true);
            try {config.save(configFile);} catch (IOException e) {throw new RuntimeException(e);}
            player.setInvulnerable(true);

            player.sendMessage("You're now afk.");
            player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + Arrays.toString(args) + ChatColor.WHITE + "]"));
        }

        return true;
    }
}
