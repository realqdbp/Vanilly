package codes.qdbp.serverplugin;

import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class SleepForward extends BukkitRunnable{
    private final Player player;

    public SleepForward(Player player) {
        this.player = player;
    }


    public List<Player> getSleepingPlayers(World world) {
        return world.getPlayers().stream()
                .filter(player -> player.getPose() == Pose.SLEEPING)
                .collect(Collectors.toList());
    }

    @Override
    public void run() {
        if (!(player.getWorld().getTime() > 12950 || player.getWorld().getTime() < 23950)) {
            player.setStatistic(Statistic.TIME_SINCE_REST, 0);
            cancel();
            return;
        } else if (getSleepingPlayers(player.getWorld()).size() == 0) {
            cancel();
            return;
        }
        player.getWorld().setTime(player.getWorld().getTime() + 150);
    }
}

