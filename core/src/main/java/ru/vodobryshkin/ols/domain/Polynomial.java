package ru.vodobryshkin.ols.domain;

import java.util.Arrays;
import java.util.List;

public class Polynomial implements MathematicalFunction {
    private final List<Double> arguments;

    public Polynomial(Number... arguments) {
        this.arguments = Arrays.stream(arguments).map(Number::doubleValue).toList();
    }

    @Override
    public double value(double x) {
        double sum = 0;

        for (int i = 0; i < arguments.size(); i++) {
            sum += arguments.get(i) * Math.pow(x, arguments.size() - i - 1);
        }

        return sum;
    }
}
