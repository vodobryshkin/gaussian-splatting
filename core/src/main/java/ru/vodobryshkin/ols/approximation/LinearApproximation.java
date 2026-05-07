package ru.vodobryshkin.ols.approximation;

import ru.vodobryshkin.ols.function.LinearFunction;
import ru.vodobryshkin.ols.function.MathematicalFunction;
import ru.vodobryshkin.ols.scalar.Scalar;
import ru.vodobryshkin.ols.slae.DefaultSystemOfLinearAlgebraicEquation;
import ru.vodobryshkin.ols.slae.DoubleMatrix;
import ru.vodobryshkin.ols.slae.Matrix;
import ru.vodobryshkin.ols.slae.SystemOfLinearAlgebraicEquation;

import java.util.ArrayList;
import java.util.List;

public class LinearApproximation implements Scalar {
    private final List<Double> xColumn;
    private final List<Double> yColumn;

    public LinearApproximation(List<Double> xColumn, List<Double> yColumn) {
        if (xColumn.size() != yColumn.size()) {
            throw new IllegalArgumentException("xColumn and yColumn must have the same size.");
        }

        this.xColumn = xColumn;
        this.yColumn = yColumn;
    }

    @Override
    public MathematicalFunction value() {
        double sx = sx();
        double sxx = sxx();
        double sy = sy();
        double sxy = sxy();
        double n = xColumn.size();

        Matrix<Double> matrix = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(sxx, sx)),
                new ArrayList<>(List.of(sx, n))
        )));
        List<Double> bVector = new ArrayList<>(List.of(sxy, sy));

        SystemOfLinearAlgebraicEquation system = new DefaultSystemOfLinearAlgebraicEquation(
                matrix,
                bVector
        );

        List<Double> result = system.solutionVector();
        double a = result.getFirst();
        double b = result.getLast();

        return new LinearFunction(a, b);
    }

    private double sx() {
        double sum = 0;

        for (Double x: xColumn) {
            sum += x;
        }

        return sum;
    }

    private double sxx() {
        double sum = 0;

        for (Double x: xColumn) {
            sum += x * x;
        }

        return sum;
    }

    private double sy() {
        double sum = 0;

        for (Double y: yColumn) {
            sum += y;
        }

        return sum;
    }

    private double sxy() {
        double sum = 0;
        int n = xColumn.size();

        for (int i = 0; i < n; i++) {
            sum += xColumn.get(i) * yColumn.get(i);
        }

        return sum;
    }
}
