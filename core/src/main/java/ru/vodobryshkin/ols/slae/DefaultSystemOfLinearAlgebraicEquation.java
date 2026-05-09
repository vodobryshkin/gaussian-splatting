package ru.vodobryshkin.ols.slae;

import java.util.ArrayList;
import java.util.List;

public class DefaultSystemOfLinearAlgebraicEquation implements SystemOfLinearAlgebraicEquation {
    private Matrix<Double> matrix;
    private final List<Double> bColumn;

    public DefaultSystemOfLinearAlgebraicEquation(Matrix<Double> matrix, List<Double> bColumn) {
        if (matrix.columnsSize() != matrix.rowsSize()) {
            throw new IllegalArgumentException("For calculating SLAE, matrix's raws and columns must have the same size.");
        }

        int n = matrix.columnsSize();

        if (n != bColumn.size()) {
            throw new IllegalArgumentException("Matrix raw's length must be the same size as b-vector length.");
        }

        this.matrix = matrix;
        this.bColumn = new ArrayList<>(bColumn);
    }

    @Override
    public List<Double> solutionVector() {
        int n = matrix.columnsSize();

        for (int i = 0; i < n; i++) {
            int rowMaxNumber = matrix.indexOfMaxInAColumn(i);

            if (rowMaxNumber != i) {
                matrix = matrix.swapTwoRows(i, rowMaxNumber);
                double temp = bColumn.get(i);
                bColumn.set(i, bColumn.get(rowMaxNumber));
                bColumn.set(rowMaxNumber, temp);
            }

            double oldDiagonalElement = matrix.diagonalElement(i);
            matrix = matrix.divideRowByDiagonalElement(i);
            bColumn.set(i, bColumn.get(i) / oldDiagonalElement);

            for (int j = i + 1; j < n; j++) {
                matrix = matrix.swapTwoRows(i, j);

                double factor = matrix.diagonalElement(i);
                matrix = matrix.swapTwoRows(i, j);

                bColumn.set(j, bColumn.get(j) - factor * bColumn.get(i));
                matrix = matrix.subtractTwoRows(j, i);
            }
        }

        List<Double> solutionVector = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            double b = bColumn.get(i);
            double sum = matrix.sumOfRow(i, solutionVector);
            solutionVector.addFirst(b - sum);
        }

        return solutionVector;
    }
}