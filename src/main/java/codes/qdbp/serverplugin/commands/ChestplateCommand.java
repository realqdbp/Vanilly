package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.Serverplugin;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChestplateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player) {
            if (Objects.requireNonNull(((Player) sender).getInventory().getChestplate()).getType() == Material.NETHERITE_CHESTPLATE) {
                if (((Player) sender).getInventory().getItemInMainHand().getType() == Material.NETHERITE_INGOT) {
                    if (Objects.requireNonNull(((Player) sender).getInventory().getChestplate()).getEnchantments().containsKey(Enchantment.getByKey(Serverplugin.hasteEnchantment.getKey()))) return true;




                    ((Player) sender).getInventory().setItemInMainHand(new ItemStack(Material.NETHERITE_INGOT, ((Player) sender).getInventory().getItemInMainHand().getAmount() - 1));

                    ItemStack chestplate = ((Player) sender).getInventory().getChestplate();
                    assert chestplate != null;
                    chestplate.addUnsafeEnchantment(Serverplugin.hasteEnchantment, 1);
                    ((Player) sender).getInventory().setChestplate(chestplate);


                }
            }
        }
        return true;
    }
}
