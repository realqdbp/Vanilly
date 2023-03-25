package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.inventories.PlayerDeathInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class InventoryListener implements Listener {

    private final Serverplugin plugin;


    public InventoryListener(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        Component inventoryTitle = event.getView().title().compact();

        if (inventoryTitle.equals(Component.text("Death Overview"))) {
            event.setCancelled(true);
            handleDeathInventory(player, event.getCurrentItem());
        } else if (inventoryTitle.contains(Component.text(" deaths"))) {
            event.setCancelled(true);
        }
    }


    private void handleDeathInventory(Player player, ItemStack clickedItem) {
        player.closeInventory();
        player.openInventory(new PlayerDeathInventory(clickedItem, plugin).getPlayerDeathInventory());
    }

//    private void handleMainUpgradeMenu(Player player, Material clickedItemMaterial) {
//        switch (clickedItemMaterial) {
//            case ANVIL -> {
//                player.closeInventory();
//            }
//            case FEATHER -> player.closeInventory();
//            case NETHERITE_PICKAXE -> {
//                player.closeInventory();
//            }
//        }
//    }
//
//    private void handleUnbreakableUpgradeMenu(Player player, Material clickedItemMaterial) {
//        Inventory playersInventory = player.getInventory();
//
//        //Check validity
//        if (!(playersInventory.contains(clickedItemMaterial))) return;
//        if (Objects.requireNonNull(playersInventory.getItem(player.getInventory().first(clickedItemMaterial))).getItemMeta().isUnbreakable()) return;
//        if (!(playersInventory.contains(Material.NETHERITE_INGOT))) return;
//        if (Objects.requireNonNull(playersInventory.getItem(playersInventory.first(Material.NETHERITE_INGOT))).getAmount() < 10) return;
//        if (!(playersInventory.contains(Material.NETHER_STAR))) return;
//        if (!(playersInventory.contains(Material.OBSIDIAN))) return;
//        if (Objects.requireNonNull(playersInventory.getItem(playersInventory.first(Material.OBSIDIAN))).getAmount() < 32) return;
//        if (playersInventory.firstEmpty() == -1) return;
//
//        player.closeInventory();
//    }
//
//    private void handleConfirmInventory(Player player, Material clickedItemMaterial) {
//        if (clickedItemMaterial == Material.RED_WOOL) {
//            player.closeInventory();
//            return;
//        }
//        if (clickedItemMaterial != Material.GREEN_WOOL) return;
//
//        Inventory topInventory = player.getOpenInventory().getTopInventory();
//        String upgradeName = Objects.requireNonNull(topInventory.getItem(4)).displayName().toString();
//
//        if (upgradeName.contains("Confirm Unbreakable Upgrade")) {
//
//            Material itemToUpgradeMaterial = Objects.requireNonNull(topInventory.getItem(4)).getType();
//            Inventory playerInventory = player.getInventory();
//
//
//            //Upgrade Core Item
//            ItemStack itemToUpgrade = playerInventory.getItem(playerInventory.first(itemToUpgradeMaterial));
//            assert itemToUpgrade != null;
//            ItemMeta itemToUpgradeMeta = itemToUpgrade.getItemMeta();
//            itemToUpgradeMeta.setUnbreakable(true);
//            itemToUpgrade.setItemMeta(itemToUpgradeMeta);
//            playerInventory.setItem(playerInventory.first(itemToUpgradeMaterial), itemToUpgrade);
//
//            //Remove Ingredients
//            //Netherite Ingots
//            ItemStack netheriteIngots = playerInventory.getItem(playerInventory.first(Material.NETHERITE_INGOT));
//            assert netheriteIngots != null;
//            int netheriteIngotsAmount = netheriteIngots.getAmount() - 10;
//            playerInventory.setItem(playerInventory.first(Material.NETHERITE_INGOT), null);
//            if (netheriteIngotsAmount > 0) {
//                playerInventory.addItem(new ItemStack(Material.NETHERITE_INGOT, netheriteIngotsAmount));
//            }
//
//            //Nether Star
//            ItemStack netherStar = playerInventory.getItem(playerInventory.first(Material.NETHER_STAR));
//            assert netherStar != null;
//            int netherStarAmount = netherStar.getAmount() - 1;
//            playerInventory.setItem(playerInventory.first(Material.NETHER_STAR), null);
//            if (netherStarAmount > 0) {
//                playerInventory.addItem(new ItemStack(Material.NETHER_STAR, netherStarAmount));
//            }
//
//            //Obsidian
//            ItemStack obsidian = playerInventory.getItem(playerInventory.first(Material.OBSIDIAN));
//            assert obsidian != null;
//            int obsidianAmount = obsidian.getAmount() - 32;
//            playerInventory.setItem(playerInventory.first(Material.OBSIDIAN), null);
//            if (obsidianAmount > 0) {
//                playerInventory.addItem(new ItemStack(Material.OBSIDIAN, obsidianAmount));
//            }
//        }
//
//
//        player.closeInventory();
//    }

}
