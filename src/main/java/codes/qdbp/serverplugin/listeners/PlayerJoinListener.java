package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.misc.UpdateChecker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.net.URISyntaxException;
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

        if (player.isOp() && Serverplugin.getPluginUpToDate()) {
            try {
                new UpdateChecker();
            } catch (IOException | InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        if (player.isOp() && (!Serverplugin.getPluginUpToDate())) player.sendMessage(Component.text("There is a newer version of Serverplugin available ").append(Component.text("here").style(Style.style(TextDecoration.UNDERLINED, TextDecoration.BOLD)).clickEvent(ClickEvent.openUrl("https://github.com/realqdbp/ServerPlugin/releases/latest"))));

        //maybe in future turn this with pom, didn't bring it to work rn
        if (plugin.getConfig().getDouble("Player." + player.getName() + ".pluginVersion") == Serverplugin.getCurrentPluginVersion()) return;

        player.sendMessage(
                """
                'Tode' verbessert! Probiers doch mit /tode aus :)
                \n
                """
        );

        plugin.getConfig().set("Player." + player.getName() + ".pluginVersion", Serverplugin.getCurrentPluginVersion());
        plugin.saveConfig();
    }
}
