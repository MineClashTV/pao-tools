package de.mineclashtv.tools.stats;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.AnarchyResult;
import de.mineclashtv.palette.Palette;
import de.mineclashtv.tools.SharedUtils;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Statistics {

    public Statistics() {

    }

    public List<AnarchyResult> getResults(BufferedImage image) {
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
