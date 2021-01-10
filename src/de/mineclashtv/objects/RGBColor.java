package de.mineclashtv.objects;

import java.awt.Color;

public class RGBColor {

    private final int r, g, b;

    public RGBColor(int c) {
        this.r = (c >> 16) & 0xFF;
        this.g = (c >> 8) & 0xFF;
        this.b = (c) & 0xFF;
    }

    public RGBColor(Color c) {
        this.r = c.getRed();
        this.g = c.getGreen();
        this.b = c.getBlue();
    }

    public RGBColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGBColor add(RGBColor o) {
        return new RGBColor(r + o.r, g + o.g, b + o.b);
    }

    public RGBColor mul(double d) {
        return new RGBColor((int) (d * r), (int) (d * g), (int) (d * b));
    }

    public RGBColor sub(RGBColor o) {
        return new RGBColor(r - o.r, g - o.g, b - o.b);
    }

    public int diff(RGBColor o) {
        int rDiff = o.r - r;
        int gDiff = o.g - g;
        int bDiff = o.b - b;
        return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;
    }


    public int getRgbaInt() {
        return ((0xFF) << 24)     | // alpha = 255
               ((r & 0xFF) << 16) | // red
               ((g & 0xFF) << 8)  | // green
               ((b & 0xFF));        // blue
    }
}
