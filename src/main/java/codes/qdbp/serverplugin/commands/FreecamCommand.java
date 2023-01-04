package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;


public class FreecamCommand implements CommandExecutor {

    private final Serverplugin plugin;

    public FreecamCommand(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (Serverplugin.freecamPlayerMap.containsKey(player.getUniqueId())) {

                backportPlayer(plugin, player, false);

            }else{
                Location playerLocation = player.getLocation();

                ArmorStand as = playerLocation.getWorld().spawn(playerLocation, ArmorStand.class);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.customName(Component.text(ChatColor.DARK_PURPLE + "Freecam | " + player.getName()));
                as.setCustomNameVisible(true);
                as.setVisible(true);
                as.setInvulnerable(true);

                Serverplugin.freecamPlayerMap.put(player.getUniqueId(), as);

                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("Mit /sw zwischen den Welten wechseln");
            }
        }
        return true;
    }

    public static void backportPlayer(Serverplugin plugin, Player player, boolean immediatly) {

        player.setGameMode(GameMode.SURVIVAL);


        ArmorStand as = Serverplugin.freecamPlayerMap.get(player.getUniqueId());

        player.teleport(as.getLocation());
        as.remove();

        Serverplugin.freecamPlayerMap.remove(player.getUniqueId());



        if (immediatly) return;

        player.setInvulnerable(true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1, false, false, false));

        new BukkitRunnable() {
            int secLeft = 3;
            public void run() {
                if (secLeft >= 0) {
                    --secLeft;
                } else {
                    player.setInvulnerable(false);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
