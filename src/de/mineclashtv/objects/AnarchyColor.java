package de.mineclashtv.objects;

import java.awt.Color;

public class AnarchyColor {

    private final Color color;
    private final String name;

    public AnarchyColor(String name, Color color) {
       this.color = color;
       this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AnarchyColor{" +
                "color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
