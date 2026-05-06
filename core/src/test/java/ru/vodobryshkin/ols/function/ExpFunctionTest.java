package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ExpFunctionTest {
    static Stream<Arguments> risingFunctionArgumentsProvider() {
        return Stream.of(
                Arguments.of(-2, 0.091215),
                Arguments.of(-1, 0.3699),
                Arguments.of(0, 1.5),
                Arguments.of(1, 6.0828)
        );
    }

    static Stream<Arguments> downRisingFunctionArgumentsProvider() {
        return Stream.of(
                Arguments.of(-2, -0.61649),
                Arguments.of(-1, -1.24146),
                Arguments.of(0, -2.5),
                Arguments.of(1, -5.03438)
        );
    }

    static Stream<Arguments> constFunctionArgumentsProvider() {
        return Stream.of(
                Arguments.of(-2, 4),
                Arguments.of(-1, 4),
                Arguments.of(0, 4),
                Arguments.of(1, 4)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("risingFunctionArgumentsProvider")
    @DisplayName("Значение экспоненциальной функции считается правильно для возрастающей экспоненциальной функции.")
    void rising_function_calculates_correctly(double x, double expected) {
        MathematicalFunction sup = new ExpFunction(1.5, 1.4);

        double actual = sup.value(x);

        assertThat(actual).isCloseTo(expected, within(0.0001));
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("downRisingFunctionArgumentsProvider")
    @DisplayName("Значение экспоненциальной функции считается правильно для убывающей экспоненциальной функции.")
    void uprising_function_calculates_correctly(double x, double expected) {
        MathematicalFunction sup = new ExpFunction(-2.5, 0.7);

        double actual = sup.value(x);

        assertThat(actual).isCloseTo(expected, within(0.0001));
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("constFunctionArgumentsProvider")
    @DisplayName("Значение экспоненциальной функции считается правильно при b = 0.")
    void const_function_calculates_correctly(double x, double expected) {
        MathematicalFunction sup = new ExpFunction(4, 0);

        double actual = sup.value(x);

        assertThat(actual).isCloseTo(expected, within(0.0001));
    }
}
