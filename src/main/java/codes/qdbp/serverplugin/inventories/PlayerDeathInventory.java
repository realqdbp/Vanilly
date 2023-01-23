package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerDeathInventory {

    Inventory playerDeathInventory;

    public PlayerDeathInventory(ItemStack clickedItem, Serverplugin plugin) {
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String headName = serializer.serialize(clickedItem.displayName());
        String playername = headName.substring(1, headName.indexOf("'"));
        this.playerDeathInventory = Bukkit.createInventory(null, 54, Component.text(playername + " deaths"));

        ItemStack deathIcon = new ItemStack(Material.SKELETON_SKULL);

        int i = plugin.getConfig().getInt("Player." + playername + ".tode");
        while (plugin.getConfig().get("Player." + playername + ".todes." + i + ".cause") != null) {
            ItemMeta meta = deathIcon.getItemMeta();
            meta.lore(List.of(
                    Component.text(plugin.getConfig().get("Player." + playername + ".todes." + i + ".deathNumber") + ""),
                    Component.text(plugin.getConfig().get("Player." + playername + ".todes." + i + ".cause") + ""),
                    Component.text(plugin.getConfig().get("Player." + playername + ".todes." + i + ".time") + ""),
                    Component.text(plugin.getConfig().get("Player." + playername + ".todes." + i + ".mcTime") + ""),
                    Component.text(plugin.getConfig().get("Player." + playername + ".todes." + i + ".position") + "")
            ));

            deathIcon.setItemMeta(meta);
            playerDeathInventory.addItem(deathIcon);
            ++i;
        }
    }


    public Inventory getPlayerDeathInventory() {
        return playerDeathInventory;
    }
}
