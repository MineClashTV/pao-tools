package de.mineclashtv.ui;

import de.mineclashtv.Main;
import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.palette.Palette;
import de.mineclashtv.tools.SharedUtils;
import de.mineclashtv.tools.blurring.BoxBlur;
import de.mineclashtv.tools.palettizing.DitheringAlgorithm;
import de.mineclashtv.tools.palettizing.Palettizer;
import de.mineclashtv.tools.scaling.Scaler;
import de.mineclashtv.tools.splitting.Splitter;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// This is a mess.
public class CLI {

    /*
    The complete process:
    1. Scale the image down to a target width.
    2. Convert it to the PAO palette and apply floyd-steinberg dithering
    3. Use the same scaled image from step 1 and blur it, 2px radius 1 iteration
    4. Convert the blurred image to the PAO palette without applying dithering
    5. Optionally split all colors from the dithered image to separate files
     */

    private final Palettizer palettizer;
    private final Scaler scaler;
    private final Splitter splitter;
    private final Scanner scanner;

    private boolean predither;
    private int targetWidth;
    private File inputFile;
    private BufferedImage inputImage;
    private DitheringAlgorithm algorithm;
    private BoxBlur blur;

    public CLI() {
        this.palettizer = new Palettizer(Palette.getPalette());
        this.scaler = new Scaler(Scalr.Method.ULTRA_QUALITY);
        this.splitter = new Splitter();
        this.scanner = new Scanner(System.in);
        this.blur = new BoxBlur(2, 1);
    }

    public void run() throws IOException {
        System.out.println("_".repeat(25));
        System.out.println(center("pao-tools " + Main.version, 25));
        System.out.println("_".repeat(25));
        System.out.print("Path to image: ");

        this.inputFile = new File(scanner.nextLine());
        this.inputImage = ImageIO.read(inputFile);

        if(this.inputImage == null) {
            System.err.println("Not an image: " + inputFile.getAbsolutePath());

            return;
        }

        System.out.print("\nTarget width (enter 0 to keep original res): ");
        this.targetWidth = scanner.nextInt();

        if(this.inputImage.getWidth() < targetWidth) {
            System.err.printf("Image width is smaller than target width: %d < %d\n",
                    this.inputImage.getWidth(), targetWidth);

            return;
        }

        System.out.print("\nUse dithering? (y/n): ");
        this.algorithm = scanner.next().equalsIgnoreCase("y") ?
                DitheringAlgorithm.FLOYD_STEINBERG : DitheringAlgorithm.NONE;

        if(this.algorithm == DitheringAlgorithm.FLOYD_STEINBERG) {
            System.out.print("\nAlso generate a predither? (y/n): ");
            this.predither = scanner.next().equalsIgnoreCase("y");
        }

        long dither_timeA = System.currentTimeMillis();
        final BufferedImage resultImage = palettizer.generateImage(
                scaler.scaleImage(inputImage, targetWidth == 0 ? inputImage.getWidth() : targetWidth), algorithm);
        final File resultFile = new File(
                inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + "-pao.png");

        ImageIO.write(resultImage, "png", resultFile);

        System.out.printf(
                "\nSaved image as %s in %dms\n",
                resultFile.getName(),
                (int)(System.currentTimeMillis() - dither_timeA)
        );

        if(this.predither) {
            long predither_timeA = System.currentTimeMillis();
            final BufferedImage preditherImage =
                    palettizer.generateImage(
                            blur.blurImage(
                                    scaler.scaleImage(
                                            inputImage, targetWidth == 0 ? inputImage.getWidth() : targetWidth)
                            ), DitheringAlgorithm.NONE);
            final File preditherFile = new File(
                    inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + "-pre-pao.png");

            ImageIO.write(preditherImage, "png", preditherFile);

            System.out.printf(
                    "Saved predither as %s in %dms\n",
                    preditherFile.getName(),
                    (int)(System.currentTimeMillis() - predither_timeA)
            );
        }

        System.out.print("\nSplit all colors into separate files? (y/n): ");
        if(scanner.next().equalsIgnoreCase("y")) {
            splitter.getSimpleSplit(resultImage).forEach(split -> {
                Color color = SharedUtils.getFirstColor(split);
                int rgb = color.getRGB();
                AnarchyColor anarchyColor = Palette.isColorInPalette(rgb) ?
                        Palette.getAnarchyColor(rgb) : new AnarchyColor("Unknown color", color);

                File imageFile = new File(
                        String.format("%s - %d.png",
                                anarchyColor.getName(),
                                SharedUtils.getTotalColoredPixels(split, rgb)
                        )
                );

                try {
                    ImageIO.write(split, "png", imageFile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                System.out.printf("Saved color %s (%s) as %s\n",
                        anarchyColor.getName(),
                        SharedUtils.getHexFromColor(color),
                        imageFile.getName()
                );
            });
        }

        System.out.printf(
                "\nDone.\nFinal image: %dx%d pixels (%d total)\n",
                resultImage.getWidth(),
                resultImage.getHeight(),
                resultImage.getWidth() * resultImage.getHeight()
        );
    }

    private String center(String input, int targetWidth) {
        int margins = (targetWidth - input.length()) / 2;

        return " ".repeat(margins) + input + " ".repeat(margins);
    }
}
