package de.mineclashtv.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Splitter {

    /**
     * Splits given image in every color with a transparent background.
     * @param image source image
     * @return ArrayList containing BufferedImages, one for each color
     */
    public static ArrayList<BufferedImage> getSimpleSplit(BufferedImage image) {
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);
        ArrayList<BufferedImage> result = new ArrayList<>();
        int width = image.getWidth();
        int height = image.getHeight();

        for (Color color : colors) {
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int rgbColor = color.getRGB();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
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
