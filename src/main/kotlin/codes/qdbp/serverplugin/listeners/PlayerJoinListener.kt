package codes.qdbp.serverplugin.listeners

import codes.qdbp.serverplugin.Serverplugin
import codes.qdbp.serverplugin.misc.checkPluginUpToDate
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.time.Duration

class PlayerJoinListener(val plugin: Serverplugin) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        /**
         * Welcome Titlemessage
         */
        player.showTitle(Title.title(
            Component.text("ψ(｀∇´)ψ", TextColor.color(0x2a9d8f)),
            Component.text("Viel Spaß aufm Server!", TextColor.color(0xe9c46a)),
            Title.Times.times(Duration.ofSeconds(2), Duration.ofSeconds(6), Duration.ofSeconds(2))
            ))

        /**
         * Update Checker for OP's
         */
        if (player.isOp && !checkPluginUpToDate(plugin)) player.sendMessage(
            Component.text("Es ist eine neuere Version vom Serverplugin verfügbar: " , TextColor.color(0xef233c))
            .append(
                Component.text("Download", TextColor.color(0x8d99ae), TextDecoration.UNDERLINED)
                .clickEvent(ClickEvent.openUrl("https://github.com/realqdbp/ServerPlugin/releases/latest"))
            )
        )


        /**
         * Set individual Players plugin version
         */
        @Suppress("UnstableApiUsage")
        if (plugin.config.getString("Player.${player.name}.pluginVersion") != plugin.pluginMeta.version) {
            player.sendMessage(
                Component.text("""
                    PLUGIN VERSION CATCH-UP:
                    - Upgrades gehen wieder.
                    - Unbreakable Upgrades Verfügbar.
                    - Efficiency Upgrades Verfügbar.
                """.trimIndent(), TextColor.color(0xcdb4db))
            )
            plugin.config.set("Player.${player.name}.pluginVersion", plugin.pluginMeta.version)
            plugin.saveConfig()
        }
    }
}