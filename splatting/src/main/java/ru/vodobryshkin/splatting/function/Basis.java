package ru.vodobryshkin.splatting.function;

import ru.vodobryshkin.ols.slae.Matrix;

public interface Basis {
    Matrix<Double> matrix();
}
