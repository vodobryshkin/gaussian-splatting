package ru.vodobryshkin.ols.slae;


import java.util.List;

public interface Matrix<T extends Number> {
    int indexOfMaxInAColumn(int columnNumber);
    int size();

    T diagonalElement(int i);
    Double sumOfRow(int i, List<Double> solutionList);

    Matrix<T> subtractTwoRows(int i, int j);
    Matrix<T> swapTwoRows(int i, int j);
    Matrix<T> divideRowByDiagonalElement(int i);
}
