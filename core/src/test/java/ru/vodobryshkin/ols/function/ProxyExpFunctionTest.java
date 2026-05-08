package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ProxyExpFunctionTest {
    @Tag("unit")
    @Test
    @DisplayName("Экспоненциальная функция корректно считает значение после линеаризации")
    void exp_function_calculates_value_correctly() {
        MathematicalFunction sut = new ProxyExpFunction(new LinearFunction(0.7, 1.2));
        double x = 1;
        double expected = 6.68589444;

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(1e-4));
    }
}
