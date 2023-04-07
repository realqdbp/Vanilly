package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class FeaturesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        player.sendMessage(
                """
                - AFK (/info afk)
                - Backpack (/info backpack)
                - Craft (/info craft)
                - Enderchest (/info enderchest)
                - Freecam (/info freecam)
                - Upgrade (/info upgade)
                - skipnight (/info skipnight)
                - Switchworld (/info switchworld)
                - Tode (/info tode)
                - Improved Eating (/info eating)
                - Improved Sleep (/info sleep)
                - Elytra Change (/info elytrachange)
                - LightRecipe (/info lightrecipe)
                - DoubleDoorOpening (/info doubledooropening)
                """
        );

        return true;
    }
}
