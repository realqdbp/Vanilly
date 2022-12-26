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
                """
                        Diese Nachricht jederzeit mit /features abrufen
                        ====================================
                        /tode optional:spielername, für ausgabe der Spielertode
                        Mülleimer erstellen mit Einzelkiste und Schild auf dem 'trash' steht
                        Schlafen mit animation und nur einem Spieler
                        Essen wird automatisch bis max aufgefüllt, bei 'normalen' essensitems
                        Hinsetzen mit rechtsclick auf: Treppen, Teppich, Schnee usw.
                        Fast Leaf Decay bei Baum abbauen (nur Overworld)
                        To come: Handel
                        Backpack, öffne deinen mit /pv /bp oder /backpack
                        To come: Backups
                        Elytra/Rüstung Swap. Einfach auszutauschendes Item rechtsclicken
                        AFK. mit /afk oder nach bestimmter Zeit
                        3D Map vom Server hier: https://qdbp.codes/map
                        Freecam mit /fc oder /freecam, switche welten mit /sw oder /switchworld
                        Craft everywhere with /craft or /c
                        """
        );

        return true;
    }
}
