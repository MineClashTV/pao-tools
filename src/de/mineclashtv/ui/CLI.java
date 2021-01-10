package de.mineclashtv.ui;

import de.mineclashtv.objects.AnarchyColor;
import de.mineclashtv.palette.Palette;
import de.mineclashtv.tools.SharedUtils;
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
import java.util.Locale;
import java.util.Scanner;

// This is a mess.
public class CLI {

    private final Palettizer palettizer;
    private final Scaler scaler;
    private final Splitter splitter;
    private final Scanner scanner;

    private boolean predither;
    private int targetWidth;
    private File inputFile;
    private BufferedImage inputImage;
    private DitheringAlgorithm algorithm;

    public CLI() {
        this.palettizer = new Palettizer(Palette.getPalette());
        this.scaler = new Scaler(Scalr.Method.ULTRA_QUALITY);
        this.splitter = new Splitter();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        System.out.println("_".repeat(25));
        System.out.println(center("pao-tools", 25));
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
        this.algorithm = scanner.next().toLowerCase(Locale.ROOT).equals("y") ?
                DitheringAlgorithm.FLOYD_STEINBERG : DitheringAlgorithm.NONE;

        if(this.algorithm == DitheringAlgorithm.FLOYD_STEINBERG) {
            System.out.print("\nAlso generate a predither? (y/n): ");
            this.predither = scanner.next().equalsIgnoreCase("y");
        }

        final BufferedImage resultImage = palettizer.generateImage(
                scaler.scaleImage(inputImage, targetWidth == 0 ? inputImage.getWidth() : targetWidth), algorithm);
        final File resultFile = new File(
                inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + "-pao.png");

        ImageIO.write(resultImage, "png", resultFile);

        System.out.println("\nSaved image as " + resultFile.getName());

        if(this.predither) {
            final BufferedImage preditherImage = palettizer.generateImage(
                    scaler.scaleImage(inputImage, targetWidth == 0 ? inputImage.getWidth() : targetWidth), DitheringAlgorithm.NONE);
            final File preditherFile = new File(
                    inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + "-pre-pao.png");

            ImageIO.write(preditherImage, "png", preditherFile);

            System.out.println("Saved predither as " + preditherFile.getName());
        }

        System.out.print("\nSplit all colors into separate files? (y/n): ");
        if(scanner.next().toLowerCase(Locale.ROOT).equals("y")) {
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
    }

    private String center(String input, int targetWidth) {
        int margins = (targetWidth - input.length()) / 2;

        return " ".repeat(margins) + input + " ".repeat(margins);
    }
}
