package ru.vodobryshkin.ols.function;

public class LinearFunction implements MathematicalFunction {
    private final MathematicalFunction polynomial;

    public LinearFunction(Number k, Number b) {
        polynomial = new Polynomial(k, b);
    }

    @Override
    public double value(double x) {
        return polynomial.value(x);
    }
}
