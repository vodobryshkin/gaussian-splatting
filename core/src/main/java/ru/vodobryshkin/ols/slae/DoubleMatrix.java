package ru.vodobryshkin.ols.slae;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class DoubleMatrix implements Matrix<Double> {
    private static final double EPSILON = 1e-9;

    private final List<List<Double>> matrix;
    private final int n;

    public DoubleMatrix(List<List<Double>> matrix) {
        n = matrix.size();

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
    public int rowsSize() {
        return n;
    }

    @Override
    public int columnsSize() {
        return matrix.getFirst().size();
    }

    @Override
    public Double diagonalElement(int i) {
        return matrix.get(i).get(i);
    }

    @Override
    public DoubleMatrix subtractTwoRows(int i, int j) {
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

    @Override
    public List<Double> column(int number) {
        int columns = matrix.getFirst().size();

        if (number < 0 || number >= columns) {
            throw new IllegalArgumentException("Column index is out of matrix bounds.");
        }

        List<Double> result = new ArrayList<>();

        for (List<Double> row : matrix) {
            result.add(row.get(number));
        }

        return result;
    }

    @Override
    public Matrix<Double> transposedMatrix() {
        List<List<Double>> transposedMatrix = new ArrayList<>();

        int columns = matrix.getFirst().size();

        for (int column = 0; column < columns; column++) {
            List<Double> transposedRow = new ArrayList<>();

            for (List<Double> doubles : matrix) {
                transposedRow.add(doubles.get(column));
            }

            transposedMatrix.add(transposedRow);
        }

        return new DoubleMatrix(transposedMatrix);
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> other) {
        if (!(other instanceof DoubleMatrix otherDoubleMatrix)) {
            throw new IllegalArgumentException("Cannot multiply other matrix.");
        }

        int leftRows = this.matrix.size();
        int leftColumns = this.matrix.getFirst().size();

        int rightRows = otherDoubleMatrix.matrix.size();
        int rightColumns = otherDoubleMatrix.matrix.getFirst().size();

        if (leftColumns != rightRows) {
            throw new IllegalArgumentException("Cannot multiply matrixes because left columns must equal right rows.");
        }

        List<List<Double>> result = new ArrayList<>();

        for (int i = 0; i < leftRows; i++) {
            List<Double> resultRow = new ArrayList<>();

            for (int j = 0; j < rightColumns; j++) {
                double sum = 0.0;

                for (int k = 0; k < leftColumns; k++) {
                    sum += this.matrix.get(i).get(k) * otherDoubleMatrix.matrix.get(k).get(j);
                }

                resultRow.add(sum);
            }

            result.add(resultRow);
        }

        return new DoubleMatrix(result);
    }
}