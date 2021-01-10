package de.mineclashtv.tools.scaling;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

public class Scaler {

    private final Scalr.Method scalingMethod;

    public Scaler(Scalr.Method scalingMethod) {
        this.scalingMethod = scalingMethod;
    }

    /**
     * Scales an image down to a target width using the scaling method given in the constructor.
     * The height will be automatically adjusted, maintaining its original proportions.
     *
     * @param input Input image
     * @param width Target width
     * @return Scaled image
     * @throws IllegalArgumentException if the input image width is smaller than the target width
     */
    public BufferedImage scaleImage(BufferedImage input, int width) throws IllegalArgumentException {
        if(input.getWidth() < width)
            throw new IllegalArgumentException(String.format(
                    "Input image width smaller than target width: %d < %d",
                    input.getWidth(),
                    width
            ));

        BufferedImage result = Scalr.resize(input, scalingMethod, width);

        input.flush();

        return result;
    }

    /**
     * Scales an image down to a target width and height using the scaling method given in the constructor.
     *
     * @param input Input image
     * @param width Target width
     * @param height Target height
     * @return Scaled image
     * @throws IllegalArgumentException if the input image dimensions are smaller than the target dimensions
     */
    public BufferedImage scaleImage(BufferedImage input, int width, int height) throws IllegalArgumentException {
        if(input.getWidth() < width || input.getHeight() < height)
            throw new IllegalArgumentException(String.format(
                    "Input image dimensions smaller than target dimensions: %dx%d < %dx%d",
                    input.getWidth(),
                    input.getHeight(),
                    width,
                    height
            ));

        BufferedImage result = Scalr.resize(input, scalingMethod, width, height);

        input.flush();

        return result;
    }
}
