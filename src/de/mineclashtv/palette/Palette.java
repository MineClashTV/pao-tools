package de.mineclashtv.palette;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.RGBColor;

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
            new AnarchyColor("White", new Color(0xffffff)),
            new AnarchyColor("Grey 4", new Color(0xcbc6c1)),
            new AnarchyColor("Grey 3", new Color(0x948f8c)),
            new AnarchyColor("Grey 2", new Color(0x595555)),
            new AnarchyColor("Grey 1", new Color(0x252529)),
            new AnarchyColor("Black", new Color(0x000000)),
            new AnarchyColor("Red 1", new Color(0x5f0b1a)),
            new AnarchyColor("Red 2", new Color(0x991118)),
            new AnarchyColor("Red 3", new Color(0xff0000)),
            new AnarchyColor("Orange 1", new Color(0xff5f00)),
            new AnarchyColor("Orange 2", new Color(0xff9000)),
            new AnarchyColor("Orange 3", new Color(0xffc600)),
            new AnarchyColor("Yellow", new Color(0xffff35)),
            new AnarchyColor("Green 4", new Color(0x43fe00)),
            new AnarchyColor("Green 3", new Color(0x00d000)),
            new AnarchyColor("Green 2", new Color(0x1a901a)),
            new AnarchyColor("Green 1", new Color(0x115d25)),
            new AnarchyColor("Teal", new Color(0x00bb86)),
            new AnarchyColor("Light Cyan", new Color(0x4dffd3)),
            new AnarchyColor("Cyan", new Color(0x00d2ff)),
            new AnarchyColor("Blue 4", new Color(0x598ef0)),
            new AnarchyColor("Blue 3", new Color(0x2553ff)),
            new AnarchyColor("Blue 2", new Color(0x0012e1)),
            new AnarchyColor("Blue 1", new Color(0x001076)),
            new AnarchyColor("Purple", new Color(0x5200ff)),
            new AnarchyColor("Lavender 1", new Color(0x8c6ec1)),
            new AnarchyColor("Lavender 2", new Color(0xc5aaeb)),
            new AnarchyColor("Lavender 3", new Color(0xefd6fa)),
            new AnarchyColor("Magenta 3", new Color(0xe587de)),
            new AnarchyColor("Magenta 2", new Color(0xe233e2)),
            new AnarchyColor("Magenta 1", new Color(0x690b69)),
            new AnarchyColor("Dark Orange", new Color(0xa34500)),
            new AnarchyColor("Red 4", new Color(0xff7662)),
            new AnarchyColor("Red 5", new Color(0xf3cdc7)),
            new AnarchyColor("Brown 4", new Color(0xffdfb6)),
            new AnarchyColor("Brown 3", new Color(0xe4b369)),
            new AnarchyColor("Brown 2", new Color(0xa56638)),
            new AnarchyColor("Brown 1", new Color(0x64341f))
    );

    public static List<AnarchyColor> getPalette() {
        return palette;
    }

    public static boolean isColorInPalette(int rgb) {
        return getIndexInPalette(rgb) != -1;
    }

    public static int getIndexInPalette(int rgb) {
        for (int i = 0; i < palette.size(); i++) {
            if (palette.get(i).getColor().getRGB() == rgb)
                return i;
        }

        return -1; /* color not in palette */
    }

    public static AnarchyColor getAnarchyColor(int color) {
        if(!isColorInPalette(color))
            return null;

        return palette.get(getIndexInPalette(color));
    }

    public static RGBColor[] getRGBColorArray() {
        RGBColor[] a = new RGBColor[palette.size()];

        for(int i = 0; i < a.length; i++)
            a[i] = new RGBColor(palette.get(i).getColor());

        return a;
    }

}
