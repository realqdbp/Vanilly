//package codes.qdbp.serverplugin.tempTrash;
//
//import codes.qdbp.serverplugin.Serverplugin;
//import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
//import org.bukkit.Material;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.PlayerInventory;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Objects;
//
//public class OpenUpgradeMenuCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//
//        if (!(sender instanceof Player player)) return false;
//
//
//
//        if (args.length < 1) player.openInventory(Serverplugin.getUpgradeMenuInventory());
//
//        if (args[0].equals("info")) {
//            player.sendMessage("Usage: /upgrade <upgradeName> <tierNumber>");
//            player.sendMessage("Example: /upgrade efficiency 6");
//            player.sendMessage("Note that you need to hold the item to upgrade in your main hand");
//            player.sendMessage("To see what you'll need you can type: /upgrade <upgradeName> requirements");
//        }
//
//
//        if (args[0].equals("efficiency")) {
//            if (args[1].equals("requirements")) {
//                player.openInventory(new ItemTierUpgradeChoiceMenuInventory("itemToUpgrade").getItemTierUpgradeChoiceMenuInventory());
//            } else {
//                int enchantTo = Integer.parseInt(args[1]);
//                switch (enchantTo) {
//                    case 6:
//                        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
//                        if (!(itemInMainHand.getType().equals(Material.NETHERITE_SHOVEL)
//                                || itemInMainHand.getType().equals(Material.NETHERITE_PICKAXE)
//                                || itemInMainHand.getType().equals(Material.NETHERITE_AXE)
//                                || itemInMainHand.getType().equals(Material.NETHERITE_HOE))) {
//
//                            return false;
//                        }
//                    if (!((enchantTo - 1) == itemInMainHand.getEnchantmentLevel(Enchantment.DIG_SPEED))) return false;
//                    PlayerInventory inv = player.getInventory();
//                    if (!(inv.contains(Material.NETHERITE_INGOT, 1))) return false;
//                    if (!(Objects.requireNonNull(inv.getItem(inv.first(Material.FEATHER))).getAmount() >= 4)) return false;
//                    PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
//                    String realName = serializer.serialize(Objects.requireNonNull(inv.getItem(inv.first(Material.POTION))).displayName());
//                    if (!(realName.contains("Speed"))) return false;
//                    if (inv.firstEmpty() == -1) return false;
//                }
//            }
//        }
//
//
//
//        return false;
//    }
//}
