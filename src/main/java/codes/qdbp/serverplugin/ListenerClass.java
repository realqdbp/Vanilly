package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.FreecamCommand;
import codes.qdbp.serverplugin.inventories.ConfirmInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        player.showTitle(Title.title(Component.text(ChatColor.DARK_PURPLE + "Viel Spaß"),Component.text(ChatColor.GREEN + "News stehen im Chat!"),Title.Times.times(Duration.ofSeconds(2),Duration.ofSeconds(8),Duration.ofSeconds(2))));
        player.sendMessage(
                ChatColor.WHITE +   "====================================\n" +
                ChatColor.DARK_PURPLE +    "Added: Freecam\n" +
                ChatColor.WHITE +   "====================================\n" +
                ChatColor.RED +     "/features, für eine Liste aller Featuers.\n" +
                ChatColor.WHITE +   "====================================\n" + " \n"
        );
        if (config.getInt("Player." + player.getName() + ".hasteEnchantmentLevel") == 0) return;
        config.set("Player." + player.getName() + ".hasteEnchantmentLevel", 1);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @EventHandler
    public void playerInventory(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        Material clickedItemMaterial = Objects.requireNonNull(event.getCurrentItem()).getType();
        Component inventoryTitle = event.getView().title().compact();

        if (inventoryTitle.equals(Component.text("Upgrade Menu"))) {
            event.setCancelled(true);
            handleMainUpgradeMenu(player, clickedItemMaterial);
        } else if (inventoryTitle.equals(Component.text("Unbreakable Upgrades"))) {
            event.setCancelled(true);
            handleUnbreakableUpgradeMenu(player, clickedItemMaterial);
        } else if (inventoryTitle.equals(Component.text("Efficiency Upgrades"))) {
            handleEfficiencyUpgradeMenu(player, clickedItemMaterial);
        } else if (inventoryTitle.equals(Component.text("Confirm"))) {
            event.setCancelled(true);
            handleConfirmInventory(player, clickedItemMaterial);
        }
    }

    private void handleMainUpgradeMenu(Player player, Material clickedItemMaterial) {
        switch (clickedItemMaterial) {
            case ANVIL:
                player.closeInventory();
                player.openInventory(Serverplugin.getUnbreakableUpgradeMenuInventory());
                break;
            case FEATHER:
                player.closeInventory();
                break;
            case NETHERITE_PICKAXE:
                player.closeInventory();
                break;
        }
    }

    private void handleUnbreakableUpgradeMenu(Player player, Material clickedItemMaterial) {
        Inventory playersInventory = player.getInventory();

        //Check validity
        if (Objects.requireNonNull(playersInventory.getItem(playersInventory.first(Material.NETHERITE_INGOT))).getAmount() < 10) return;
        if (playersInventory.firstEmpty() == -1) return;
        if (!(playersInventory.contains(clickedItemMaterial))) return;
        if (Objects.requireNonNull(playersInventory.getItem(player.getInventory().first(clickedItemMaterial))).getItemMeta().isUnbreakable()) return;

        player.closeInventory();
        player.openInventory(new ConfirmInventory(Component.text("Confirm Unbreakable Upgrade"), clickedItemMaterial).getConfirmInventory());
    }

    private void handleEfficiencyUpgradeMenu(Player player, Material clickedItemMaterial) {
    }

    private void handleConfirmInventory(Player player, Material clickedItemMaterial) {
        if (clickedItemMaterial != Material.GREEN_WOOL) {
            player.closeInventory();
            player.openInventory(Serverplugin.getUpgradeMenuInventory());
            return;
        }

        Inventory topInventory = player.getOpenInventory().getTopInventory();
        String wurst = Objects.requireNonNull(topInventory.getItem(4)).displayName().toString();

        if (wurst.contains("Confirm Unbreakable Upgrade")) {

            Material itemToUpgradeMaterial = Objects.requireNonNull(topInventory.getItem(4)).getType();
            Inventory playerInventory = player.getInventory();

            //Renew item with unbreakable
            ItemStack item = playerInventory.getItem(player.getInventory().first(itemToUpgradeMaterial));
            assert item != null;
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setUnbreakable(true);
            item.setItemMeta(itemMeta);
            playerInventory.setItem(playerInventory.first(itemToUpgradeMaterial), item);

            //Remove other Ingredients
            ItemStack netheriteIngots = playerInventory.getItem(playerInventory.first(Material.NETHERITE_INGOT));
            assert netheriteIngots != null;
            int netheriteIngotsAmount = netheriteIngots.getAmount() - 10;
            playerInventory.setItem(playerInventory.first(Material.NETHERITE_INGOT), null);
            if (netheriteIngotsAmount > 0) {
                playerInventory.addItem(new ItemStack(Material.NETHERITE_INGOT, netheriteIngotsAmount));
            }
        }


        player.closeInventory();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        Player player = event.getPlayer();
        config.set("Player." + player.getName() + ".tode", config.getInt("Player." + player.getName() + ".tode") + 1);
        config.save(configFile);
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws IOException {
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);
        Player player = event.getPlayer();

        if (!(c.getBoolean("Player." + player.getName() + ".Freecam.state"))) return;

        ArmorStand as = (ArmorStand) Bukkit.getEntity(UUID.fromString(Objects.requireNonNull(c.getString("Player." + player.getName() + ".Freecam.Placeholder"))));
        assert as != null;
        FreecamCommand.backportPlayer(c, player, configFile, as);
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }

    @EventHandler
    public void onPlayerAFK(PlayerKickEvent event) throws IOException {
        event.setCancelled(true);
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);
        Player player = event.getPlayer();
        if (c.getBoolean("Player." + player.getName() + ".Freecam.state")) {
            ArmorStand as = (ArmorStand) Bukkit.getEntity(UUID.fromString(Objects.requireNonNull(c.getString("Player." + player.getName() + ".Freecam.Placeholder"))));
            assert as != null;
            FreecamCommand.backportPlayer(c, player, configFile, as);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        if (event.reason().toString().contains("multiplayer.disconnect.idling")) {
            if (c.getBoolean("Player." + player.getName() + ".afk")) return;
            c.set("Player." + player.getName() + ".afk", true);
            c.save(configFile);
            player.setInvulnerable(true);
            player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + Calendar.getInstance().getTime() + ChatColor.WHITE + "]"));
            player.sendMessage("Du bist jetzt AFK");
        } else {
            event.setCancelled(false);
        }
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
        changeElytra(event);

        enchantHasteThing(event);
    }

    public void enchantHasteThing(PlayerInteractEvent event) {
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);
        Player player = event.getPlayer();

        if (player.getEquipment().getChestplate() == null) return;

        if (!(player.getPose().equals(Pose.SNEAKING))) return;

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (c.getInt("Player." + player.getName() + ".hasteEnchantmentLevel") == 10) {
                c.set("Player." + player.getName() + ".hasteEnchantmentLevel", 0);
            }else{
                c.set("Player." + player.getName() + ".hasteEnchantmentLevel", c.getInt("Player." + player.getName() + ".hasteEnchantmentLevel") + 1);
            }
            try {
                c.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage("Haste Level is now: " + c.getInt("Player." + player.getName() + ".hasteEnchantmentLevel"));
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);
            if (c.getInt("Player." + player.getName() + ".hasteEnchantmentLevel") == 0) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 144000, (c.getInt("Player." + player.getName() + ".hasteEnchantmentLevel") - 1), true, true, true));
        }
    }

    public void changeElytra(PlayerInteractEvent event) {

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
    }


    @EventHandler
    public void onPlayerGetIntoBed(PlayerBedEnterEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        new SleepForward(player).runTaskTimer(Serverplugin.getPlugin(), 0, 2);
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
