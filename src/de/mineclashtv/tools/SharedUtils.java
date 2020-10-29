package de.mineclashtv.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class SharedUtils {

    /**
     * @param image source image
     * @param rgb RGB value representing color in default sRGB color model
     * @return the amount of pixels in given image that are of the exact rgb value
     */
    public static int getTotalColoredPixels(BufferedImage image, int rgb) {
        int answer = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == rgb)
                    answer++;
            }
        }

        return answer;
    }

    public static ArrayList<Color> getImagePalette(BufferedImage image) {
        ArrayList<Color> result = new ArrayList<>();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));

                if (!result.contains(color))
                    result.add(color);
            }
        }

        return result;
    }

    /**
     * @param image source image
     * @return whatever color of the first non-transparent pixel, null if there is none
     */
    public static Color getFirstColor(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                if((rgb >> 24) != 0x00) /* checking for non-transparency */
                    return new Color(rgb);
            }
        }

        return null;
    }

    public static String getHexFromColor(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
