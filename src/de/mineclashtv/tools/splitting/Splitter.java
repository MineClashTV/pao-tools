package de.mineclashtv.tools.splitting;

import de.mineclashtv.tools.SharedUtils;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Splitter {

    public Splitter() {

    }

    /**
     * Splits given image in every color with a transparent background.
     * @param image source image
     * @return ArrayList containing BufferedImages, one for each color
     */
    public List<BufferedImage> getSimpleSplit(BufferedImage image) {
        return getSplitWithBackground(image, null);
    }

    public List<BufferedImage> getSplitWithBackground(BufferedImage image, Color backgroundColor) {
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);
        ArrayList<BufferedImage> result = new ArrayList<>();
        int width = image.getWidth();
        int height = image.getHeight();

        for (Color color : colors) {
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int rgbColor = color.getRGB();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (backgroundColor != null) {
                        outputImage.setRGB(x, y, backgroundColor.getRGB());
                    }

                    int rgb = image.getRGB(x, y);
                    if (rgbColor == rgb) {
                        outputImage.setRGB(x, y, rgb);
                    }
                }
            }

            result.add(outputImage);
        }

        return result;
    }
}
