package codes.qdbp.serverplugin.listeners;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.inventories.ConfirmInventory;
import codes.qdbp.serverplugin.inventories.ItemTierUpgradeChoiceMenuInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
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

}
