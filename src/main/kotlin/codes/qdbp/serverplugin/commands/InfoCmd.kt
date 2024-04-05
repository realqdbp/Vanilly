package codes.qdbp.serverplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class InfoCmd : TabExecutor{
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?) =
        if (args?.size == 1) mutableListOf(
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
        ) else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {

        when (args?.get(0) ?: return false) {
            "afk" -> sender.sendPlainMessage(afkInfo)
            "backpack" -> sender.sendPlainMessage(backpackInfo)
            "craft" -> sender.sendPlainMessage(craftInfo)
            "enderchest" -> sender.sendPlainMessage(enderchestInfo)
            "freecam" -> sender.sendPlainMessage(freecamInfo)
            "upgrade" -> sender.sendPlainMessage(upgradeInfo)
            "skipnight" -> sender.sendPlainMessage(skipNightInfo)
            "switchworld" -> sender.sendPlainMessage(switchWorldInfo)
            "deaths" -> sender.sendPlainMessage(deathInfo)
            "enhancedEating" -> sender.sendPlainMessage(enhancedEatingInfo)
            "enhancedSleep" -> sender.sendPlainMessage(enhancedSleepInfo)
            "light" -> sender.sendPlainMessage(lightInfo)
            "doubleDoorOpening" -> sender.sendPlainMessage(doubleDoorOpeningInfo)
            "mapImage" -> sender.sendPlainMessage(mapImageInfo)
            "invisibleItemFrames" -> sender.sendPlainMessage(invisItemFrameInfo)
            "enhancedTotems" -> sender.sendPlainMessage(enhancedTotemsInfo)
            "fastLeafDecay" -> sender.sendPlainMessage(fastLeafDecayInfo)
        }
        return true
    }


    private val afkInfo = """
        Anstelle vom Server wegen AFK gekickt zu werden,
        wirst du in den AFK Modus gesetzt.
        Du kannst dann nicht mehr angegriffen werden.
        Du kannst dich aber auch mit /afk selbst in diesen Modus setzen.
    """.trimIndent()


    private val backpackInfo = """
        Du hast mit /backpack einen Persönlichen backpack.
        Mit /backpack all einen Öffentlichen.
        Items in diesen bleiben gespeichert.
    """.trimIndent()

    private val craftInfo = """
        Mit /craft wird das Crafting Menü geöffnet.
        Also keine Werkbank mehr auf Reisen mitnehmen.
    """.trimIndent()

    private val enderchestInfo = """
        Mit /enderchest wird
        deine Enderchest geöffnet.
    """.trimIndent()

    private val freecamInfo = """
        Setzt dich in Freecam.
    """.trimIndent()

    private val upgradeInfo = """
        Öffnet ein codes.qdbp.serverplugin.menusystem.Menu in dem
        late-game Upgrades zu kaufen sind.
    """.trimIndent()

    private val skipNightInfo = """
        Mit /skipnight wird
        die Nacht schnell 'übersprungen'
    """.trimIndent()

    private val switchWorldInfo = """
        Während der Freecam kannst
        du mit /switchworld zwischen
        normaler Welt, Nether und End
        wechseln.
    """.trimIndent()

    private val deathInfo = """
        Mit /tode werden dir von jedem Spieler
        der auf dem Server gewhitelistet ist,
        angezeigt wie viele Tode diese haben.
    """.trimIndent()

    private val enhancedEatingInfo = """
        Wenn du isst, wird deine Hungerleiste
        komplett gefüllt und dementsprechend
        viele Items konsumiert. Heißt also,
        nur noch einmal essen anstatt sich
        15 Möhren reinzuschieben.
    """.trimIndent()

    private val enhancedSleepInfo = """
        Schlafen ist jetzt 'animiert'.
        Wenn man sich ins Bett legt oder
        /skipnight verwendet wird nicht
        direkt zum Tag geskippt sondern
        die Zeit einfach beschleunigt.
    """.trimIndent()

    private val lightInfo = """
        Das Item 'Light' kann nun gecrafted werden.
        Das Rezept ist: Fackeln und in der Mitte ein Eisenblock.
    """.trimIndent()

    private val doubleDoorOpeningInfo = """
        Öffnet die Anliegende Tür direkt mit.
    """.trimIndent()

    private val mapImageInfo = """
        WIP
        Erstelle eine Minecraft Map mit einem Custom Bild aus dem Internet.
        'shrink' setzt die größe des Bildes auf eine Map.
        'resize' gibt dir eine Auswahl an verschiedenen übergreifenden größen des Bildes.
        Btw, musst du so viele Maps in der Hand halten wie gebraucht werden.
    """.trimIndent()

    private val invisItemFrameInfo = """
        Das Item 'Invisible Item Frame' kann nun gecraftet werden.
        Das Rezept ist: Item Frame und Glass Pane.
    """.trimIndent()

    private val enhancedTotemsInfo = """
        Totem müssen nicht in der Hand oder Off-Hand sein um genutzt zu werden,
        es reicht wenn sie im Inventar sind.
    """.trimIndent()

    private val fastLeafDecayInfo = """
        Blätter verschwinden schneller als normal,
        wenn der Baum abgebaut ist.
    """.trimIndent()

}