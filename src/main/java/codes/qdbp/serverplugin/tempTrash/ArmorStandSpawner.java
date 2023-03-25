//package codes.qdbp.serverplugin.tempTrash;
//
//import net.kyori.adventure.text.Component;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.entity.ArmorStand;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.EquipmentSlot;
//import org.bukkit.inventory.ItemStack;
//
//public class ArmorStandSpawner {
//
//
//    public static void spawnArmorStand(Player player, Location playerLocation, String header, Material handSlot, Material chestSlot) {
//        Location spawnLocation = playerLocation.add(10d, 10d, 0d);
//
//        ArmorStand armorStand = player.getWorld().spawn(spawnLocation, ArmorStand.class);
//
//        armorStand.setGravity(false);
//        armorStand.setCanMove(false);
//        armorStand.setCanPickupItems(false);
//        armorStand.customName(Component.text(header));
//        armorStand.setCustomNameVisible(true);
//        armorStand.setInvulnerable(true);
//        armorStand.setVisible(true);
//
//        armorStand.setItem(EquipmentSlot.HAND, new ItemStack(handSlot));
//        armorStand.setItem(EquipmentSlot.CHEST, new ItemStack(chestSlot));
//    }
//
//}
