package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class PolynomialTest {
    static Stream<Arguments> oddDegreePolynomialArgumentsProvider() {
        return Stream.of(
                Arguments.of(1.07726, 18.48501),
                Arguments.of(0.30327, 3.07739),
                Arguments.of(-2.763, 10.03978)
        );
    }

    static Stream<Arguments> notOddDegreePolynomialArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 10),
                Arguments.of(-10, 0),
                Arguments.of(10, 20),
                Arguments.of(-10.85, -5.03657)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("oddDegreePolynomialArgumentsProvider")
    @DisplayName("Значение многочлена четной (четвертой) степени считается правильно.")
    void odd_degree_polynomial_calculates_correctly(double x, double expected) {
        MathematicalFunction sut = new Polynomial(-19, -14, 89, -48, 10);

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(0.00001));
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("notOddDegreePolynomialArgumentsProvider")
    @DisplayName("Значение многочлена нечетной (пятой) степени считается правильно.")
    void not_odd_degree_polynomial_calculates_correctly(double x, double expected) {
        MathematicalFunction sut = new Polynomial(0.0001, 0, 0, 0, 0, 10);

        double actual = sut.value(x);

        assertThat(actual).isCloseTo(expected, within(0.00001));
    }
}
