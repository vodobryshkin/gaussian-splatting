package ru.vodobryshkin.ols.slae;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoubleMatrixTest {
    static Stream<Arguments> maxElemCorrectArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 0),
                Arguments.of(2, 2)
        );
    }

    static Stream<Arguments> diagonalElementDivisionCorrectArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(1, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(0.8, 1.0, 1.2),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(2, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(7.0/9.0, 8.0/9.0, 1.0)
                )))
        );
    }

    static Stream<Arguments> swapRowsCorrectArgumentProvider() {
        return Stream.of(
                Arguments.of(1, 2, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(7.0, 8.0, 9.0),
                        List.of(4.0, 5.0, 6.0)
                ))),
                Arguments.of(0, 1, new DoubleMatrix(List.of(
                        List.of(4.0, 5.0, 6.0),
                        List.of(1.0, 2.0, 3.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(0, 2, new DoubleMatrix(List.of(
                        List.of(7.0, 8.0, 9.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(1.0, 2.0, 3.0)
                )))
                ,
                Arguments.of(2, 1, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(7.0, 8.0, 9.0),
                        List.of(4.0, 5.0, 6.0)
                )))
        );
    }

    static Stream<Arguments> twoRowsSubtractionCorrectArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 1, new DoubleMatrix(List.of(
                        List.of(-3.0, -3.0, -3.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(1, 0, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(3.0, 3.0, 3.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(0, 2, new DoubleMatrix(List.of(
                        List.of(-6.0, -6.0, -6.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(2, 0, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(6.0, 6.0, 6.0)
                ))),
                Arguments.of(1, 2, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(-3.0, -3.0, -3.0),
                        List.of(7.0, 8.0, 9.0)
                ))),
                Arguments.of(2, 1, new DoubleMatrix(List.of(
                        List.of(1.0, 2.0, 3.0),
                        List.of(4.0, 5.0, 6.0),
                        List.of(3.0, 3.0, 3.0)
                )))
        );
    }

    static Stream<Arguments> rowMultipliedByXVectorCorrectArgumentsProvider() {
        return Stream.of(
                Arguments.of(3, List.of(), 0.0),
                Arguments.of(2, List.of(1.0), 4.0),
                Arguments.of(1, List.of(2.0, 1.0), 10.0),
                Arguments.of(0, List.of(3.0, 2.0, 1.0), 16.0)
        );
    }

    @Tag("unit")
    @Test
    @DisplayName("Матрица не создаётся при несовпадении количества строк и количества столбцов.")
    void matrix_is_not_created_when_different_numbers_of_rows_and_columns() {
        assertThrows(IllegalArgumentException.class, () -> new DoubleMatrix(List.of(
                List.of(1.0, 2.0, 3.0),
                List.of(1.0, 2.0, 3.0)
        )));
    }

    @Tag("unit")
    @Test
    @DisplayName("Матрица фиксирует вырожденность при максимальном элементе меньше эпсилон.")
    void matrix_catch_its_singularity_when_max_elem_lower_than_epsilon() {
        Matrix<Double> sut = new DoubleMatrix(List.of(
                List.of(1e-10, 4.0, -2.0),
                List.of(0.0, 9.0, -3.0),
                List.of(1e-11, -3.0, 7.0)
        ));

        assertThrows(ArithmeticException.class, () -> sut.indexOfMaxInAColumn(0));
    }

    @Tag("unit")
    @Test
    @DisplayName("Матрица фиксирует деление на ноль при делении на диагональный элемент.")
    void matrix_catch_zero_division_while_dividing_on_diagonal_element() {
        Matrix<Double> sut = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 4.0, -2.0)),
                new ArrayList<>(List.of(5.0, 9.0, -3.0)),
                new ArrayList<>(List.of(0.0, -3.0, 0.0))
        )));

        assertThrows(ArithmeticException.class, () -> sut.divideRowByDiagonalElement(2));
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("maxElemCorrectArgumentsProvider")
    @DisplayName("Матрица корректно считает максимальный элемент в каждом из столбцов.")
    void matrix_calculates_max_index_of_element_in_a_column(int column, int expected) {
        Matrix<Double> sut = new DoubleMatrix(List.of(
                List.of(2.0, 9.0, -2.0),
                List.of(4.0, 4.0, -3.0),
                List.of(-2.0, -3.0, 7.0)
        ));

        int actual = sut.indexOfMaxInAColumn(column);

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("diagonalElementDivisionCorrectArgumentsProvider")
    @DisplayName("Матрица корректно делит строку на диагональный элемент в каждом из столбцов.")
    void matrix_correctly_divides_row_on_its_diagonal_element(int row, Matrix<Double> expected) {
        Matrix<Double> sut = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 2.0, 3.0)),
                new ArrayList<>(List.of(4.0, 5.0, 6.0)),
                new ArrayList<>(List.of(7.0, 8.0, 9.0))
        )));

        Matrix<Double> actual = sut.divideRowByDiagonalElement(row);

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("swapRowsCorrectArgumentProvider")
    @DisplayName("Матрица корректно меняет две строки местами.")
    void matrix_correctly_swaps_two_rows(int i, int j, Matrix<Double> expected) {
        Matrix<Double> sut = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 2.0, 3.0)),
                new ArrayList<>(List.of(4.0, 5.0, 6.0)),
                new ArrayList<>(List.of(7.0, 8.0, 9.0))
        )));

        Matrix<Double> actual = sut.swapTwoRows(i, j);

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("twoRowsSubtractionCorrectArgumentsProvider")
    @DisplayName("Матрица корректно вычитает одну строку из другой.")
    void matrix_correctly_subtracts_two_rows(int i, int j, Matrix<Double> expected) {
        Matrix<Double> sut = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 2.0, 3.0)),
                new ArrayList<>(List.of(4.0, 5.0, 6.0)),
                new ArrayList<>(List.of(7.0, 8.0, 9.0))
        )));

        Matrix<Double> actual = sut.subtractTwoRows(i, j);

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("rowMultipliedByXVectorCorrectArgumentsProvider")
    @DisplayName("Матрица корректно считает сумму строки, умноженной на вектор неизвестных.")
    void matrix_correctly_multiplies_the_row_on_a_x_vector(int i, List<Double> hut, double expected) {
        Matrix<Double> sut = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0)),
                new ArrayList<>(List.of(0.0, 2.0, 3.0, 4.0)),
                new ArrayList<>(List.of(0.0, 0.0, 3.0, 4.0)),
                new ArrayList<>(List.of(0.0, 0.0, 0.0, 4.0))
        )));

        double actual = sut.sumOfRow(i, hut);

        assertThat(actual).isEqualTo(expected);
    }
}
