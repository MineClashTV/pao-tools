package de.mineclashtv.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class SharedUtils {

    public static int[] getImageData(BufferedImage image) {
        return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
    }

    /**
     * @param image source image
     * @param rgb RGB value representing color in default sRGB color model
     * @return the amount of pixels in given image that are of the exact rgb value
     */
    public static int getTotalColoredPixels(BufferedImage image, int rgb) {
        int[] data = getImageData(image);
        int answer = 0;

        for(int a : data) {
            if(rgb == a) {
                answer++;
            }
        }

        return answer;
    }

    public static ArrayList<Color> getImagePalette(BufferedImage image) {
        int[] data = getImageData(image);
        ArrayList<Color> answer = new ArrayList<>();

        for(int a : data) {
            Color color = new Color(a);

            if(!answer.contains(color)) {
                answer.add(color);
            }
        }

        return answer;
    }

    /**
     * @param image source image
     * @return whatever color of the first non-transparent pixel, null if there is none
     */
    public static Color getFirstColor(BufferedImage image) {
        int[] data = getImageData(image);

        for(int a : data) {
            if((a >> 24) != 0x00) {
                return new Color(a);
            }
        }

        return null;
    }

    public static String getHexFromColor(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
