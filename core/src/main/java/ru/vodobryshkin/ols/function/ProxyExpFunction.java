package ru.vodobryshkin.ols.function;

public class ProxyExpFunction implements MathematicalFunction {
    private final MathematicalFunction function;

    public ProxyExpFunction(MathematicalFunction function) {
        this.function = function;
    }

    @Override
    public double value(double x) {
        return Math.exp(function.value(x));
    }
}
