package de.mineclashtv.palette;

import java.awt.Color;

/**
 * This was used to convert hex colors (#FFFFFF) into single RGB integers
 */
public class PaletteConverter {

    public static String convert(String colors) {
        StringBuilder builder = new StringBuilder();
        String[] lines = colors.split("\n");

        for(String line : lines) {
            int rgb = Color.decode(line).getRGB();
            builder.append(rgb).append("\n");
        }

        return builder.toString();
    }
}
