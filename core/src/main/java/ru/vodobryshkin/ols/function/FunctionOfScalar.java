package ru.vodobryshkin.ols.function;

import ru.vodobryshkin.ols.scalar.Scalar;

public class FunctionOfScalar implements MathematicalFunction {
    private final Scalar scalar;

    public FunctionOfScalar(Scalar scalar) {
        this.scalar = scalar;
    }

    @Override
    public double value(double x) {
        return scalar.value().value(x);
    }
}
