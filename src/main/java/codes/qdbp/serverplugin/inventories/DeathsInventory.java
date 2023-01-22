package codes.qdbp.serverplugin.inventories;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Objects;


public class DeathsInventory {

    Inventory deathsInventory;

    public DeathsInventory(Player player, Serverplugin plugin) {

        this.deathsInventory = Bukkit.createInventory(null, InventoryType.CHEST, Component.text("Death Overview"));

        //Command sender is first Item Head
        ItemStack headItem = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta commandUserHeadSkullMeta = (SkullMeta) headItem.getItemMeta();
        commandUserHeadSkullMeta.setPlayerProfile(player.getPlayerProfile());
        commandUserHeadSkullMeta.lore(List.of(Component.text(plugin.getConfig().getInt("Player." + player.getName() + ".tode") + " Tode")));
        headItem.setItemMeta(commandUserHeadSkullMeta);

        this.deathsInventory.addItem(headItem);


        //Other item heads
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getWhitelistedPlayers()) {
            if (Objects.equals(offlinePlayer.getName(), player.getName())) continue;
            SkullMeta merta = (SkullMeta) headItem.getItemMeta();
            merta.setOwningPlayer(offlinePlayer);
            merta.lore(List.of(Component.text(plugin.getConfig().getInt("Player." + offlinePlayer.getName() + ".tode") + " Tode")));
            headItem.setItemMeta(merta);
            this.deathsInventory.addItem(headItem);
        }
    }

    public Inventory getDeathsInventory() {
        return deathsInventory;
    }
}
