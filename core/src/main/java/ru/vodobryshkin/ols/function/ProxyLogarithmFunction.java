package ru.vodobryshkin.ols.function;

public class ProxyLogarithmFunction implements MathematicalFunction {
    private final MathematicalFunction function;

    public ProxyLogarithmFunction(MathematicalFunction function) {
        this.function = function;
    }

    @Override
    public double value(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm argument must be positive.");
        }

        return function.value(Math.log(x));
    }
}
