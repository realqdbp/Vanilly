package codes.qdbp.serverplugin.commands

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.event.ClickEvent.runCommand
import net.kyori.adventure.text.format.TextColor.color
import net.kyori.adventure.text.format.TextDecoration.UNDERLINED
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FeaturesCmd : CommandExecutor {

    private val features =
        text("Klick ein Feature an um mehr zu erfahren:", color(0xe9c46a))
            .appendNewline().append(text("AFK", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info afk")))
            .appendNewline().append(text("Backpack", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info backpack")))
            .appendNewline().append(text("Craft", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info craft")))
            .appendNewline().append(text("Enderchest", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enderchest")))
            .appendNewline().append(text("Freecam", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info freecam")))
            .appendNewline().append(text("Upgrade", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info upgrade")))
            .appendNewline().append(text("SkipNight", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info skipnight")))
            .appendNewline().append(text("SwitchWorld", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info switchworld")))
            .appendNewline().append(text("Deaths", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info deaths")))
            .appendNewline().append(text("EnhancedEating", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enhancedeating")))
            .appendNewline().append(text("EnhancedSleep", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info enhancedSleep")))
            .appendNewline().append(text("Light", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info light")))
            .appendNewline().append(text("DoubleDoorOpening", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info doubledooropening")))
            .appendNewline().append(text("MapImage", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info mapImage")))
            .appendNewline().append(text("InvisItemFrames", color(0x2a9d8f), UNDERLINED).clickEvent(runCommand("/info invisitemframes")))

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        sender.sendMessage(features)
        return true
    }
}