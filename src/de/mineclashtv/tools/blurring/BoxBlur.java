package de.mineclashtv.tools.blurring;

import de.mineclashtv.tools.SharedUtils;

import java.awt.image.BufferedImage;

public class BoxBlur {

    private final int hRadius;
    private final int vRadius;
    private final int iterations;

    public BoxBlur(int radius, int iterations) {
        this.hRadius = radius;
        this.vRadius = radius;
        this.iterations = iterations;
    }

    /**
     * Blurs a {@code BufferedImage} using a box blur algorithm with the radius and iteration values of this instance.
     * <br/>
     * This implementation uses two 1-dimensional blurring passes for best performance.
     *
     * @param input Image to blur
     * @return Blurred image using a box blur algorithm
     */
    public BufferedImage blurImage(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();
        int[] inPixels = SharedUtils.getImageData(input);
        int[] outPixels = new int[inPixels.length];
        final BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0; i < iterations; i++) {
            blur(inPixels, outPixels, w, h, hRadius);
            blur(outPixels, inPixels, h, w, vRadius);
        }

        result.setRGB(0, 0, w, h, inPixels, 0, w);

        return result;
    }

    public void blur(int[] in, int[] out, int w, int h, int radius) {
        int tableSize = 2 * radius + 1;
        int[] divide = new int[256*tableSize];

        for(int i = 0; i < 256*tableSize; i++) {
            divide[i] = i / tableSize;
        }

        int inIndex = 0;

        for(int y = 0; y < h; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;

            for(int i = -radius; i <= radius; i++) {
                int rgb = in[inIndex + clamp(i, 0, w - 1)];

                ta += (rgb >> 24) & 0xFF;
                tr += (rgb >> 16) & 0xFF;
                tg += (rgb >> 8) & 0xFF;
                tb += rgb & 0xFF;
            }

            for(int x = 0; x < w; x++) {
                out[outIndex] =
                        (divide[ta] << 24) |
                        (divide[tr] << 16) |
                        (divide[tg] << 8) |
                        divide[tb];

                int i1 = x + radius + 1;
                if(i1 > w - 1)
                    i1 = w - 1;
                int i2 = x - radius;
                if(i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xFF)-((rgb2 >> 24) & 0xFF);
                tr += ((rgb1 & 0xFF0000)-(rgb2 & 0xFF0000)) >> 16;
                tg += ((rgb1 & 0xFF00)-(rgb2 & 0xFF00)) >> 8;
                tb += (rgb1 & 0xFF)-(rgb2 & 0xFF);
                outIndex += h;
            }

            inIndex += w;
        }
    }

    private int clamp(int x, int a, int b) {
        return (x < a) ? a : Math.min(x, b);
    }
}
