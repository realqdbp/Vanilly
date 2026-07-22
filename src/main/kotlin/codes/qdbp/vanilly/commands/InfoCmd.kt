package codes.qdbp.vanilly.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import java.util.concurrent.CompletableFuture

private val commandNames = listOf(
    "afk",
    "backpack",
    "craft",
    "enderchest",
    "freecam",
    "upgrade",
    "skipnight",
    "switchworld",
    "deaths",
    "enhancedEating",
    "enhancedSleep",
    "light",
    "doubleDoorOpening",
    "mapImage",
    "invisibleItemFrames",
    "enhancedTotems",
    "fastLeafDecay",
)

private object InfoCmdSuggestionProvider : SuggestionProvider<CommandSourceStack> {
    override fun getSuggestions(
        context: CommandContext<CommandSourceStack>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        commandNames.forEach { builder.suggest(it) }
        return builder.buildFuture()
    }
}

val infoCmd: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("info")
    .then(
        Commands.argument("commandName", StringArgumentType.string())
            .suggests(InfoCmdSuggestionProvider)
            .executes {
                when (StringArgumentType.getString(it, "commandName")) {
                    // TODO Implement "afk" -> it.source.sendSuccess(afkInfo, false)
                    // TODO Implement "backpack" -> it.source.sendSuccess(backpackInfo, false)
                    // TODO Implement "craft" -> it.source.sendSuccess(craftInfo, false)
                    // TODO Implement "enderchest" -> it.source.sendSuccess(enderchestInfo, false)
                    // TODO Implement "freecam" -> it.source.sendSuccess(freecamInfo, false)
                    // TODO Implement "upgrade" -> it.source.sendSuccess(upgradeInfo, false)
                    // TODO Implement "skipnight" -> it.source.sendSuccess(skipNightInfo, false)
                    // TODO Implement "switchworld" -> it.source.sendSuccess(switchWorldInfo, false)
                    // TODO Implement "deaths" -> it.source.sendSuccess(deathInfo, false)
                    // TODO Implement "enhancedEating" -> it.source.sendSuccess(enhancedEatingInfo, false)
                    // TODO Implement "enhancedSleep" -> it.source.sendSuccess(enhancedSleepInfo, false)
                    // TODO Implement "light" -> it.source.sendSuccess(lightInfo, false)
                    // TODO Implement "doubleDoorOpening" -> it.source.sendSuccess(doubleDoorOpeningInfo, false)
                    // TODO Implement "mapImage" -> it.source.sendSuccess(mapImageInfo, false)
                    // TODO Implement "invisibleItemFrames" -> it.source.sendSuccess(invisItemFrameInfo, false)
                    // TODO Implement "enhancedTotems" -> it.source.sendSuccess(enhancedTotemsInfo, false)
                    // TODO Implement "fastLeafDecay" -> it.source.sendSuccess(fastLeafDecayInfo, false)
                }
                Command.SINGLE_SUCCESS
            }
    )


private val afkInfo = { Component.literal(
    """
        Anstelle vom Server wegen AFK gekickt zu werden,
        wirst du in den AFK Modus gesetzt.
        Du kannst dann nicht mehr angegriffen werden.
        Du kannst dich aber auch mit /afk selbst in diesen Modus setzen.
    """.trimIndent()
)}

private val backpackInfo = { Component.literal(
    """
        Du hast mit /backpack einen Persönlichen backpack.
        Mit /backpack all einen Öffentlichen.
        Items in diesen bleiben gespeichert.
    """.trimIndent()
)}

private val craftInfo = { Component.literal(
    """
        Mit /craft wird das Crafting Menü geöffnet.
        Also keine Werkbank mehr auf Reisen mitnehmen.
    """.trimIndent()
)}

private val enderchestInfo = { Component.literal(
    """
        Mit /enderchest wird
        deine Enderchest geöffnet.
    """.trimIndent()
)}

private val freecamInfo = { Component.literal(
    """
        Setzt dich in Freecam.
    """.trimIndent()
)}

private val upgradeInfo = { Component.literal(
    """
        Öffnet ein codes.qdbp.serverplugin.menusystem.Menu in dem
        late-game Upgrades zu kaufen sind.
    """.trimIndent()
)}

private val skipNightInfo = { Component.literal(
    """
        Mit /skipnight wird
        die Nacht schnell 'übersprungen'
    """.trimIndent()
)}

private val switchWorldInfo = { Component.literal(
    """
        Während der Freecam kannst
        du mit /switchworld zwischen
        normaler Welt, Nether und End
        wechseln.
    """.trimIndent()
)}

private val deathInfo = { Component.literal(
    """
        Mit /tode werden dir von jedem Spieler
        der auf dem Server gewhitelistet ist,
        angezeigt wie viele Tode diese haben.
    """.trimIndent()
)}

private val enhancedEatingInfo = { Component.literal(
    """
        Wenn du isst, wird deine Hungerleiste
        komplett gefüllt und dementsprechend
        viele Items konsumiert. Heißt also,
        nur noch einmal essen anstatt sich
        15 Möhren reinzuschieben.
    """.trimIndent()
)}

private val enhancedSleepInfo = { Component.literal(
    """
        Schlafen ist jetzt 'animiert'.
        Wenn man sich ins Bett legt oder
        /skipnight verwendet wird nicht
        direkt zum Tag geskippt sondern
        die Zeit einfach beschleunigt.
    """.trimIndent()
)}

private val lightInfo = { Component.literal(
    """
        Das Item 'Light' kann nun gecrafted werden.
        Das Rezept ist: Fackeln und in der Mitte ein Eisenblock.
    """.trimIndent()
)}

private val doubleDoorOpeningInfo = { Component.literal(
    """
        Öffnet die Anliegende Tür direkt mit.
    """.trimIndent()
)}

private val mapImageInfo = { Component.literal(
    """
        WIP
        Erstelle eine Minecraft Map mit einem Custom Bild aus dem Internet.
        'shrink' setzt die größe des Bildes auf eine Map.
        'resize' gibt dir eine Auswahl an verschiedenen übergreifenden größen des Bildes.
        Btw, musst du so viele Maps in der Hand halten wie gebraucht werden.
    """.trimIndent()
)}

private val invisItemFrameInfo = { Component.literal(
    """
        Das Item 'Invisible Item Frame' kann nun gecraftet werden.
        Das Rezept ist: Item Frame und Glass Pane.
    """.trimIndent()
)}

private val enhancedTotemsInfo = { Component.literal(
    """
        Totem müssen nicht in der Hand oder Off-Hand sein um genutzt zu werden,
        es reicht wenn sie im Inventar sind.
    """.trimIndent()
)}

private val fastLeafDecayInfo = { Component.literal(
    """
        Blätter verschwinden schneller als normal,
        wenn der Baum abgebaut ist.
    """.trimIndent()
)}