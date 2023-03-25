package codes.qdbp.serverplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        switch (args[0]) {
            case "afk" -> sendAfkInfo(player);
            case "backpack" -> sendBackpackInfo(player);
            case "craft" -> sendCraftInfo(player);
            case "enderchest" -> sendEnderchestInfo(player);
            case "freecam" -> sendFreecamInfo(player);
            case "upgrade" -> sendUpgradeInfo(player);
            case "skipnight" -> sendSkipnightInfo(player);
            case "switchworld" -> sendSwitchworldInfo(player);
            case "tode" -> sendTodeInfo(player);
            case "eating" -> sendEatingInfo(player);
            case "sleep" -> sendSleepInfo(player);
            case "elytrachange" -> sendElytrachangeInfo(player);
            case "lightrecipe" -> sendLightRecipeInfo(player);
        }

        return true;
    }


    private void sendAfkInfo(Player player) {
        player.sendMessage(
                """
                
                Anstelle vom Server wegen AFK gekickt zu werden,
                wirst du in den AFK Modus gesetzt.
                Du kannst dann nicht mehr angegriffen werden.
                Du kannst dich aber auch mit /afk selbst in diesen Modus setzen.
                
                """
        );
    }

    private void sendBackpackInfo(Player player) {
        player.sendMessage(
                """
                
                Du hast mit /backpack
                einen Persönlichen backpack.
                Items in diesem bleiben gespeichert.
                
                """
        );
    }

    private void sendCraftInfo(Player player) {
        player.sendMessage(
                """
                
                Mit /craft wird das Crafting Menü geöffnet.
                Also keine Werkbank mehr auf Reisen mitnehmen.
                
                """
        );
    }

    private void sendEnderchestInfo(Player player) {
        player.sendMessage(
                """
                
                Mit /enderchest wird
                deine Enderchest geöffnet.
                
                """
        );
    }

    private void sendFreecamInfo(Player player) {
        player.sendMessage(
                """
                
                Setzt dich in Freecam.
                
                """
        );
    }

    private void sendUpgradeInfo(Player player) {
        player.sendMessage(
                """
                
                Öffnet ein Menu in dem
                late-game Upgrades zu kaufen sind.
                
                """
        );
    }

    private void sendSkipnightInfo(Player player) {
        player.sendMessage(
                """
                
                Mit /skipnight wird
                die Nacht schnell 'übersprungen'
                
                """
        );
    }

    private void sendSwitchworldInfo(Player player) {
        player.sendMessage(
                """
                
                Während der Freecam kannst
                du mit /switchworld zwischen
                normaler Welt, Nether und End
                wechseln.
                
                """
        );
    }

    private void sendTodeInfo(Player player) {
        player.sendMessage(
                """
                
                Mit /tode werden dir von jedem Spieler
                der auf dem Server gewhitelistet ist,
                angezeigt wie viele Tode diese haben.
                
                """
        );
    }

    private void sendEatingInfo(Player player) {
        player.sendMessage(
                """
                
                Wenn du isst, wird deine Hungerleiste
                komplett gefüllt und dementsprechend
                viele Items konsumiert. Heißt also,
                nur noch einmal essen anstatt sich
                15 Möhren reinzuschieben.
                
                """
        );
    }

    private void sendSleepInfo(Player player) {
        player.sendMessage(
                """
                
                Schlafen ist jetzt 'animiert'.
                Wenn man sich ins Bett legt oder
                /skipnight verwendet wird nicht
                direkt zum Tag geskippt sondern
                die Zeit einfach beschleunigt.
                
                """
        );
    }

    private void sendElytrachangeInfo(Player player) {
        player.sendMessage(
                """
                
                Hat man Rüstung an und die Elytra
                in der Hand (oder andersrum) werden
                diese mit einem Rechtsklick
                ausgetauscht.
                
                
                """
        );
    }

    private void sendLightRecipeInfo(Player player) {
        player.sendMessage(
                """
                
                Das Item 'Light' kann nun gecrafted werden.
                Das Rezept ist: Fackeln und in der Mitte ein Eisenblock.
                
                """
        );
    }
}
