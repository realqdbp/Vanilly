package codes.qdbp.serverplugin.commands;

import codes.qdbp.serverplugin.misc.CustomMapRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;


public class ImageMapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (!(sender instanceof Player player)) return false;
        if (args.length != 1) return false;

        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();

        MapView mapView = Bukkit.createMap(player.getWorld());


        CustomMapRenderer customMapRenderer = new CustomMapRenderer(args[0]);
        mapView.getRenderers().clear();
        while (mapView.getRenderers().iterator().hasNext()) {
            mapView.removeRenderer(mapView.getRenderers().iterator().next());
        }
        mapView.addRenderer(customMapRenderer);

        mapMeta.setMapView(mapView);
        mapItem.setItemMeta(mapMeta);

        player.getInventory().addItem(mapItem);

        return true;
    }
}
