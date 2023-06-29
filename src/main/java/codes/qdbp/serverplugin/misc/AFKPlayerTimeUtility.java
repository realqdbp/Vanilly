package codes.qdbp.serverplugin.misc;


import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;



public class AFKPlayerTimeUtility {



    public static void justTime(Serverplugin plugin, Player player) {
        BukkitRunnable runnable = new BukkitRunnable() {

            int time = 0;
            int hours, minutes, seconds;
            String timeString;
            @Override
            public void run() {
                ++time;

                hours = time / 3600;
                minutes = (time % 3600) / 60;
                seconds = time % 60;
                timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                Serverplugin.afkPlayerTimes.put(player.getUniqueId(), timeString);
                player.playerListName(
                        Component.text(player.getName() + " [" + NamedTextColor.RED + "AFK" + NamedTextColor.WHITE + " - " + NamedTextColor.GREEN
                        + timeString.replace(" ", "")
                        + NamedTextColor.WHITE + "]"));
            }
        };
        runnable.runTaskTimer(plugin, 0, 20);
        Serverplugin.afkPlayerRunningTasksMap.put(player.getUniqueId(), runnable.getTaskId());
    }

    public static void textAndTime(Serverplugin plugin, Player player, String message) {

        BukkitRunnable runnable = new BukkitRunnable() {
            int time = 0;
            int hours, minutes, seconds;
            String timeString;
            @Override
            public void run() {
                ++time;

                hours = time / 3600;
                minutes = (time % 3600) / 60;
                seconds = time % 60;
                timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                Serverplugin.afkPlayerTimes.put(player.getUniqueId(), timeString);
                player.playerListName(
                        Component.text(player.getName() + " [" + NamedTextColor.RED + "AFK" + NamedTextColor.WHITE + " - " + NamedTextColor.GREEN
                        + timeString + " "
                        + message + NamedTextColor.WHITE + "]"));
            }
        };
        runnable.runTaskTimer(plugin, 0, 20);
        Serverplugin.afkPlayerRunningTasksMap.put(player.getUniqueId(), runnable.getTaskId());
    }
}
