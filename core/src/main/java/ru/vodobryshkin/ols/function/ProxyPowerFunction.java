package ru.vodobryshkin.ols.function;

public class ProxyPowerFunction implements MathematicalFunction {
    private final MathematicalFunction function;

    public ProxyPowerFunction(ProxyLogarithmFunction function) {
        this.function = function;
    }

    @Override
    public double value(double x) {
        return Math.exp(function.value(x));
    }
}

