package ru.vodobryshkin.splatting.image;

import function.FunctionOfTwoVariables;
import function.GaussianWithFixedSigma;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GaussianGridTest {
    static Stream<Arguments> gaussianGridArgumentProvider() {
        return Stream.of(
                Arguments.of(20, 20, List.of(
                        new GaussianWithFixedSigma(1, 0, 0),
                        new GaussianWithFixedSigma(1, 19, 0),
                        new GaussianWithFixedSigma(1, 0, 19),
                        new GaussianWithFixedSigma(1, 19, 19)
                )),
                Arguments.of(19, 19, List.of(
                        new GaussianWithFixedSigma(1, 0, 0),
                        new GaussianWithFixedSigma(1, 18, 0),
                        new GaussianWithFixedSigma(1, 0, 18),
                        new GaussianWithFixedSigma(1, 18, 18)
                ))
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("gaussianGridArgumentProvider")
    @DisplayName("Сетка корректно строится по переданным данным")
    void gaussian_grid_builds_correctly(int height, int width, List<FunctionOfTwoVariables> expected) {
        Grid sut = new GaussianGrid(2, 2, 1);

        List<FunctionOfTwoVariables> actual = sut.functionRepresentation(height, width);

        assertThat(actual).isEqualTo(expected);
    }
}
