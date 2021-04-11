package io.github.pdkst.tank;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Slf4j
public class ResourceManager {
    public static BufferedImage tankImageUp;
    public static BufferedImage tankImageDown;
    public static BufferedImage tankImageLeft;
    public static BufferedImage tankImageRight;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            final InputStream stream = ResourceManager.class.getResourceAsStream("/tank2.png");
            Objects.requireNonNull(stream);
            tankImageUp = ImageIO.read(stream);
            tankImageRight = rotate(tankImageUp, 90);
            tankImageDown = rotate(tankImageUp, 180);
            tankImageLeft = rotate(tankImageUp, 270);

            for (int i = 0; i < explodes.length; i++) {
                final InputStream resource = ResourceManager.class.getResourceAsStream("/images/e" + (i + 1) + ".gif");
                Objects.requireNonNull(resource);
                explodes[i] = ImageIO.read(resource);
            }
        } catch (IOException e) {
            System.out.println("加载失败");
            e.printStackTrace();
        }
    }

    private static BufferedImage rotate(BufferedImage image, final int deg) {
        final int width = image.getWidth();
        final int height = image.getHeight();

        int targetWidth = width;
        int targetHeight = height;
        if (deg % 180 == 90) {
            targetHeight = width;
            targetWidth = height;
        }

        BufferedImage target = new BufferedImage(targetWidth, targetHeight, tankImageUp.getType());
        final Graphics2D graphicsSource = (Graphics2D) target.getGraphics();
        graphicsSource.rotate(Math.toRadians(deg), width / 2.0, height / 2.0);
        graphicsSource.drawImage(image, 0, 0, null);
        return target;
    }
}
