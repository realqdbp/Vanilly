package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class InfoMessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        player.sendMessage(
                "Diese Nachricht jederzeit mit /features abrufen\n" +
                "====================================\n" +
                "/tode optional:spielername, für ausgabe der Spielertode\n" +
                "Mülleimer erstellen mit Einzelkiste und Schild auf dem 'trash' steht\n" +
                "Schlafen mit animation und nur einem Spieler\n" +
                "Essen wird automatisch bis max aufgefüllt, bei 'normalen' essensitems\n" +
                "Hinsetzen mit rechtsclick auf: Treppen, Teppich, Schnee usw.\n" +
                "Fast Leaf Decay bei Baum abbauen (nur Overworld)\n" +
                "To come: Handel\n" +
                "Backpack, öffne deinen mit /pv /bp oder /backpack\n" +
                "To come: Backups\n" +
                "Elytra/Rüstung Swap. Einfach auszutauschendes Item rechtsclicken\n" +
                "AFK. mit /afk oder nach bestimmter Zeit\n" +
                "3D Map vom Server hier: https://qdbp.codes/map\n" +
                "Freecam mit /fc oder /freecam, switche welten mit /sw oder /switchworld\n" +
                "Craft everywhere with /craft or /c\n"
        );

        return true;
    }
}
