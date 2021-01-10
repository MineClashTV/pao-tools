package de.mineclashtv.tools.palettizing;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.objects.RGBColor;
import de.mineclashtv.palette.Palette;
import de.mineclashtv.tools.SharedUtils;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.util.List;

public class Palettizer {

    private final int[] palette;
    private final RGBColor[] rgbColorArray;
    private final IndexColorModel indexColorModel;

    /**
     * Creates a Palettizer object with given color palette.
     * @param palette Palette to use for this instance
     */
    public Palettizer(List<AnarchyColor> palette) {
        this.palette = convertToPalette(palette);
        this.rgbColorArray = Palette.getRGBColorArray();
        this.indexColorModel = new IndexColorModel(
                8,
                palette.size(),
                getColorChannel(this.palette, 16),
                getColorChannel(this.palette, 8),
                getColorChannel(this.palette, 0)
        );
    }

    /**
     * Takes an input image and converts it to the color palette of this Palettizer object.
     * Uses either a simple approximation or a Floyd-Steinberg dithering algorithm.
     *
     * @param input Input image to convert
     * @param algorithm {@code DitheringAlgorithm} to use. Valid values are {@code DitheringAlgorithm.NONE} and {@code DitheringAlgorithm.FLOYD_STEINBERG}.
     * @return Converted image using given algorithm
     * @see DitheringAlgorithm
     */
    public BufferedImage generateImage(BufferedImage input, DitheringAlgorithm algorithm) {
        int w = input.getWidth();
        int h = input.getHeight();
        final BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED, indexColorModel);

        switch(algorithm) {
            case NONE:
                result.setRGB(0, 0, w, h, SharedUtils.getImageData(input), 0, w);

                return result;
            case FLOYD_STEINBERG:
                // SOURCES:
                // https://gist.github.com/naikrovek/643a9799171d20820cb9
                // https://en.wikipedia.org/wiki/Floyd%E2%80%93Steinberg_dithering

                RGBColor[][] pixels = getRgbColorRaster(input);
                for(int y = 0; y < h; y++) {
                    for(int x = 0; x < w; x++) {
                        RGBColor oldColor = pixels[y][x];
                        RGBColor newColor = findClosestColor(oldColor);
                        RGBColor quantError = oldColor.sub(newColor);

                        result.setRGB(x, y, newColor.getRgbaInt());

                        // always check for bounds
                        if (x + 1 < w)
                            pixels[y    ][x + 1] = pixels[y    ][x + 1].add(quantError.mul(7. / 16));

                        if (x - 1 >= 0 && y + 1 < h)
                            pixels[y + 1][x - 1] = pixels[y + 1][x - 1].add(quantError.mul(3. / 16));

                        if (y + 1 < h)
                            pixels[y + 1][x    ] = pixels[y + 1][x    ].add(quantError.mul(5. / 16));

                        if (x + 1 < w && y + 1 < h)
                            pixels[y + 1][x + 1] = pixels[y + 1][x + 1].add(quantError.mul(1. / 16));
                    }
                }
                return result;
            default:
                return null;
        }
    }

    private RGBColor findClosestColor(RGBColor color) {
        RGBColor closest = rgbColorArray[0];

        for(RGBColor c : rgbColorArray) {
            if(c.diff(color) < closest.diff(color))
                closest = c;
        }

        return closest;
    }

    private RGBColor[][] getRgbColorRaster(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        RGBColor[][] a = new RGBColor[h][w];

        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                a[y][x] = new RGBColor(image.getRGB(x, y));
            }
        }

        return a;
    }

    /**
     * Converts a palette based on {@code AnarchyColor} objects to an array of integers.
     *
     * @param palette List of {@code AnarchyColor} objects
     * @return array of integers containing colors from the {@code AnarchyColor} list
     * @see AnarchyColor
     * @see Palette
     */
    private int[] convertToPalette(List<AnarchyColor> palette) {
        int[] a = new int[palette.size()];

        for(int i = 0; i < a.length; i++)
            a[i] = palette.get(i).getColor().getRGB();

        return a;
    }

    /**
     * Gets the color channels from a color palette based on integers.<br/>
     * This method uses bit shifting and may be used as follows:<br/>
     * <br/>
     * To get the alpha channel, use a {@code shift} value of 24.<br/>
     * To get the red color channel, use a {@code shift} value of 16.<br/>
     * To get the green color channel, use a {@code shift} value of 8.<br/>
     * To get the blue color channel, use a {@code shift} value of 0.<br/>
     *
     * @param palette Color palette to use
     * @param shift How many bits to shift
     * @return Colrs shifted by the specified number of bits
     */
    private byte[] getColorChannel(int[] palette, int shift) {
        byte[] a = new byte[palette.length];

        for(int i = 0; i < a.length; i++)
            a[i] = (byte) ((palette[i] >> shift) & 0xFF);

        return a;
    }

}
