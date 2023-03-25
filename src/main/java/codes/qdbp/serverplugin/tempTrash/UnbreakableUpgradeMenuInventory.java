//package codes.qdbp.serverplugin.tempTrash;
//
//import net.kyori.adventure.text.Component;
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.util.List;
//
//public class UnbreakableUpgradeMenuInventory {
//
//    Inventory unbreakableUpgradeMenuInventory;
//
//
//    public UnbreakableUpgradeMenuInventory() {
//        this.unbreakableUpgradeMenuInventory = Bukkit.createInventory(null, 27, Component.text("Unbreakable Upgrades"));
//
//
//        //Create Items for this Inventory
//        ItemStack netheriteHelmet = new ItemStack(Material.NETHERITE_HELMET);
//        ItemStack netheriteChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
//        ItemStack netheriteLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
//        ItemStack netheriteBoots = new ItemStack(Material.NETHERITE_BOOTS);
//        ItemStack netheriteSword = new ItemStack(Material.NETHERITE_SWORD);
//        ItemStack netheriteShovel = new ItemStack(Material.NETHERITE_SHOVEL);
//        ItemStack netheritePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
//        ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE);
//        ItemStack netheriteHoe = new ItemStack(Material.NETHERITE_HOE);
//        ItemStack elytra = new ItemStack(Material.ELYTRA);
//
//
//        //Create Descriptions on Items with ItemMeta
//        ItemMeta netheriteHelmetMeta = netheriteHelmet.getItemMeta();
//        netheriteHelmetMeta.displayName(Component.text("Upgrade Helmet"));
//        netheriteHelmetMeta.lore(setItemDescription("Netherite Helmet"));
//        netheriteHelmet.setItemMeta(netheriteHelmetMeta);
//
//        ItemMeta netheriteChestplateMeta = netheriteChestplate.getItemMeta();
//        netheriteChestplateMeta.displayName(Component.text("Upgrade Chestplate"));
//        netheriteChestplateMeta.lore(setItemDescription("Netherite Chestplate"));
//        netheriteChestplate.setItemMeta(netheriteChestplateMeta);
//
//        ItemMeta netheriteLeggingsMeta = netheriteLeggings.getItemMeta();
//        netheriteLeggingsMeta.displayName(Component.text("Upgrade Leggings"));
//        netheriteLeggingsMeta.lore(setItemDescription("Netherite Leggings"));
//        netheriteLeggings.setItemMeta(netheriteLeggingsMeta);
//
//        ItemMeta netheriteBootsMeta = netheriteBoots.getItemMeta();
//        netheriteBootsMeta.displayName(Component.text("Upgrade Boots"));
//        netheriteBootsMeta.lore(setItemDescription("Netherite Boots"));
//        netheriteBoots.setItemMeta(netheriteBootsMeta);
//
//        ItemMeta netheriteSwordMeta = netheriteSword.getItemMeta();
//        netheriteSwordMeta.displayName(Component.text("Upgrade Sword"));
//        netheriteSwordMeta.lore(setItemDescription("Netherite Sword"));
//        netheriteSword.setItemMeta(netheriteSwordMeta);
//
//        ItemMeta netheriteShovelMeta = netheriteShovel.getItemMeta();
//        netheriteShovelMeta.displayName(Component.text("Upgrade Shovel"));
//        netheriteShovelMeta.lore(setItemDescription("Netherite Shovel"));
//        netheriteShovel.setItemMeta(netheriteShovelMeta);
//
//        ItemMeta netheritePickaxeMeta = netheritePickaxe.getItemMeta();
//        netheritePickaxeMeta.displayName(Component.text("Upgrade Pickaxe"));
//        netheritePickaxeMeta.lore(setItemDescription("Netherite Pickaxe"));
//        netheritePickaxe.setItemMeta(netheritePickaxeMeta);
//
//        ItemMeta netheriteAxeMeta = netheriteAxe.getItemMeta();
//        netheriteAxeMeta.displayName(Component.text("Upgrade Axe"));
//        netheriteAxeMeta.lore(setItemDescription("Netherite Axe"));
//        netheriteAxe.setItemMeta(netheriteAxeMeta);
//
//        ItemMeta netheriteHoeMeta = netheriteHoe.getItemMeta();
//        netheriteHoeMeta.displayName(Component.text("Upgrade Hoe"));
//        netheriteHoeMeta.lore(setItemDescription("Netherite Hoe"));
//        netheriteHoe.setItemMeta(netheriteHoeMeta);
//
//        ItemMeta elytraMeta = elytra.getItemMeta();
//        elytraMeta.displayName(Component.text("Upgrade Elytra"));
//        elytraMeta.lore(setItemDescription("Elytra"));
//        elytra.setItemMeta(elytraMeta);
//
//
//        //Set items in correct slots from inventory
//        this.unbreakableUpgradeMenuInventory.setItem(1, netheriteHelmet);
//        this.unbreakableUpgradeMenuInventory.setItem(3, netheriteChestplate);
//        this.unbreakableUpgradeMenuInventory.setItem(5, netheriteLeggings);
//        this.unbreakableUpgradeMenuInventory.setItem(7, netheriteBoots);
//
//        this.unbreakableUpgradeMenuInventory.setItem(9, netheriteSword);
//        this.unbreakableUpgradeMenuInventory.setItem(11, netheriteShovel);
//        this.unbreakableUpgradeMenuInventory.setItem(13, netheritePickaxe);
//        this.unbreakableUpgradeMenuInventory.setItem(15, netheriteAxe);
//        this.unbreakableUpgradeMenuInventory.setItem(17, netheriteHoe);
//
//        this.unbreakableUpgradeMenuInventory.setItem(22, elytra);
//    }
//
//    private List<Component> setItemDescription (String itemName) {
//        return List.of(
//                Component.text("REQUIREMENTS:"),
//                Component.text(itemName + " x 1"),
//                Component.text("Netherite Ingots x 10 (Stacked)"),
//                Component.text("Nether Star x 1"),
//                Component.text("Obsidian x 32 (Stacked)"),
//                Component.text("Empty Inventory Slot x 1")
//        );
//    }
//
//    public Inventory getUnbreakableUpgradeMenuInventory() {
//        return unbreakableUpgradeMenuInventory;
//    }
//}
