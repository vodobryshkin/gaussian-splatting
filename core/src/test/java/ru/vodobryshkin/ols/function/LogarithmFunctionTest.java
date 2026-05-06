package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class LogarithmFunctionTest {
    static Stream<Arguments> definedFromBothSidesFunctionArgumentProvider() {
        return Stream.of(
                Arguments.of(Math.exp(1), 0),
                Arguments.of(1, -2)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("definedFromBothSidesFunctionArgumentProvider")
    @DisplayName("Значение логарифмической функции считается правильно.")
    void defined_power_function_calculates_correctly(double x, double expected) {
        MathematicalFunction sut = new LogarithmFunction(2, -2);

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(1e-10));
    }
}