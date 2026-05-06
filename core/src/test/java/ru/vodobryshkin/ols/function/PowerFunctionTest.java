package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class PowerFunctionTest {
    static Stream<Arguments> definedFromBothSidesFunctionArgumentProvider() {
        return Stream.of(
                Arguments.of(-1, -2),
                Arguments.of(1, 2),
                Arguments.of(0, 0)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("definedFromBothSidesFunctionArgumentProvider")
    @DisplayName("Значение степенной функции, определённой на промежутке (-inf, +inf), считается правильно.")
    void defined_power_function_calculates_correctly(double x, double expected) {
        MathematicalFunction sut = new PowerFunction(2, 3);

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(1e-10));
    }
}
