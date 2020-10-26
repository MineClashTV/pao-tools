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
public class Splitter {

    /* TODO split this up more (no pun intended). this function does too much */
    public static void splitColors(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < colors.size(); i++) {
            Color color = colors.get(i);
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

            File outputFile = new File("output-" + i + ".png");
            ImageIO.write(outputImage, "png", outputFile);

            int index = Palette.getIndexInPalette(rgbColor);
            if (index == -1) {
                String hex = String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
                System.out.printf("WARNING: Color %d not in palette: #%s. File saved as %s\n", i, hex, outputFile.getName());
            } else {
                String colorName = Palette.getPalette().get(index).getName();
                System.out.printf("Successfully exported color %d (%s) as %s\n", i, colorName, outputFile.getName());
            }
        }
    }
}
