package codes.qdbp.serverplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class ListenerClass implements Listener {
    File configFile = new File("plugins/Serverplugin", "config.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    HashMap<Material, List<Float>> foodMap = Serverplugin.getFoodMap();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.showTitle(Title.title(Component.text(ChatColor.LIGHT_PURPLE + "Viel Spaß"),Component.text(ChatColor.GREEN + "News stehen im Chat!"),Title.Times.times(Duration.ofSeconds(2),Duration.ofSeconds(8),Duration.ofSeconds(2))));
        player.sendMessage(
                ChatColor.WHITE +   "====================================\n" +
                ChatColor.DARK_PURPLE +    "Added: Backups\n" +
                                    "Added: 3D Map vom Server. Aufrufbar unter https://qdbp.codes/map\n" +
                ChatColor.WHITE +   "====================================\n" +
                ChatColor.RED +     "/features, für eine Liste aller Featuers.\n" +
                ChatColor.WHITE +   "====================================\n" + " \n"
        );
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        Player player = event.getPlayer();
        config.set("Player." + player.getName() + ".tode", config.getInt("Player." + player.getName() + ".tode") + 1);
        config.save(configFile);
    }

    @EventHandler
    public void onPlayerAFK(PlayerKickEvent event) throws IOException {
        event.setCancelled(true);
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);

        if (event.reason().toString().contains("multiplayer.disconnect.idling")) {
            Player player = event.getPlayer();
            if (c.getBoolean("Player." + player.getName() + ".afk")) return;
            c.set("Player." + player.getName() + ".afk", true);
            c.save(configFile);
            player.setInvulnerable(true);
            player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + Calendar.getInstance().getTime() + ChatColor.WHITE + "]"));
            player.sendMessage("Du bist jetzt AFK");
        }else event.getPlayer().sendMessage(event.reason().toString());
    }

    @EventHandler
    public void onPlayerDeAFK(PlayerMoveEvent event) throws IOException {
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);
        if (!c.getBoolean("Player." + event.getPlayer().getName() + ".afk")) return;
        Player player = event.getPlayer();
        c.set("Player." + player.getName() + ".afk", false);
        c.save(configFile);
        player.setInvulnerable(false);
        player.playerListName(Component.text(player.getName()));
        player.sendMessage("Du bist nicht mehr AFK");
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();

        ItemStack eventItem = event.getItem();
        if (eventItem == null) return;
        if (eventItem.equals(player.getInventory().getItemInOffHand())) return;
        if (!(eventItem.getType().equals(Material.ELYTRA) || eventItem.getType().toString().toLowerCase().contains("chestplate"))) return;

        ItemStack chestPlateSlotItem = player.getInventory().getChestplate();
        if (eventItem.getType().equals(Material.ELYTRA)) {
            if (chestPlateSlotItem != null && !chestPlateSlotItem.getType().equals(Material.ELYTRA)) {
                player.getInventory().setChestplate(eventItem);
                player.getInventory().setItemInMainHand(chestPlateSlotItem);
            }
        } else {
            if (eventItem.getEnchantments().containsKey(Enchantment.BINDING_CURSE)) return;
            if (chestPlateSlotItem != null && chestPlateSlotItem.getType().equals(Material.ELYTRA)) {
                player.getInventory().setChestplate(eventItem);
                player.getInventory().setItemInMainHand(chestPlateSlotItem);
            }
        }


    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {

        float foodlevel;
        float saturation;

        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();

        if (foodMap.containsKey(item.getType()) && item.getAmount() > 1) {

            foodlevel = foodMap.get(player.getActiveItem().getType()).get(0);
            saturation = foodMap.get(player.getActiveItem().getType()).get(1);

            while (item.getAmount() > 0 && player.getFoodLevel() < 20) {
                player.setFoodLevel((int) (player.getFoodLevel() + foodlevel));
                player.setSaturation(player.getSaturation() + saturation);
                item.setAmount(item.getAmount() - 1);
            }

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryUpdate(InventoryMoveItemEvent event) {
        Block block = Objects.requireNonNull(event.getDestination().getLocation()).getBlock();

        if (block.getType().toString().toLowerCase().contains("chest")) {

            if (config.contains("TrashChests.trash" + block.getX() + block.getY() + block.getZ())) {
                event.getItem().subtract(event.getItem().getAmount());
            }
        }
    }


    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent event) throws IOException {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();

            if (event.getView().title().equals(Component.text(player.getName() + "'s Backpack"))) {
                config.set("Player." + player.getName() + ".backpackInventory", player.getOpenInventory().getTopInventory().getContents());
                config.save(configFile);
            }
        }





        if (event.getInventory().getType().equals(InventoryType.CHEST)) {
            if (event.getView().title().equals(Component.text(event.getPlayer().getName() + "'s Backpack"))) return;
            Block block = Objects.requireNonNull(event.getInventory().getLocation()).getBlock();

            Chest chest = (Chest) block.getState();
            if (config.contains("TrashChests.trash" + block.getX() + block.getY() + block.getZ())) {
                chest.getInventory().clear();
            }
        }
    }


    @EventHandler
    public void onPlayerDestroyLogs(BlockBreakEvent event) throws IOException {
        Block block = event.getBlock();


        //check if it was TRASHCHEST or the SIGN
        if (block.getType().toString().toLowerCase().contains("chest")) {
            if (config.contains("TrashChests.trash" + block.getX() + block.getY() + block.getZ())) {
                config.set("TrashChests.trash" + block.getX() + block.getY() + block.getZ(), null);
                config.save(configFile);
            }
        }
        if ((block.getType().name().contains("WALL_SIGN"))) {
            if (block.getType().toString().toLowerCase().contains("sign")) {
                WallSign signData = (WallSign) block.getState().getBlockData();
                BlockFace attached = signData.getFacing().getOppositeFace();
                Block blockAttached = block.getRelative(attached);

                if (blockAttached.getType().toString().toLowerCase().contains("chest")) {
                    if (config.contains("TrashChests.trash" + blockAttached.getX() + blockAttached.getY() + blockAttached.getZ())) {
                        config.set("TrashChests.trash" + blockAttached.getX() + blockAttached.getY() + blockAttached.getZ(), null);
                        config.save(configFile);
                    }
                }
            }
        }


        //WOODCHUCK
        if (block.getWorld().getEnvironment() == World.Environment.THE_END) return;

        if (block.getType().toString().toLowerCase().contains("log")) {

            if (DestroyLeaves.noLogsInWay(block, 15)) {
                DestroyLeaves.destroyLeaves(block);
            }
        }
    }


    @EventHandler
    public void onPlayerGetIntoBed(PlayerBedEnterEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        new SleepForward(player).runTaskTimer(Serverplugin.getPlugin(), 0, 1);
    }


    @EventHandler
    public void onSignCreate(SignChangeEvent event) throws IOException {
        Block sign = event.getBlock();
        if (!(sign.getType().name().contains("WALL_SIGN"))) return;

        WallSign signData = (WallSign) sign.getState().getBlockData();
        BlockFace attached = signData.getFacing().getOppositeFace();

        Block blockAttached = sign.getRelative(attached);


        if (blockAttached.getType().toString().toLowerCase().contains("chest")) {
            Chest trashChest = (Chest) blockAttached.getState();
            //if doublechest quit
            if (trashChest.getInventory().getSize() == 54) return;

            if (event.lines().toString().toLowerCase().contains("trash")) {
                config.set("TrashChests.trash" + trashChest.getX() + trashChest.getY() + trashChest.getZ() + ".x", trashChest.getX());
                config.set("TrashChests.trash" + trashChest.getX() + trashChest.getY() + trashChest.getZ() + ".y", trashChest.getY());
                config.set("TrashChests.trash" + trashChest.getX() + trashChest.getY() + trashChest.getZ() + ".z", trashChest.getZ());
                config.save(configFile);
            }
        }
    }
}
