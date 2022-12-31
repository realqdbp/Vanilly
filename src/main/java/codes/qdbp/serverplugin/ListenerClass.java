package codes.qdbp.serverplugin;

import codes.qdbp.serverplugin.commands.FreecamCommand;
import codes.qdbp.serverplugin.inventories.ConfirmInventory;
import codes.qdbp.serverplugin.inventories.ItemTierUpgradeChoiceMenuInventory;
import codes.qdbp.serverplugin.misc.FoodMap;
import codes.qdbp.serverplugin.misc.SleepForward;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class ListenerClass implements Listener {
    File configFile = new File("plugins/Serverplugin", "config.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    HashMap<Material, List<Float>> foodMap = FoodMap.getFoodMap();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        player.showTitle(Title.title(Component.text(ChatColor.DARK_PURPLE + "ψ(｀∇´)ψ"),Component.text(ChatColor.GREEN + "Viel Spaß aufm Server!"),Title.Times.times(Duration.ofSeconds(2),Duration.ofSeconds(8),Duration.ofSeconds(2))));

        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);

        //maybe in future turn this with pom, didn't bring it to work rn
        double currentPluginVersion = 1.7;
        if (c.getDouble("Player." + player.getName() + ".pluginVersion") == currentPluginVersion) return;

        player.sendMessage(
                """
                /Features zeigt jetzt alle Features!
                \n
                """
        );

        c.set("Player." + player.getName() + ".pluginVersion", currentPluginVersion);
        c.save(configFile);
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
            event.setCancelled(true);
            handleEfficiencyUpgradeMenu(player);
        } else if (inventoryTitle.equals(Component.text("Confirm"))) {
            event.setCancelled(true);
            handleConfirmInventory(player, clickedItemMaterial);
        } else if (inventoryTitle.equals(Component.text("Choice Upgrade Tier"))) {
            event.setCancelled(true);
            handleChoiceUpgradeTierMenu(player, clickedItemMaterial);
        } else if (inventoryTitle.equals(Component.text("Death Overview"))) {
            event.setCancelled(true);
        }

    }

    private void handleMainUpgradeMenu(Player player, Material clickedItemMaterial) {
        switch (clickedItemMaterial) {
            case ANVIL -> {
                player.closeInventory();
                player.openInventory(Serverplugin.getUnbreakableUpgradeMenuInventory());
            }
            case FEATHER -> player.closeInventory();
            case NETHERITE_PICKAXE -> {
                player.closeInventory();
                player.openInventory(Serverplugin.getEfficiencyUpgradeMenuInventory());
            }
        }
    }

    private void handleUnbreakableUpgradeMenu(Player player, Material clickedItemMaterial) {
        Inventory playersInventory = player.getInventory();

        //Check validity
        if (!(playersInventory.contains(clickedItemMaterial))) return;
        if (Objects.requireNonNull(playersInventory.getItem(player.getInventory().first(clickedItemMaterial))).getItemMeta().isUnbreakable()) return;
        if (!(playersInventory.contains(Material.NETHERITE_INGOT))) return;
        if (Objects.requireNonNull(playersInventory.getItem(playersInventory.first(Material.NETHERITE_INGOT))).getAmount() < 10) return;
        if (!(playersInventory.contains(Material.NETHER_STAR))) return;
        if (!(playersInventory.contains(Material.OBSIDIAN))) return;
        if (Objects.requireNonNull(playersInventory.getItem(playersInventory.first(Material.OBSIDIAN))).getAmount() < 32) return;
        if (playersInventory.firstEmpty() == -1) return;

        player.closeInventory();
        player.openInventory(new ConfirmInventory(Component.text("Confirm Unbreakable Upgrade"), clickedItemMaterial).getConfirmInventory());
    }

    private void handleEfficiencyUpgradeMenu(Player player) {
        player.closeInventory();
        player.openInventory(new ItemTierUpgradeChoiceMenuInventory().getItemTierUpgradeChoiceMenuInventory());
    }

    private void handleChoiceUpgradeTierMenu(Player player, Material clickedItemMaterial) {
        player.closeInventory();
        player.openInventory(new ConfirmInventory(Component.text("Confirm Efficiency Upgrade"), clickedItemMaterial).getConfirmInventory());
    }

    private void handleConfirmInventory(Player player, Material clickedItemMaterial) {
        if (clickedItemMaterial == Material.RED_WOOL) {
            player.closeInventory();
            player.openInventory(Serverplugin.getUpgradeMenuInventory());
            return;
        }
        if (clickedItemMaterial != Material.GREEN_WOOL) return;

        Inventory topInventory = player.getOpenInventory().getTopInventory();
        String upgradeName = Objects.requireNonNull(topInventory.getItem(4)).displayName().toString();

        if (upgradeName.contains("Confirm Unbreakable Upgrade")) {

            Material itemToUpgradeMaterial = Objects.requireNonNull(topInventory.getItem(4)).getType();
            Inventory playerInventory = player.getInventory();


            //Upgrade Core Item
            ItemStack itemToUpgrade = playerInventory.getItem(playerInventory.first(itemToUpgradeMaterial));
            assert itemToUpgrade != null;
            ItemMeta itemToUpgradeMeta = itemToUpgrade.getItemMeta();
            itemToUpgradeMeta.setUnbreakable(true);
            itemToUpgrade.setItemMeta(itemToUpgradeMeta);
            playerInventory.setItem(playerInventory.first(itemToUpgradeMaterial), itemToUpgrade);

            //Remove Ingredients
            //Netherite Ingots
            ItemStack netheriteIngots = playerInventory.getItem(playerInventory.first(Material.NETHERITE_INGOT));
            assert netheriteIngots != null;
            int netheriteIngotsAmount = netheriteIngots.getAmount() - 10;
            playerInventory.setItem(playerInventory.first(Material.NETHERITE_INGOT), null);
            if (netheriteIngotsAmount > 0) {
                playerInventory.addItem(new ItemStack(Material.NETHERITE_INGOT, netheriteIngotsAmount));
            }

            //Nether Star
            ItemStack netherStar = playerInventory.getItem(playerInventory.first(Material.NETHER_STAR));
            assert netherStar != null;
            int netherStarAmount = netherStar.getAmount() - 1;
            playerInventory.setItem(playerInventory.first(Material.NETHER_STAR), null);
            if (netherStarAmount > 0) {
                playerInventory.addItem(new ItemStack(Material.NETHER_STAR, netherStarAmount));
            }

            //Obsidian
            ItemStack obsidian = playerInventory.getItem(playerInventory.first(Material.OBSIDIAN));
            assert obsidian != null;
            int obsidianAmount = obsidian.getAmount() - 32;
            playerInventory.setItem(playerInventory.first(Material.OBSIDIAN), null);
            if (obsidianAmount > 0) {
                playerInventory.addItem(new ItemStack(Material.OBSIDIAN, obsidianAmount));
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

        if (c.getBoolean("Player." + player.getName() + ".Freecam.state")) FreecamCommand.backportPlayer(c, player, configFile);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) throws IOException {
        if (event.reason().toString().contains("multiplayer.disconnect.idling")) {
            event.setCancelled(true);

            FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);
            Player player = event.getPlayer();

            if (c.getBoolean("Player." + player.getName() + ".afk")) return;

            c.set("Player." + player.getName() + ".afk", true);
            c.save(configFile);
            player.setInvulnerable(true);
            player.playerListName(Component.text(player.getName() + " [" + ChatColor.RED + "AFK" + ChatColor.WHITE + " - " + ChatColor.GREEN + Calendar.getInstance().getTime() + ChatColor.WHITE + "]"));
            player.sendMessage("You're now afk");
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) throws IOException {
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);

        if (!c.getBoolean("Player." + event.getPlayer().getName() + ".afk")) return;

        Player player = event.getPlayer();

        c.set("Player." + player.getName() + ".afk", false);
        c.save(configFile);
        player.setInvulnerable(false);
        player.playerListName(null);
        player.sendMessage("Du bist nicht mehr AFK");
    }


    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        changeElytra(event);
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
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();

        if (foodMap.containsKey(item.getType()) && item.getAmount() > 1) {

            float foodlevel = foodMap.get(player.getActiveItem().getType()).get(0);
            float saturation = foodMap.get(player.getActiveItem().getType()).get(1);

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
        FileConfiguration c = YamlConfiguration.loadConfiguration(configFile);

        Player player = (Player) event.getPlayer();
        if (c.getBoolean("Player." + player.getName() + ".Freecam.state")) return;

        if (event.getView().title().equals(Component.text(player.getName() + "'s Backpack"))) {
            config.set("Player." + player.getName() + ".backpackInventory", player.getOpenInventory().getTopInventory().getContents());
            config.save(configFile);
        }
    }


    @EventHandler
    public void onPlayerGetIntoBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        new SleepForward(player, false).runTaskTimer(Serverplugin.getPlugin(), 0, 0);
    }
}
