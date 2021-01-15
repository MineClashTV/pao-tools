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

        // Skip processing if no scaling is needed.
        if(input.getWidth() == width)
            return input;

        BufferedImage result = Scalr.resize(input, scalingMethod, width);

        input.flush();

        return result;
    }

}
