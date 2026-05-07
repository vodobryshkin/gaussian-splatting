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

public class CubicApproximationTest {
    static Stream<Arguments> cubicApproximationArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0),
                        List.of(1.6, 4.6, 11.2, 24.4, 47.2, 82.6),
                        List.of(1.600, 4.600, 11.200, 24.400, 47.200, 82.600)
                )
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("cubicApproximationArgumentProvider")
    @DisplayName("Кубическая аппроксимация функции считается корректно")
    void cubic_approximation_calculates_correctly(List<Double> xColumn, List<Double> yColumn, List<Double> expected) {
        MathematicalFunction sut = new FunctionOfScalar(
                new CachedScalar(
                        new CubicApproximation(xColumn, yColumn)
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
    @DisplayName("Кубическая аппроксимация фиксирует то, что два столбца неодинаковой длины, ввиду чего решение невозможно.")
    void cubic_approximation_catches_different_x_and_y_columns_sizes() {
        ArrayList<Double> xColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0));
        ArrayList<Double> yColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0));

        assertThrows(IllegalArgumentException.class, () -> new CubicApproximation(xColumn, yColumn));
    }
}