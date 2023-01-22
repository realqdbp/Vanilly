package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Duration;

public class PlayerJoinListener implements Listener {

    private final Serverplugin plugin;

    public PlayerJoinListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.showTitle(Title.title(Component.text(ChatColor.DARK_PURPLE + "ψ(｀∇´)ψ"),Component.text(ChatColor.GREEN + "Viel Spaß aufm Server!"),Title.Times.times(Duration.ofSeconds(2),Duration.ofSeconds(8),Duration.ofSeconds(2))));


        //maybe in future turn this with pom, didn't bring it to work rn
        double currentPluginVersion = 1.9;
        if (plugin.getConfig().getDouble("Player." + player.getName() + ".pluginVersion") == currentPluginVersion) return;

        player.sendMessage(
                """
                Hoffentlich Freecam und AFK gefixed :)
                \n
                """
        );

        plugin.getConfig().set("Player." + player.getName() + ".pluginVersion", currentPluginVersion);
        plugin.saveConfig();
    }
}
