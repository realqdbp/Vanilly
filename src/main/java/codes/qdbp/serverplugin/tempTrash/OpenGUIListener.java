//package codes.qdbp.serverplugin.tempTrash;
//
//import codes.qdbp.serverplugin.tempTrash.ArmorStandSpawner;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.entity.Pose;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerMoveEvent;
//
//public class OpenGUIListener implements Listener {
//
//
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent moveEvent) {
//        Player player = moveEvent.getPlayer();
//
//        if (!(player.getPose().equals(Pose.SNEAKING))) return;
//        if (moveEvent.hasExplicitlyChangedBlock()) {
//            ArmorStandSpawner.spawnArmorStand(player, moveEvent.getTo(), "Nice", Material.DIAMOND_SWORD, Material.CHAINMAIL_CHESTPLATE);
//        }
//
//
//    }
//}
