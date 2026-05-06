package ru.vodobryshkin.ols.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class ThirdDegreePolynomialTest {
    static Stream<Arguments> thirdDegreeArgumentsProvider() {
        return Stream.of(
                Arguments.of(-1, 4),
                Arguments.of(1, 4)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("thirdDegreeArgumentsProvider")
    @DisplayName("Значение полинома второй степени считается правильно.")
    void third_degree_polynomial_calculates_correctly_with_stable_parameters(double x, double expected) {
        MathematicalFunction sut = new ThirdDegreePolynomial(2, 2, -2, 2);

        double actual = sut.value(x);
        System.out.println(x);

        assertThat(actual).isCloseTo(expected, within(1e-10));
    }
}
