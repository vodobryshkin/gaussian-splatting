package ru.vodobryshkin.ols.approximation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vodobryshkin.ols.function.FunctionOfScalar;
import ru.vodobryshkin.ols.function.MathematicalFunction;
import ru.vodobryshkin.ols.scalar.CachedScalar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuadraticApproximationTest {
    static Stream<Arguments> quadraticApproximationArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(1.0, 2.0, 3.0, 4.0, 5.0),
                        List.of(2.5, 4.2, 7.8, 10.9, 15.5),
                        List.of(2.397, 4.531, 7.423, 11.071, 15.477)
                )
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("quadraticApproximationArgumentProvider")
    @DisplayName("Квадратичная аппроксимация функции считается корректно")
    void quadratic_approximation_calculates_correctly(List<Double> xColumn, List<Double> yColumn, List<Double> expected) {
        MathematicalFunction sut = new FunctionOfScalar(
                new CachedScalar(
                        new QuadraticApproximation(xColumn, yColumn)
                )
        );

        List<Double> actual = new ArrayList<>();

        for (Double x : xColumn) {
            actual.add(sut.value(x));
        }

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isCloseTo(expected.get(i), within(1e-3));
        }
    }

    @Tag("unit")
    @Test
    @DisplayName("Линейная аппроксимация фиксирует то, что два столбца неодинаковой длины, ввиду чего решение невозможно.")
    void quadratic_approximation_catches_different_x_and_y_columns_sizes() {
        ArrayList<Double> xColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0));
        ArrayList<Double> yColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0));

        assertThrows(IllegalArgumentException.class, () -> new QuadraticApproximation(xColumn, yColumn));
    }
}
