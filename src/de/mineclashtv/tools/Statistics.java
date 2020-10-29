package de.mineclashtv.tools;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.AnarchyResult;
import de.mineclashtv.palette.Palette;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Statistics {

    public static ArrayList<AnarchyResult> getResults(BufferedImage image) {
        ArrayList<Color> colors = SharedUtils.getImagePalette(image);
        ArrayList<AnarchyResult> results = new ArrayList<>();

        for (Color col : colors) {
            int rgb = col.getRGB();
            AnarchyColor anarchyColor = Palette.isColorInPalette(rgb) ?
                    Palette.getAnarchyColor(rgb) : new AnarchyColor("Unknown color", col);

            AnarchyResult result = new AnarchyResult(
                    SharedUtils.getTotalColoredPixels(image, rgb),
                    anarchyColor
            );

            results.add(result);
        }

        return results;
    }

}
