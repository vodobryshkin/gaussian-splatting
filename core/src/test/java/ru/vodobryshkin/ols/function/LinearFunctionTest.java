package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LinearFunctionTest {
    static Stream<Arguments> stableKBArgumentProvider() {
        return Stream.of(
                Arguments.of(5, 4, -2, -5),
                Arguments.of(-10, 2, 2.2, -20),
                Arguments.of(-20.3213, -268, -12.202, -20.0395),
                Arguments.of(15, -8, 2.6, 31)
        );
    }

    static Stream<Arguments> zeroKArgumentProvider() {
        return Stream.of(
                Arguments.of(4, 1),
                Arguments.of(-10, 2),
                Arguments.of(-15.25, 100)
        );
    }

    static Stream<Arguments> zeroBArgumentProvider() {
        return Stream.of(
                Arguments.of(5, 0, 0),
                Arguments.of(10, 2, 20)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("stableKBArgumentProvider")
    @DisplayName("Значение линейной функции считается правильно при ненулевых k и b.")
    void linear_function_calculates_correctly_with_stable_parameters() {
        double expected = -5;
        MathematicalFunction sut = new LinearFunction(5, 5);

        double actual = sut.value(-2);

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("zeroKArgumentProvider")
    @DisplayName("Значение линейной функции считается правильно при фиксированном k.")
    void linear_function_calculates_correctly_with_k_0(double b, double x) {
        MathematicalFunction sut = new LinearFunction(0, b);

        double actual = sut.value(x);

        assertThat(actual).isEqualTo(b);
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("zeroBArgumentProvider")
    @DisplayName("Значение линейной функции считается правильно при фиксированном k.")
    void linear_function_calculates_correctly_with_b_0(double k, double x, double expected) {
        MathematicalFunction sut = new LinearFunction(k, 0);

        double actual = sut.value(x);

        assertThat(actual).isEqualTo(expected);
    }
}
