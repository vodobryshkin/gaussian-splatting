package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProxyPowerFunctionTest {
    @Tag("unit")
    @Test
    @DisplayName("Степенная функция корректно считает значение после линеаризации")
    void power_function_calculates_value_correctly() {
        MathematicalFunction sut = new ProxyPowerFunction(
                new ProxyLogarithmFunction(
                        new LinearFunction(2.0, Math.log(3.0))
                )
        );

        double x = 4.0;
        double expected = 48.0;

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(1e-4));
    }

    @Tag("unit")
    @Test
    @DisplayName("Степенная функция фиксирует неположительный аргумент, ввиду чего логарифмирование невозможно")
    void power_function_catches_non_positive_argument() {
        MathematicalFunction sut = new ProxyPowerFunction(
                new ProxyLogarithmFunction(
                        new LinearFunction(2.0, Math.log(3.0))
                )
        );

        assertThrows(IllegalArgumentException.class, () -> sut.value(0.0));
    }
}