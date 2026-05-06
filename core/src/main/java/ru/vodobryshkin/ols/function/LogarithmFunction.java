package ru.vodobryshkin.ols.function;

public class LogarithmFunction implements MathematicalFunction {
    private final double a;
    private final double b;

    public LogarithmFunction(Number a, Number b) {
        this.a = a.doubleValue();
        this.b = b.doubleValue();
    }

    @Override
    public double value(double x) {
        return a * Math.log(x) + b;
    }
}
