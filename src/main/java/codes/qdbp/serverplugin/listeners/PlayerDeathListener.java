package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class PlayerDeathListener implements Listener {

    private final Serverplugin plugin;

    public PlayerDeathListener(Serverplugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler

    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();


        /*
        * Position
        * Wie viele Tode durch andere Spieler
         */

        int deaths = plugin.getConfig().getInt("Player." + player.getName() + ".tode") + 1;

        String deathMessage = PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(event.deathMessage()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String positions = "X: " + player.getLocation().getBlockX() + "| Y: " + player.getLocation().getBlockY() + "| Z: " + player.getLocation().getBlockZ();


        plugin.getConfig().set("Player." + player.getName() + ".tode", deaths);

        String deathNumber = "Tod " + deaths;
        String cause = "Grund: " + deathMessage;
        String realLifeTime = "Datum: " + dtf.format(now);
        String mcDay = "MC Tag: " + (player.getWorld().getFullTime() / 24000);
        String position = "Position: " + positions;

        String todesInfo = "Player." + player.getName() + ".todes." + deaths;

        plugin.getConfig().set(todesInfo + ".deathNumber", deathNumber);
        plugin.getConfig().set(todesInfo + ".cause", cause);
        plugin.getConfig().set(todesInfo + ".time", realLifeTime);
        plugin.getConfig().set(todesInfo + ".mcTime", mcDay);
        plugin.getConfig().set(todesInfo + ".position", position);


        plugin.saveConfig();
    }

}
