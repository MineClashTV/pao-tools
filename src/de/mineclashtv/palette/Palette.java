package de.mineclashtv.palette;

import de.mineclashtv.objects.AnarchyColor;

import java.awt.Color;
import java.util.List;

/**
 * This class contains the main palette and some commonly used functions.
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Palette {

    /**
     * main list of colors with their names, as of 10/24/20
     */
    private static final List<AnarchyColor> palette = List.of(
            new AnarchyColor("White", new Color(-1)),
            new AnarchyColor("Grey 1", new Color(-3422527)),
            new AnarchyColor("Grey 2", new Color(-7041140)),
            new AnarchyColor("Grey 3", new Color(-10922667)),
            new AnarchyColor("Grey 4", new Color(-14342871)),
            new AnarchyColor("Black", new Color(-16777216)),
            new AnarchyColor("Dark Red", new Color(-10548454)),
            new AnarchyColor("Red", new Color(-6745832)),
            new AnarchyColor("Light Red", new Color(-65536)),
            new AnarchyColor("Dark Orange", new Color(-41216)),
            new AnarchyColor("Orange", new Color(-28672)),
            new AnarchyColor("Light Orange", new Color(-14848)),
            new AnarchyColor("Yellow", new Color(-203)),
            new AnarchyColor("Light Green", new Color(-12321280)),
            new AnarchyColor("Green", new Color(-16723968)),
            new AnarchyColor("Dark Green", new Color(-15036390)),
            new AnarchyColor("Darker Green", new Color(-15639259)),
            new AnarchyColor("Teal", new Color(-16729210)),
            new AnarchyColor("Light Cyan", new Color(-11665453)),
            new AnarchyColor("Cyan", new Color(-16723201)),
            new AnarchyColor("Lighter Blue", new Color(-10907920)),
            new AnarchyColor("Light Blue", new Color(-14330881)),
            new AnarchyColor("Blue", new Color(-16772383)),
            new AnarchyColor("Dark Blue", new Color(-16773002)),
            new AnarchyColor("Dark Violet", new Color(-11403009)),
            new AnarchyColor("Violet", new Color(-7573823)),
            new AnarchyColor("Light Violet", new Color(-3822869)),
            new AnarchyColor("Lighter Violet", new Color(-1059078)),
            new AnarchyColor("Light Magenta", new Color(-1734690)),
            new AnarchyColor("Magenta", new Color(-1952798)),
            new AnarchyColor("Dark Magenta", new Color(-9893015)),
            new AnarchyColor("Saturated Brown", new Color(-6077184)),
            new AnarchyColor("Lighter Red", new Color(-35230)),     /* TODO whatever these colors are actually called */
            new AnarchyColor("Not quite white", new Color(-799289)),
            new AnarchyColor("Very light orange", new Color(-8266)),
            new AnarchyColor("Light Brown", new Color(-1789079)),
            new AnarchyColor("Brown", new Color(-5937608)),
            new AnarchyColor("Dark Brown", new Color(-10210273))
    );

    public static List<AnarchyColor> getPalette() {
        return palette;
    }

    public static boolean isColorInPalette(int rgb) {
        return getIndexInPalette(rgb) != -1;
    }

    public static int getIndexInPalette(int rgb) {
        for (int i = 0; i < palette.size(); i++) {
            if (palette.get(i).getColor().getRGB() == rgb) return i;
        }

        return -1; /* color not in palette */
    }

    public static AnarchyColor getAnarchyColor(Color color) {
        if(!isColorInPalette(color.getRGB())) return null;

        return palette.get(getIndexInPalette(color.getRGB()));
    }

}
