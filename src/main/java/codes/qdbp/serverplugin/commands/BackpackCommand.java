package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class BackpackCommand implements CommandExecutor {

    private final Serverplugin plugin;

    public BackpackCommand(Serverplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;


        Inventory bpInv = Bukkit.createInventory(player, 54, Component.text(player.getName() + "'s Backpack"));

        if (plugin.getConfig().getList("Player." + player.getName() + ".backpackInventory") != null) {
            @SuppressWarnings("unchecked")
            List<ItemStack> items = (List<ItemStack>) plugin.getConfig().getList("Player." + player.getName() + ".backpackInventory");
            assert items != null;
            bpInv.setContents(items.toArray(new ItemStack[0]));
        }
        player.openInventory(bpInv);

        return true;
    }
}
