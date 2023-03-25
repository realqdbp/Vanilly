package codes.qdbp.serverplugin.misc;

import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class SleepForward extends BukkitRunnable{
    private final Player player;

    //for SkipNight command
    private final boolean wasCommand;


    public SleepForward(Player player, boolean wasCommand) {
        this.player = player;
        this.wasCommand = wasCommand;
    }


    public List<Player> getSleepingPlayers(World world) {
        return world.getPlayers().stream()
                .filter(player -> player.getPose() == Pose.SLEEPING)
                .collect(Collectors.toList());
    }

    @Override
    public void run() {
        if (!(13000 < player.getWorld().getTime())) {
            player.setStatistic(Statistic.TIME_SINCE_REST, 0);
            if (!(player.getWorld().isClearWeather())) player.getWorld().setClearWeatherDuration(1);
            cancel();
            return;
        } else if (player.getWorld().isThundering()) {
            if (player.getWorld().getTime() > 23800) {
                player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                if (!(player.getWorld().isClearWeather())) player.getWorld().setClearWeatherDuration(1);
                cancel();
                return;
            }
        } else if (getSleepingPlayers(player.getWorld()).size() == 0 && !wasCommand) {
            cancel();
            return;
        }
        if (player.getWorld().getTime() < 23940) player.getWorld().setTime(player.getWorld().getTime() + 50);
    }
}

