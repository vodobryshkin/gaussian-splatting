package ru.vodobryshkin.ols.function;

public class PowerFunction implements MathematicalFunction {
    private final double a;
    private final double b;

    public PowerFunction(Number a, Number b) {
        this.a = a.doubleValue();
        this.b = b.doubleValue();
    }

    @Override
    public double value(double x) {
        return a * Math.pow(x, b);
    }
}
