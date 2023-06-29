package codes.qdbp.serverplugin.misc;

import codes.qdbp.serverplugin.Serverplugin;
import codes.qdbp.serverplugin.inventories.ConfirmInventory;
import codes.qdbp.serverplugin.inventories.UnbreakableInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.UUID;

public class UpgradeMenuUtility implements Listener {

    private Upgrades currentUpgrade;
    private UpgradeOption currentUpgradeChoice;
    private UpgradeMenus currentMenu;
    private UpgradeTiers currentUpgradeTier;


    public void updateCurrentUpgrade(Upgrades upgrade) {
        currentUpgrade = upgrade;
    }

    public void updateCurrentUpgradeChoice(UpgradeOption upgradeOption) {
        currentUpgradeChoice = upgradeOption;
    }

    public void updateCurrentMenu(UpgradeMenus menu) {
        currentMenu = menu;
    }

    public void updateCurrentUpgradeTier(UpgradeTiers tier) {
        currentUpgradeTier = tier;
    }

    public UpgradeMenus getCurrentMenu() {
        return currentMenu;
    }

    public UpgradeOption getCurrentUpgradeChoice() {
        return currentUpgradeChoice;
    }

    public Upgrades getCurrentUpgrade() {
        return currentUpgrade;
    }

    public UpgradeTiers getCurrentUpgradeTier() {
        return currentUpgradeTier;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().title().compact().toString().contains("--- ")) return;
        if (event.getCurrentItem() == null) return;


        UUID playerUUID = event.getWhoClicked().getUniqueId();
        Material clickedItemMaterial = Objects.requireNonNull(event.getCurrentItem()).getType();

        if (Serverplugin.menuUtilityMap.get(playerUUID) == null) return;
        if (Serverplugin.menuUtilityMap.get(playerUUID).getCurrentMenu() == UpgradeMenus.MAIN) {
            event.setCancelled(true);

            handleUpgradeMenus(playerUUID, clickedItemMaterial);

        } else if (Serverplugin.menuUtilityMap.get(playerUUID).getCurrentMenu() == UpgradeMenus.CONFIRM) {

            int clickedSlot = event.getRawSlot();
            event.setCancelled(clickedSlot != 19 && clickedSlot != 21 && clickedSlot != 23 && clickedSlot != 25 && (clickedSlot < 27 || clickedSlot > 62));

        } else if (Serverplugin.menuUtilityMap.get(playerUUID).getCurrentMenu() == UpgradeMenus.TIERS) {
            event.setCancelled(true);

            handleTierMenu(playerUUID, clickedItemMaterial);
        } else if (Serverplugin.menuUtilityMap.get(playerUUID).getCurrentMenu() == UpgradeMenus.UNBREAKABLE) {
            event.setCancelled(true);
            handleUnbreakableMenu(playerUUID, clickedItemMaterial);
        }

    }


    private void handleUnbreakableMenu(UUID uuid, Material material) {
        switch (material) {
            case NETHERITE_HELMET -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.HELMET);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_CHESTPLATE -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.CHESTPLATE);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_LEGGINGS -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.LEGGINGS);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_BOOTS -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.BOOTS);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_SWORD -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.SWORD);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_SHOVEL -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.SHOVEL);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_PICKAXE -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.PICKAXE);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_AXE -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.AXE);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_HOE -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.HOE);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case ELYTRA -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeChoice(UpgradeOption.ELYTRA);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
        }
    }

    private void handleUpgradeMenus(UUID uuid, Material material) {
        switch (material) {
            case DIAMOND_PICKAXE -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgrade(Upgrades.EFFICIENCY);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.EFFICIENCY);
            }
            case ANVIL -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgrade(Upgrades.UNBREAKABLE);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.UNBREAKABLE);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new UnbreakableInventory().getUnbreakableInventory());
            }
            case FEATHER -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgrade(Upgrades.MOVEMENTSPEED);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.MOVEMENTSPEED);

            }




                /*
        Upgrade Tiers: EFFICIENCY WISE
         Iron
            Upgrade to Tier 6 / VI
            Requirements:
                Vorheriges Upgrade
                Netherite Ingot x 1
                Feather x 4
                Speed Pot lvl 1 x 1
                Leerer INventarplatz x 1

          Gold
            Upgrade to Tier 7 / VII
            Requirements:
                Vorheriges Upgrade
                Leerer INventarplatz x 1
                Feather x 8
                Speed pot lvl 2 x 1
                Netherite ingot x 1

          Diamond
            Upgrade to tier 8 / VIII
            Requirements:
                Vorheriges Upgrade
                Leerer INventarplatz x 1
                Netherite ingot x 1
                Feather x 16
                Speed pot lvl 2 x 1
                Honey x 4

          Emerald
            Upgrade to tier 9 / IX
            Requirements:
                Vorheriges Upgrade
                Leerer INventarplatz x 1
                Netherite ingot x 1
                Fewather x 32
                Speed pot lvl 3 x 1
                Honey x 8
                End Crystal x 1

          Netherite
            Upgrade to tier 10 / X
            Requirements:
                Vorheriges Upgrade
                Leerer INventarplatz x 1
                Netheite ingot x 1
                Featehr x 64
                Speed pot lvl 3 x 1
                Honey x 16
                End Crystal x 3
                Nether star x 1

 */
        }
    }


    private void handleTierMenu(UUID uuid, Material material) {
        switch (material) {
            case IRON_BLOCK -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeTier(UpgradeTiers.MK1);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case GOLD_BLOCK -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeTier(UpgradeTiers.MK2);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case DIAMOND_BLOCK -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeTier(UpgradeTiers.MK3);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case EMERALD_BLOCK -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeTier(UpgradeTiers.MK4);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
            case NETHERITE_BLOCK -> {
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentUpgradeTier(UpgradeTiers.MK5);
                Serverplugin.menuUtilityMap.get(uuid).updateCurrentMenu(UpgradeMenus.CONFIRM);
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(new ConfirmInventory(uuid).getConfirmInventory());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {


        if (event.getView().title().compact().toString().contains("Confirm")) {

            Inventory inventory = event.getInventory();
            ItemStack item1 = inventory.getItem(19);
            ItemStack item2 = inventory.getItem(21);
            ItemStack item3 = inventory.getItem(23);
            ItemStack item4 = inventory.getItem(25);


            if (item1 == null || item2 == null || item3 == null || item4 == null || item1.getType() != Objects.requireNonNull(inventory.getItem(10)).getType() || !item2.equals(inventory.getItem(12)) || !item3.equals(inventory.getItem(14)) || !item4.equals(inventory.getItem(16))) {

                returnItemsToInventory((Player) event.getPlayer());
            } else if (event.getReason() == InventoryCloseEvent.Reason.PLAYER) {
                Player player = (Player) event.getPlayer();
                player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);

                ItemMeta itemMeta = item1.getItemMeta();
                itemMeta.setUnbreakable(true);
                item1.setItemMeta(itemMeta);
                player.getInventory().addItem(item1);
            }
        }
    }


    public void returnItemsToInventory(Player player) {

        Inventory inventory = player.getOpenInventory().getTopInventory();

        for (int i = 17; i <= 26; ++i) {
            if (inventory.getItem(i) == null || Objects.requireNonNull(inventory.getItem(i)).getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) continue;
            player.getInventory().addItem(Objects.requireNonNull(inventory.getItem(i)));
        }
    }
}
