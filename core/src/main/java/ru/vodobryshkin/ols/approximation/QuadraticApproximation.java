package ru.vodobryshkin.ols.approximation;

import ru.vodobryshkin.ols.function.MathematicalFunction;
import ru.vodobryshkin.ols.function.SecondDegreePolynomial;
import ru.vodobryshkin.ols.scalar.Scalar;
import ru.vodobryshkin.ols.slae.DefaultSystemOfLinearAlgebraicEquation;
import ru.vodobryshkin.ols.slae.DoubleMatrix;
import ru.vodobryshkin.ols.slae.Matrix;
import ru.vodobryshkin.ols.slae.SystemOfLinearAlgebraicEquation;

import java.util.ArrayList;
import java.util.List;

public class QuadraticApproximation implements Scalar {
    private final List<Double> xColumn;
    private final List<Double> yColumn;

    public QuadraticApproximation(List<Double> xColumn, List<Double> yColumn) {
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
        double s3x = s3x();
        double s4x = s4x();
        double sy = sy();
        double sxy = sxy();
        double sxxy = sxxy();
        double n = xColumn.size();

        Matrix<Double> matrix = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(s4x, s3x, sxx)),
                new ArrayList<>(List.of(s3x, sxx, sx)),
                new ArrayList<>(List.of(sxx, sx, n))
        )));
        List<Double> bVector = new ArrayList<>(List.of(sxxy, sxy, sy));

        SystemOfLinearAlgebraicEquation system = new DefaultSystemOfLinearAlgebraicEquation(
                matrix,
                bVector
        );

        List<Double> result = system.solutionVector();
        double a = result.getFirst();
        double b = result.get(1);
        double c = result.getLast();

        return new SecondDegreePolynomial(a, b, c);
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

    private double s3x() {
        double sum = 0;

        for (Double x: xColumn) {
            sum += x * x * x;
        }

        return sum;
    }

    private double s4x() {
        double sum = 0;

        for (Double x: xColumn) {
            sum += x * x * x * x;
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

    private double sxxy() {
        double sum = 0;
        int n = xColumn.size();

        for (int i = 0; i < n; i++) {
            double x = xColumn.get(i);
            sum += x * x * yColumn.get(i);
        }

        return sum;
    }
}
