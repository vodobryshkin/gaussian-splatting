package ru.vodobryshkin.splatting.gaussian;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.vodobryshkin.ols.slae.DoubleMatrix;
import ru.vodobryshkin.ols.slae.Matrix;
import ru.vodobryshkin.splatting.function.Basis;
import ru.vodobryshkin.splatting.function.FunctionOfTwoVariables;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GaussianBasisTest {
    @Tag("unit")
    @Test
    @DisplayName("GaussianBasis корректно строит матрицу значений функций по пикселям.")
    void gaussian_basis_correctly_represents_functions_on_pixels() {
        List<FunctionOfTwoVariables> functions = List.of(
                (x, y) -> x,
                (x, y) -> y,
                Double::sum
        );

        Basis sut = new GaussianBasis(2, 3, functions);

        Matrix<Double> actual = sut.matrix();

        Matrix<Double> expected = new DoubleMatrix(List.of(
                List.of(0.0, 0.0, 0.0),
                List.of(1.0, 0.0, 1.0),
                List.of(2.0, 0.0, 2.0),
                List.of(0.0, 1.0, 1.0),
                List.of(1.0, 1.0, 2.0),
                List.of(2.0, 1.0, 3.0)
        ));

        assertThat(actual).isEqualTo(expected);
    }
}
