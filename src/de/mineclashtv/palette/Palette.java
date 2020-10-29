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
            new AnarchyColor("Grey 4", new Color(-3422527)),
            new AnarchyColor("Grey 3", new Color(-7041140)),
            new AnarchyColor("Grey 2", new Color(-10922667)),
            new AnarchyColor("Grey 1", new Color(-14342871)),
            new AnarchyColor("Black", new Color(-16777216)),
            new AnarchyColor("Red 1", new Color(-10548454)),
            new AnarchyColor("Red 2", new Color(-6745832)),
            new AnarchyColor("Red 3", new Color(-65536)),
            new AnarchyColor("Dark Orange", new Color(-41216)),
            new AnarchyColor("Orange", new Color(-28672)),
            new AnarchyColor("Light Orange", new Color(-14848)),
            new AnarchyColor("Yellow", new Color(-203)),
            new AnarchyColor("Green 4", new Color(-12321280)),
            new AnarchyColor("Green 3", new Color(-16723968)),
            new AnarchyColor("Green 2", new Color(-15036390)),
            new AnarchyColor("Green 1", new Color(-15639259)),
            new AnarchyColor("Teal", new Color(-16729210)),
            new AnarchyColor("Light Cyan", new Color(-11665453)),
            new AnarchyColor("Cyan", new Color(-16723201)),
            new AnarchyColor("Blue 4", new Color(-10907920)),
            new AnarchyColor("Blue 3", new Color(-14330881)),
            new AnarchyColor("Blue 2", new Color(-16772383)),
            new AnarchyColor("Blue 1", new Color(-16773002)),
            new AnarchyColor("Purple", new Color(-11403009)),
            new AnarchyColor("Lavender 1", new Color(-7573823)),
            new AnarchyColor("Lavender 2", new Color(-3822869)),
            new AnarchyColor("Lavender 3", new Color(-1059078)),
            new AnarchyColor("Magenta 3", new Color(-1734690)),
            new AnarchyColor("Magenta 2", new Color(-1952798)),
            new AnarchyColor("Magenta 1", new Color(-9893015)),
            new AnarchyColor("Dark Orange", new Color(-6077184)),
            new AnarchyColor("Red 4", new Color(-35230)),
            new AnarchyColor("Red 5", new Color(-799289)),
            new AnarchyColor("Brown 4", new Color(-8266)),
            new AnarchyColor("Brown 3", new Color(-1789079)),
            new AnarchyColor("Brown 2", new Color(-5937608)),
            new AnarchyColor("Brown 1", new Color(-10210273))
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

    public static AnarchyColor getAnarchyColor(int color) {
        if(!isColorInPalette(color)) return null;

        return palette.get(getIndexInPalette(color));
    }

}
