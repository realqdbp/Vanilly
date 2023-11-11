package codes.qdbp.serverplugin.commands

import codes.qdbp.serverplugin.Serverplugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MapImageCmd(val plugin: Serverplugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {


        if (sender !is  Player) return false

        sender.sendMessage(
            sender.inventory.itemInMainHand.itemMeta.persistentDataContainer.has(plugin.invisItemFrameNSK).toString()
        )


        //val bufferedImage = ImageIO.read(URL(args?.get(0)))
        return true
    }


    /*
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


            if (args[1].equals("shrink") && args.length == 2) {

                if (!(player.getInventory().getItemInMainHand().getType().equals(Material.MAP))) return false;

                ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
                MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();

                MapView mapView = Bukkit.createMap(player.getWorld());

                CustomMapRenderer customMapRenderer = new CustomMapRenderer(bufferedImage, true);
                while (mapView.getRenderers().iterator().hasNext()) {
                    mapView.removeRenderer(mapView.getRenderers().iterator().next());
                }
                mapView.addRenderer(customMapRenderer);


                mapMeta.setMapView(mapView);
                mapItem.setItemMeta(mapMeta);


                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                player.getInventory().addItem(mapItem);


                Serverplugin.customImageMaps.put(mapView.getId(), customMapRenderer);

            } else if (args[1].equals("resize") && args.length == 2) {

                if (!(player.getInventory().getItemInMainHand().getType().equals(Material.MAP))) return false;


                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                int factor = gcdByBruteForce(width, height);
                int widthRatio = width / factor;
                int heightRatio = height / factor;

                switch (widthRatio) {
                    case 1, 2, 3, 4, 5, 7, 8, 9, 10, 16, 18, 21, 32, 48 -> {
                    }
                    default -> {
                        player.sendMessage(Component.text("Your Ratio: " + widthRatio + " x " + heightRatio));
                        player.sendMessage(Component.text(
                                """
                                Please use a common aspect ratio. Or crop the immage yourself to one of these types:
                                1x1
                                3x2
                                4x3
                                5x4
                                9x16
                                10x16
                                9x18
                                21x9
                                32x9
                                48x9
                                16x9
                                16x10
                                """));
                        return false;
                    }
                }

                switch (heightRatio) {
                    case 1, 2, 3, 4, 5, 7, 8, 9, 10, 16, 18, 21, 32, 48 -> {
                    }
                    default -> {
                        player.sendMessage(Component.text("Your Ratio: " + widthRatio + " x " + heightRatio));
                        player.sendMessage(Component.text(
                                """
                                Please use a common aspect ratio. Or crop the immage yourself to one of these types:
                                1x1
                                3x2
                                4x3
                                5x4
                                9x16
                                10x16
                                9x18
                                21x9
                                32x9
                                48x9
                                16x9
                                16x10
                                """));
                        return false;
                    }
                }


                int originalBlocksWidth = width / 128;
                int originalBlocksHeight = height / 128;
                double w = (double) width / 128;
                double h = (double) height / 128;
                if (w - originalBlocksWidth != 0 || h - originalBlocksHeight != 0) return false;
                player.sendMessage(Component.text("Choose Layout:"));


                int smallestCommonDivisor = smallestCommonDivisor(originalBlocksWidth, originalBlocksHeight);
                if (originalBlocksWidth == originalBlocksHeight) {
                    player.sendMessage(Component.text("Original | " + originalBlocksWidth + " x " + originalBlocksHeight).clickEvent(ClickEvent.suggestCommand("/mi " + args[0] + " resize " + originalBlocksWidth + " " + originalBlocksHeight)));
                    int tempSize = originalBlocksWidth-1;
                    while (tempSize > 0) {
                        player.sendMessage(Component.text("Smaller | " + tempSize + " x " + tempSize).clickEvent(ClickEvent.suggestCommand("/mi " + args[0] + " resize " + tempSize + " " + tempSize)));
                        --tempSize;
                    }
                } else if (smallestCommonDivisor == 1) {
                    player.sendMessage(Component.text("Original | " + originalBlocksWidth + " x " + originalBlocksHeight).clickEvent(ClickEvent.suggestCommand("/mi " + args[0] + " resize " + originalBlocksWidth + " " + originalBlocksHeight)));
                } else {
                    player.sendMessage(Component.text("Original | " + originalBlocksWidth + " x " + originalBlocksHeight).clickEvent(ClickEvent.suggestCommand("/mi " + args[0] + " resize " + originalBlocksWidth + " " + originalBlocksHeight)));

                    double tempWidth = (double) originalBlocksWidth / smallestCommonDivisor;
                    double tempHeight = (double) originalBlocksHeight / smallestCommonDivisor;

                    while ((tempWidth - (int) tempWidth) == 0 && (tempHeight - (int) tempHeight) == 0) {
                        player.sendMessage(Component.text("Smaller | " + ((int) tempWidth) + " x " + ((int) tempHeight)).clickEvent(ClickEvent.suggestCommand("/mi " + args[0] + " resize " + ((int) tempWidth) + " " + ((int) tempHeight))));
                        tempWidth /= smallestCommonDivisor;
                        tempHeight /= smallestCommonDivisor;
                    }
                }


            } else if (args[1].equals("resize") && args[2].matches("\\d+") && args[3].matches("\\d+") && args.length == 4) {

                int blocksWidth = Integer.parseInt(args[2]);
                int blocksHeight = Integer.parseInt(args[3]);
                int pixelWidth = blocksWidth*128;
                int pixelHeight = blocksHeight*128;

                BufferedImage resizedImage = Scalr.resize(bufferedImage, pixelWidth, pixelHeight);

                int smallImagesToDo = blocksWidth * blocksHeight;
                BufferedImage tempImage;

                int i = 1;
                int heightCounterThing = 1;
                int widthCounterThing = 1;
                while (i <= smallImagesToDo) {
                    while (heightCounterThing <= blocksHeight) {
                        tempImage = resizedImage.getSubimage((128 * (widthCounterThing - 1)), (128 * (heightCounterThing - 1)), 128, 128);
                        ++i;
                        ++heightCounterThing;

                        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
                        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
                        MapView mapView = Bukkit.createMap(player.getWorld());

                        CustomMapRenderer customMapRenderer = new CustomMapRenderer(tempImage, false);
                        while (mapView.getRenderers().iterator().hasNext()) {
                            mapView.removeRenderer(mapView.getRenderers().iterator().next());
                        }
                        mapView.addRenderer(customMapRenderer);

                        mapMeta.setMapView(mapView);
                        mapItem.setItemMeta(mapMeta);
                        player.getWorld().dropItem(player.getLocation(), mapItem);

                        Serverplugin.customImageMaps.put(mapView.getId(), customMapRenderer);
                    }
                    heightCounterThing = 1;
                    ++widthCounterThing;
                }



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return true;
    }

    int gcdByBruteForce(int n1, int n2) {
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    int smallestCommonDivisor(int numberOne, int numberTwo) {
        double smallestCommonDivisor = 2d;
        double smallerNumber = Math.min(numberOne, numberTwo);

        while (smallestCommonDivisor <= smallerNumber) {
            if (((numberOne / smallestCommonDivisor) - ((int) (numberOne / smallestCommonDivisor)) == 0) && ((numberTwo / smallestCommonDivisor) - (int) (numberTwo / smallestCommonDivisor)) == 0) {
                return (int) smallestCommonDivisor;
            }
            ++smallestCommonDivisor;
        }

        return 1;
    }
     */
}