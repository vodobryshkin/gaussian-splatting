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

public class LinearApproximationTest {
    static Stream<Arguments> linearApproximationArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(1.2, 2.9, 4.1, 5.5, 6.7, 7.8, 9.2, 10.3),
                        List.of(7.4, 9.5, 11.1, 12.9, 14.6, 17.3, 18.2, 20.7),
                        List.of(7.0363, 9.5086, 11.2538, 13.2899, 15.0351, 16.6348, 18.6709, 20.2707)
                )
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("linearApproximationArgumentProvider")
    @DisplayName("Линейная аппроксимация функции считается корректно")
    void linear_approximation_calculates_correctly(List<Double> xColumn, List<Double> yColumn, List<Double> expected) {
        MathematicalFunction sut = new FunctionOfScalar(
                new CachedScalar(
                        new LinearApproximation(xColumn, yColumn)
                )
        );

        List<Double> actual = new ArrayList<>();

        for (Double x : xColumn) {
            actual.add(sut.value(x));
        }

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isCloseTo(expected.get(i), within(1e-4));
        }
    }

    @Tag("unit")
    @Test
    @DisplayName("Линейная аппроксимация фиксирует то, что два столбца неодинаковой длины, ввиду чего решение невозможно.")
    void linear_approximation_catches_different_x_and_y_columns_sizes() {
        ArrayList<Double> xColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0));
        ArrayList<Double> yColumn = new ArrayList<>(List.of(1.0, 2.0, 3.0));

        assertThrows(IllegalArgumentException.class, () -> new LinearApproximation(xColumn, yColumn));
    }
}
