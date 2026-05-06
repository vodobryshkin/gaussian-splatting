package ru.vodobryshkin.ols.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class SecondDegreePolynomialTest {
    static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of(0.1, 0.95),
                Arguments.of(0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    @DisplayName("Значение полинома второй степени считается правильно.")
    void second_degree_polynomial_calculates_correct_with_stable_parameters(double x, double expected) {
        MathematicalFunction sut = new SecondDegreePolynomial(5, -1, 1);

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(1e-10));
    }
}
