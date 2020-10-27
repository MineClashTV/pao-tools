package de.mineclashtv.tools;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.AnarchyResult;
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

    public static ArrayList<AnarchyResult> getResults(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);
        ArrayList<AnarchyResult> results = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Color col = colors.get(i);
            AnarchyColor anarchyColor = Palette.isColorInPalette(col.getRGB()) ?
                     Palette.getAnarchyColor(col) : new AnarchyColor("Unknown color", col);

            AnarchyResult result = new AnarchyResult(
                    SharedUtils.getTotalColoredPixels(image, col.getRGB()),
                    anarchyColor
            );

            results.add(result);
        }

        return results;
    }

}
