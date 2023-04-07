package codes.qdbp.serverplugin.misc;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CustomMapRenderer extends MapRenderer {

    private final String imageURL;

    private boolean initialized = true;

    public CustomMapRenderer(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (!initialized) return;
        initialized = false;
        try {
            URL url = new URL(imageURL);
            BufferedImage bufferedImage = ImageIO.read(url);

            if (bufferedImage == null) return;


            canvas.drawImage(0, 0, MapPalette.resizeImage(bufferedImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
