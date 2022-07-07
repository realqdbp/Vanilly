package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
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
                "To come: Automatisches Zaun öffnen/schließen\n" +
                "To come: Backups\n"
        );

        return true;
    }
}
