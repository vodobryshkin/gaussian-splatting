package ru.vodobryshkin.splatting.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PixelColoredImage implements ColoredImage {
    private final BufferedImage image;

    public PixelColoredImage(String filePath) {
        this(new File(filePath));
    }

    public PixelColoredImage(Path path) {
        this(path.toFile());
    }

    public PixelColoredImage(File imageFile) {
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }

    }

    @Override
    public List<List<Double>> list() {
        List<List<Double>> pixels = new ArrayList<>();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                double red = ((rgb >> 16) & 0xFF) / 255.0;
                double green = ((rgb >> 8) & 0xFF) / 255.0;
                double blue = (rgb & 0xFF) / 255.0;

                pixels.add(new ArrayList<>(List.of(red, green, blue)));
            }
        }

        return pixels;
    }

    @Override
    public int height() {
        return image.getHeight();
    }

    @Override
    public int width() {
        return image.getWidth();
    }
}
