package de.mineclashtv;

import java.awt.Color;

public class AnarchyColor {

    private Color color;
    private String name;

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
