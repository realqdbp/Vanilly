package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class FreecamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);


        if (sender instanceof Player player) {

            if (config.getBoolean("Player." + player.getName() + ".Freecam.state")) {
                try {
                    ArmorStand as = (ArmorStand) Bukkit.getEntity(UUID.fromString(Objects.requireNonNull(config.getString("Player." + player.getName() + ".Freecam.Placeholder"))));
                    assert as != null;
                    backportPlayer(config, player, configFile, as);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                config.set("Player." + player.getName() + ".Freecam.state", true);

                config.set("Player." + player.getName() + ".Freecam.coords.world", player.getWorld().getName());
                config.set("Player." + player.getName() + ".Freecam.coords.x", player.getLocation().getX());
                config.set("Player." + player.getName() + ".Freecam.coords.y", player.getLocation().getY());
                config.set("Player." + player.getName() + ".Freecam.coords.z", player.getLocation().getZ());
                config.set("Player." + player.getName() + ".Freecam.coords.yaw", player.getLocation().getYaw());
                config.set("Player." + player.getName() + ".Freecam.coords.pitch", player.getLocation().getPitch());
                Location asLoc = player.getLocation();
                ArmorStand as = asLoc.getWorld().spawn(asLoc, ArmorStand.class);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.customName(Component.text(ChatColor.DARK_PURPLE + "Freecam | " + player.getName()));
                as.setCustomNameVisible(true);
                as.setVisible(true);
                as.setInvulnerable(true);
                config.set("Player." + player.getName() + ".Freecam.Placeholder", as.getUniqueId().toString());
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("Mit /sw zwischen den Welten wechseln");
            }
        }
        return true;
    }

    public static void backportPlayer(FileConfiguration config, Player player, File configFile, ArmorStand as) throws IOException {

        player.setGameMode(GameMode.SURVIVAL);
        player.setInvulnerable(true);

        Location loc = new Location(Bukkit.getWorld(Objects.requireNonNull(config.getString("Player." + player.getName() + ".Freecam.coords.world"))), config.getDouble("Player." + player.getName() + ".Freecam.coords.x"), config.getDouble("Player." + player.getName() + ".Freecam.coords.y"), config.getDouble("Player." + player.getName() + ".Freecam.coords.z"), (float) (config.getDouble("Player." + player.getName() + ".Freecam.coords.yaw")) , (float) (config.getDouble("Player." + player.getName() + ".Freecam.coords.pitch")));

        player.teleport(loc);

        config.set("Player." + player.getName() + ".Freecam.state", false);
        config.set("Player." + player.getName() + ".Freecam.coords.world", null);
        config.set("Player." + player.getName() + ".Freecam.coords.x", null);
        config.set("Player." + player.getName() + ".Freecam.coords.y", null);
        config.set("Player." + player.getName() + ".Freecam.coords.z", null);
        config.set("Player." + player.getName() + ".Freecam.coords.yaw", null);
        config.set("Player." + player.getName() + ".Freecam.coords.pitch", null);
        as.remove();
        config.save(configFile);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 1, false, false, false));
        new BukkitRunnable() {
            int secLeft = 5;
            public void run() {
                if (secLeft >= 0) {
                    player.sendMessage(ChatColor.AQUA + "Still invulnurable for " + secLeft);
                    --secLeft;
                } else {
                    player.setInvulnerable(false);
                    cancel();
                }
            }
        }.runTaskTimer(Serverplugin.getPlugin(), 0, 20);
    }
}
