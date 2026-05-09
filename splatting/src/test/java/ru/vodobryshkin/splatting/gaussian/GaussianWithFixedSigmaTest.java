package ru.vodobryshkin.splatting.gaussian;

import ru.vodobryshkin.splatting.function.FunctionOfTwoVariables;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class GaussianWithFixedSigmaTest {
    static Stream<Arguments> gaussianArgumentProvider() {
        return Stream.of(
                Arguments.of(0, 0, 1, 0, 0, 1.0),
                Arguments.of(0, 0, 1, 1, 0, 0.606530659713),
                Arguments.of( 0, 0, 1, 0, 1, 0.606530659713),
                Arguments.of(1.5, -2.0, 0.5, 2.0, -1.5, 0.367879441171),
                Arguments.of(1.5, -2.0, 0.5, 2.5, -2.0, 0.135335283237),
                Arguments.of(1.5, -2.0, 0.5, 2.5, -1.0, 0.018315638889),
                Arguments.of(2, -1, 2, 6, -1, 0.135335283237),
                Arguments.of(2, -1, 2, 6, 3, 0.018315638889)
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("gaussianArgumentProvider")
    @DisplayName("Гауссиан корректно считается.")
    void gaussian_calculates_correctly(double centerX, double centerY, double sigma, double x, double y, double expected) {
        FunctionOfTwoVariables sut = new GaussianWithFixedSigma(sigma, centerX, centerY);

        double actual = sut.value(x, y);

        assertThat(actual).isCloseTo(expected, within(1e-5));
    }
}
