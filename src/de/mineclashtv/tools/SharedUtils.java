package de.mineclashtv.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Set of commonly used static functions.
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class SharedUtils {

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
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Color> result = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                if (!result.contains(color))
                    result.add(color);
            }
        }

        return result;
    }
}
