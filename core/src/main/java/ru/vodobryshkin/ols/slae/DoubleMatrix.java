package ru.vodobryshkin.ols.slae;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class DoubleMatrix implements Matrix<Double> {
    private static final double EPSILON = 1e-9;

    private final List<List<Double>> matrix;
    private final int n;

    public DoubleMatrix(List<List<Double>> matrix) {
        n = matrix.size();

        for (List<Double> row : matrix) {
            if (row.size() != n) {
                throw new IllegalArgumentException("Matrix rows must have the same length.");
            }
        }

        this.matrix = matrix;
    }

    @Override
    public int indexOfMaxInAColumn(int columnNumber) {
        double rowMax = -1;
        int rowMaxNumber = columnNumber;

        for (int j = columnNumber; j < n; j++) {
            if (Math.abs(matrix.get(j).get(columnNumber)) > rowMax) {
                rowMax = Math.abs(matrix.get(j).get(columnNumber));
                rowMaxNumber = j;
            }
        }

        if (rowMax < EPSILON) {
            throw new ArithmeticException("The matrix is singular.");
        }

        return rowMaxNumber;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Double diagonalElement(int i) {
        return matrix.get(i).get(i);
    }

    @Override
    public DoubleMatrix subtractTwoRows(int i, int j) {
        // ИСПРАВЛЕНО: вынесли множитель до цикла, чтобы он не мутировал в процессе
        double factor = matrix.get(i).get(j);

        for (int k = 0; k < n; k++) {
            matrix.get(i).set(k, matrix.get(i).get(k) - factor * matrix.get(j).get(k));
        }

        return new DoubleMatrix(matrix);
    }

    @Override
    public DoubleMatrix swapTwoRows(int i, int j) {
        for (int k = 0; k < n; k++) {
            double temp = matrix.get(i).get(k);
            matrix.get(i).set(k, matrix.get(j).get(k));
            matrix.get(j).set(k, temp);
        }

        return new DoubleMatrix(matrix);
    }

    @Override
    public DoubleMatrix divideRowByDiagonalElement(int i) {
        double diagonal = matrix.get(i).get(i);

        if (Math.abs(diagonal) < EPSILON) {
            throw new ArithmeticException("Division by zero.");
        }

        for (int j = 0; j < n; j++) {
            double elem = matrix.get(i).get(j);
            matrix.get(i).set(j, elem / diagonal);
        }

        return new DoubleMatrix(matrix);
    }

    @Override
    public Double sumOfRow(int i, List<Double> solutionList) {
        double sum = 0;
        List<Double> row = matrix.get(i);

        for (int j = solutionList.size() - 1, rowI = matrix.size() - 1; j >= 0; j--, rowI--) {
            sum += solutionList.get(j) * row.get(rowI);
        }

        return sum;
    }
}