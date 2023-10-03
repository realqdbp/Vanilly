package codes.qdbp.serverplugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent.runCommand
import net.kyori.adventure.text.format.TextColor.color
import net.kyori.adventure.text.format.TextDecoration.UNDERLINED
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FeaturesCmd : CommandExecutor {

    private val features =
        Component.text("Klick ein Feature an um mehr zu erfahren:", color(0xe9c46a))
            .appendNewline().append(Component.text("AFK", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info afk")))
            .appendNewline().append(Component.text("Backpack", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info backpack")))
            .appendNewline().append(Component.text("Craft", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info craft")))
            .appendNewline().append(Component.text("Enderchest", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enderchest")))
            .appendNewline().append(Component.text("Freecam", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info freecam")))
            .appendNewline().append(Component.text("Upgrade", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info upgrade")))
            .appendNewline().append(Component.text("SkipNight", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info skipnight")))
            .appendNewline().append(Component.text("SwitchWorld", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info switchworld")))
            .appendNewline().append(Component.text("Deaths", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info deaths")))
            .appendNewline().append(Component.text("EnhancedEating", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enhancedeating")))
            .appendNewline().append(Component.text("EnhancedSleep", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enhancedSleep")))
            .appendNewline().append(Component.text("Light", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info light")))
            .appendNewline().append(Component.text("DoubleDoorOpening", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info doubledooropening")))
            .appendNewline().append(Component.text("MapImage", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info mapImage")))

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        sender.sendMessage(features)
        return true
    }
}