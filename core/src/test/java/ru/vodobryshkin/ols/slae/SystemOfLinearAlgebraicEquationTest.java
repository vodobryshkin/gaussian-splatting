package ru.vodobryshkin.ols.slae;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SystemOfLinearAlgebraicEquationTest {
    static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of(new DoubleMatrix(new ArrayList<>(List.of(
                        new ArrayList<>(List.of(2.0, 1.0, -1.0)),
                        new ArrayList<>(List.of(-3.0, -1.0, 2.0)),
                        new ArrayList<>(List.of(-2.0, 1.0, 2.0))
                ))),
                        new ArrayList<>(List.of(8.0, -11.0, -3.0)),
                        new ArrayList<>(List.of(2.0, 3.0, -1.0))
                ),
                Arguments.of(new DoubleMatrix(new ArrayList<>(List.of(
                                new ArrayList<>(List.of(0.0, 2.0, 1.0)),
                                new ArrayList<>(List.of(1.0, 1.0, 2.0)),
                                new ArrayList<>(List.of(2.0, 1.0, 1.0))
                        ))),
                        new ArrayList<>(List.of(4.0, 7.0, 7.0)),
                        new ArrayList<>(List.of(2.0, 1.0, 2.0))
                ),
                Arguments.of(new DoubleMatrix(new ArrayList<>(List.of(
                                new ArrayList<>(List.of(0.0001, 1.0)),
                                new ArrayList<>(List.of(1.0, 1.0))
                        ))),
                        new ArrayList<>(List.of(1.0, 2.0)),
                        new ArrayList<>(List.of(1.0001, 0.9999))
                )
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("argumentsProvider")
    @DisplayName("СЛАУ считается и даёт корректный ответ.")
    void SLAE_calculates_correctly(Matrix<Double> matrix, List<Double> b, List<Double> expected) {
        SystemOfLinearAlgebraicEquation sut = new DefaultSystemOfLinearAlgebraicEquation(matrix, b);

        List<Double> actual = sut.solutionVector();

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isCloseTo(expected.get(i), within(1e-4));
        }
    }

    @Tag("unit")
    @Test
    @DisplayName("СЛАУ фиксирует вырожденность и невозможность решения.")
    void SLAE_catches_singularity() {
        Matrix<Double> matrix = new DoubleMatrix(new ArrayList<>(List.of(
                new ArrayList<>(List.of(1.0, 1.0)),
                new ArrayList<>(List.of(2.0, 2.0))
        )));
        List<Double> b = new ArrayList<>(List.of(2.0, 5.0));
        SystemOfLinearAlgebraicEquation sut = new DefaultSystemOfLinearAlgebraicEquation(matrix, b);

        assertThrows(ArithmeticException.class, sut::solutionVector);
    }
}
