package ru.vodobryshkin.ols.function;

public class ThirdDegreePolynomial implements MathematicalFunction {
    private final MathematicalFunction polynomial;

    public ThirdDegreePolynomial(Number a3, Number a2, Number a1, Number a0) {
        this.polynomial = new Polynomial(a3, a2, a1, a0);
    }

    @Override
    public double value(double x) {
        return polynomial.value(x);
    }
}
