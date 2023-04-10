package codes.qdbp.serverplugin.misc;

import jakarta.xml.bind.DatatypeConverter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SerializableAs("CustomMapRenderer")
public class CustomMapRenderer extends MapRenderer implements ConfigurationSerializable {


    private boolean initialized = true;

    private final boolean resize;
    private final BufferedImage bufferedImage;


    public CustomMapRenderer(BufferedImage bufferedImage, boolean resize) {
        this.bufferedImage = bufferedImage;
        this.resize = resize;
    }

    public CustomMapRenderer(String imageBase64, boolean resize) throws IOException {
        this.resize = resize;

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageBase64);

        this.bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (!initialized) return;
        initialized = false;

        if (bufferedImage == null) return;

        if (resize) {
            canvas.drawImage(0, 0, MapPalette.resizeImage(bufferedImage));
        } else {

            canvas.drawImage(0, 0, bufferedImage);
        }

    }

    @Override
    public @NotNull Map<String, Object> serialize() {

        Map<String, Object> data = new HashMap<>();

        data.put("resize", this.resize);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data.put("image", Base64.getEncoder().encodeToString(os.toByteArray()));

        return data;
    }

    @SuppressWarnings("Never Used")
    public static CustomMapRenderer deserialize(@NotNull Map<String, Object> args) throws IOException {

        return new CustomMapRenderer(args.get("image").toString(), (Boolean) args.get("resize"));
    }

}
