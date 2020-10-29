package de.mineclashtv;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.AnarchyResult;
import de.mineclashtv.palette.Palette;
import de.mineclashtv.tools.SharedUtils;
import de.mineclashtv.tools.Splitter;
import de.mineclashtv.tools.Statistics;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("sampleinput.png");

        ArrayList<AnarchyResult> stats = Statistics.getResults(ImageIO.read(file));
        for (AnarchyResult result : stats) {
            System.out.printf("%dx %s (%s)\n", result.getCount(), result.getColorName(), result.getColorHex());
        }

        ArrayList<BufferedImage> splits = Splitter.getSimpleSplit(ImageIO.read(file));
        for (BufferedImage split : splits) {
            /* Color may be null when image only consists of fully transparent pixels. I don't care though. */
            Color color = SharedUtils.getFirstColor(split);
            AnarchyColor anarchyColor = Palette.isColorInPalette(Objects.requireNonNull(color).getRGB()) ?
                    Palette.getAnarchyColor(color.getRGB()) : new AnarchyColor("Unknown color", color);
            File imageFile = new File(anarchyColor.getName() + ".png");

            ImageIO.write(split, "png", imageFile);

            System.out.printf("Saved color %s (%s) as %s\n", anarchyColor.getName(), SharedUtils.getHexFromColor(color), imageFile.getName());
        }
    }

}