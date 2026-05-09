package ru.vodobryshkin.splatting.gaussian;

import ru.vodobryshkin.ols.slae.DoubleMatrix;
import ru.vodobryshkin.ols.slae.Matrix;
import ru.vodobryshkin.splatting.function.Basis;
import ru.vodobryshkin.splatting.function.FunctionOfTwoVariables;

import java.util.ArrayList;
import java.util.List;

public class GaussianBasis implements Basis {
    private final int height;
    private final int width;
    private final List<FunctionOfTwoVariables> gaussians;

    public GaussianBasis(int height, int width, List<FunctionOfTwoVariables> gaussians) {
        this.height = height;
        this.width = width;
        this.gaussians = gaussians;
    }

    @Override
    public Matrix<Double> matrix() {
        int M = height * width;

        List<List<Double>> resultMatrix = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            List<Double> resultRow = new ArrayList<>();

            int x = i % width;
            int y = i / width;

            for (FunctionOfTwoVariables function: gaussians) {
                resultRow.add(function.value(x, y));
            }

            resultMatrix.add(resultRow);
        }

        return new DoubleMatrix(resultMatrix);
    }
}
