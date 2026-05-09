package ru.vodobryshkin.splatting.image;

import function.FunctionOfTwoVariables;
import function.GaussianWithFixedSigma;

import java.util.ArrayList;
import java.util.List;

public class GaussianGrid implements Grid {
    private final int gaussianHeight;
    private final int gaussianWidth;
    private final double sigma;

    public GaussianGrid(int gaussianHeight, int gaussianWidth, double sigma) {
        this.gaussianHeight = gaussianHeight;
        this.gaussianWidth = gaussianWidth;
        this.sigma = sigma;
    }

    @Override
    public List<FunctionOfTwoVariables> functionRepresentation(int height, int width) {
        List<FunctionOfTwoVariables> gaussians = new ArrayList<>();

        for (int gy = 0; gy < gaussianHeight; gy++) {
            for (int gx = 0; gx < gaussianWidth; gx++) {
                double centerX = gaussianWidth == 1 ? (width - 1) / 2.0 : gx * (width - 1) / (double) (gaussianWidth - 1);

                double centerY = gaussianHeight == 1 ? (height - 1) / 2.0 : gy * (height - 1) / (double) (gaussianHeight - 1);

                gaussians.add(new GaussianWithFixedSigma(sigma, centerX, centerY));
            }
        }

        return gaussians;
    }
}