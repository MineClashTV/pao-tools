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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Main {

    /*
    TODO add some kind of interface
    TODO better splitting algorithms
    TODO better efficiency; less iterations over the image
     */

    public static void main(String[] args) throws IOException {
        File file = new File("Ancient Enemy.png"); // put path to png here
        Files.createDirectory(Paths.get(file.getName().replace(".png", "")));

        ArrayList<AnarchyResult> stats = Statistics.getResults(ImageIO.read(file));
        for (AnarchyResult result : stats) {
            System.out.printf("%dx %s (%s)\n", result.getCount(), result.getColorName(), result.getColorHex());
        }

        ArrayList<BufferedImage> splits = Splitter.getSimpleSplit(ImageIO.read(file));
        for (BufferedImage split : splits) {
            Color color = SharedUtils.getFirstColor(split);
            int rgb = Objects.requireNonNull(color).getRGB();
            AnarchyColor anarchyColor = Palette.isColorInPalette(rgb) ?
                    Palette.getAnarchyColor(rgb) : new AnarchyColor("Unknown color", color);
            File imageFile = new File(file.getName().replace(".png", "") + "/" + anarchyColor.getName() + " - " + SharedUtils.getTotalColoredPixels(split, rgb) + ".png");

            ImageIO.write(split, "png", imageFile);

            System.out.printf("Saved color %s (%s) as %s\n", anarchyColor.getName(), SharedUtils.getHexFromColor(color), imageFile.getName());
        }
    }

}