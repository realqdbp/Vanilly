package codes.qdbp.serverplugin.misc;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Utils {

    public static ItemStack createItem(Material material, String displayName, Component... lore) {


        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Component.text(displayName));
        if (lore.length >= 1) itemMeta.lore(Arrays.stream(lore).toList());
        item.setItemMeta(itemMeta);

        return item;
    }

}
