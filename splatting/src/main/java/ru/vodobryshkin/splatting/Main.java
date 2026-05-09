package ru.vodobryshkin.splatting;

import ru.vodobryshkin.splatting.gaussian.GaussianGrid;
import ru.vodobryshkin.splatting.gaussian.Grid;
import ru.vodobryshkin.splatting.image.ColoredImage;
import ru.vodobryshkin.splatting.image.PixelColoredImage;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Path.of(args[0]);
        String outputPath = args[1];

        int gaussiansHeight = Integer.parseInt(args[2]);
        int gaussiansWidth = Integer.parseInt(args[3]);
        double sigma = Double.parseDouble(args[4]);

        ColoredImage image = new PixelColoredImage(inputPath);

        Grid grid = new GaussianGrid(
                gaussiansHeight,
                gaussiansWidth,
                sigma
        );

        GaussianSplattingApproximation approximation =
                new GaussianSplattingApproximation(image, grid);

        approximation.approx(outputPath);
    }
}