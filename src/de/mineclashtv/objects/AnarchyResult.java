package de.mineclashtv.objects;

import de.mineclashtv.tools.SharedUtils;

import java.awt.Color;

public class AnarchyResult {

    private final AnarchyColor anarchyColor;
    private final String colorHex, colorName;
    private final int count;

    public AnarchyResult(int count, AnarchyColor color) {
        this.anarchyColor = color;
        Color col = anarchyColor.getColor(); /* don't get these in the wrong order... */

        this.colorHex = SharedUtils.getHexFromColor(col);
        this.colorName = anarchyColor.getName();
        this.count = count;
    }

    public AnarchyColor getAnarchyColor() {
        return anarchyColor;
    }

    public int getCount() {
        return count;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getColorName() {
        return colorName;
    }

    @Override
    public String toString() {
        return "AnarchyResult{" +
                "anarchyColor=" + anarchyColor +
                ", colorHex='" + colorHex + '\'' +
                ", colorName='" + colorName + '\'' +
                ", count=" + count +
                '}';
    }
}
