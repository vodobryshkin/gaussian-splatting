package ru.vodobryshkin.splatting;

import ru.vodobryshkin.ols.slae.DefaultSystemOfLinearAlgebraicEquation;
import ru.vodobryshkin.ols.slae.DoubleMatrix;
import ru.vodobryshkin.ols.slae.Matrix;
import ru.vodobryshkin.splatting.function.FunctionOfTwoVariables;
import ru.vodobryshkin.splatting.gaussian.GaussianBasis;
import ru.vodobryshkin.splatting.gaussian.Grid;
import ru.vodobryshkin.splatting.image.ColoredImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GaussianSplattingApproximation {
    private final ColoredImage image;
    private final Grid grid;

    public GaussianSplattingApproximation(ColoredImage image, Grid grid) {
        this.image = image;
        this.grid = grid;
    }

    public void approx(String outputPath) {
        List<FunctionOfTwoVariables> gaussians = grid.functionRepresentation(image.height(), image.width());

        Matrix<Double> Phi = new GaussianBasis(image.height(), image.width(), gaussians).matrix();
        Matrix<Double> Y = new DoubleMatrix(image.list());

        Matrix<Double> A = A(Phi);
        Matrix<Double> B = B(Phi, Y);

        List<Double> redColumn = B.column(0);
        List<Double> greenColumn = B.column(1);
        List<Double> blueColumn = B.column(2);

        List<Double> redSolution =
                new DefaultSystemOfLinearAlgebraicEquation(A(Phi), redColumn)
                        .solutionVector();

        List<Double> greenSolution =
                new DefaultSystemOfLinearAlgebraicEquation(A(Phi), greenColumn)
                        .solutionVector();

        List<Double> blueSolution =
                new DefaultSystemOfLinearAlgebraicEquation(A(Phi), blueColumn)
                        .solutionVector();

        BufferedImage newImage = reconstructImage(image.width(), image.height(), gaussians, redSolution, greenSolution, blueSolution);

        try {
            ImageIO.write(newImage, "png", new File(outputPath));
        } catch (IOException exception) {
            throw new RuntimeException("Cannot save image: " + outputPath, exception);
        }
    }

    private Matrix<Double> A(Matrix<Double> Phi) {
        return Phi.transposedMatrix().multiply(Phi);
    }

    private Matrix<Double> B(Matrix<Double> Phi, Matrix<Double> Y) {
        return Phi.transposedMatrix().multiply(Y);
    }

    BufferedImage reconstructImage(int width,
                                   int height,
                                   List<FunctionOfTwoVariables> gaussians,
                                   List<Double> redCoeffs,
                                   List<Double> greenCoeffs,
                                   List<Double> blueCoeffs) {

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                double r = 0, g = 0, b = 0;

                for (int k = 0; k < gaussians.size(); k++) {
                    double value = gaussians.get(k).value(x, y);
                    r += redCoeffs.get(k) * value;
                    g += greenCoeffs.get(k) * value;
                    b += blueCoeffs.get(k) * value;
                }

                int red = (int) Math.clamp(r * 255, 0, 255);
                int green = (int) Math.clamp(g * 255, 0, 255);
                int blue = (int) Math.clamp(b * 255, 0, 255);

                int rgb = (red << 16) | (green << 8) | blue;

                result.setRGB(x, y, rgb);
            }
        }
        return result;
    }
}
