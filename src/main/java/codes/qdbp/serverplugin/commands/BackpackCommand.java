package codes.qdbp.serverplugin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;


public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File configFile = new File("plugins/Serverplugin", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (!(sender instanceof Player player)) return false;


        Inventory bpInv = Bukkit.createInventory(player, 54, Component.text(player.getName() + "'s Backpack"));

        if (config.getList("Player." + player.getName() + ".backpackInventory") != null) {
            @SuppressWarnings("unchecked")
            List<ItemStack> items = (List<ItemStack>) config.getList("Player." + player.getName() + ".backpackInventory");
            assert items != null;
            bpInv.setContents(items.toArray(new ItemStack[0]));
        }
        player.openInventory(bpInv);

        return true;
    }
}
