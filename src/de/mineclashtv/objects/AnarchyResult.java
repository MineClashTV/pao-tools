package de.mineclashtv.objects;

import java.awt.Color;

public class AnarchyResult {

    private AnarchyColor anarchyColor;
    private String colorHex, colorName;
    private int count;

    public AnarchyResult(int count, AnarchyColor color) {
        this.anarchyColor = color;
        Color col = anarchyColor.getColor(); /* don't get these in the wrong order... */

        this.colorHex = String.format("#%02X%02X%02X", col.getRed(), col.getGreen(), col.getBlue());
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
