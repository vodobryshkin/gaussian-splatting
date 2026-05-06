package ru.vodobryshkin.ols.domain;

public class SecondDegreePolynomial implements MathematicalFunction {
    private final MathematicalFunction polynomial;

    public SecondDegreePolynomial(Number a, Number b, Number c) {
        polynomial = new Polynomial(a, b, c);
    }

    @Override
    public double value(double x) {
        return polynomial.value(x);
    }
}
