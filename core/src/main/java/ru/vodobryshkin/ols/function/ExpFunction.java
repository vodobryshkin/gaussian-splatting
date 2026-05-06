package ru.vodobryshkin.ols.function;

public class ExpFunction implements MathematicalFunction {
    private final double a;
    private final double b;

    public ExpFunction(Number a, Number b) {
        this.a = a.doubleValue();
        this.b = b.doubleValue();
    }

    @Override
    public double value(double x) {
        return a * Math.exp(b * x);
    }
}
