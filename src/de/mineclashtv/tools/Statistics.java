package de.mineclashtv.tools;

import de.mineclashtv.palette.Palette;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Statistics {

    /* TODO remove printf calls, let the main class handle output */
    public static void getImageStats(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);

        System.out.printf("Found %d colors in %s:\n", colors.size(), file.getName());
        for (int i = 0; i < colors.size(); i++) {
            Color color = colors.get(i);
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            int rgb = color.getRGB();

            System.out.printf("%d: %s, RGB(%d, %d, %d), %d pixels total, color in PAO palette: %s\n",
                    i, String.format("#%02X%02X%02X", r, g, b), r, g, b, SharedUtils.getTotalColoredPixels(image, rgb),
                    Palette.isColorInPalette(rgb) ? Palette.getPalette().get(Palette.getIndexInPalette(rgb)).getName() : "not in palette"
            );
        }
    }

}
