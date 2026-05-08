package ru.vodobryshkin.ols.approximation;

import ru.vodobryshkin.ols.function.LogarithmFunction;
import ru.vodobryshkin.ols.function.MathematicalFunction;
import ru.vodobryshkin.ols.function.ProxyLogarithmFunction;
import ru.vodobryshkin.ols.function.ProxyPowerFunction;
import ru.vodobryshkin.ols.scalar.Scalar;

import java.util.List;

public class PowerApproximation implements Scalar {
    private final List<Double> xColumn;
    private final List<Double> yColumn;

    public PowerApproximation(List<Double> xColumn, List<Double> yColumn) {
        if (xColumn.size() != yColumn.size()) {
            throw new IllegalArgumentException("xColumn and yColumn must have the same size.");
        }

        if (yColumn.stream().anyMatch(y -> y <= 0 || !Double.isFinite(y))) {
            throw new IllegalArgumentException("PowerApproximation requires positive finite y values.");
        }

        if (xColumn.stream().anyMatch(x -> x <= 0 || !Double.isFinite(x))) {
            throw new IllegalArgumentException("PowerApproximation requires positive finite x values.");
        }

        this.xColumn = xColumn;
        this.yColumn = yColumn;
    }

    @Override
    public MathematicalFunction value() {
        List<Double> logXColumn = xColumn.stream()
                .map(Math::log)
                .toList();

        List<Double> logYColumn = yColumn.stream()
                .map(Math::log)
                .toList();

        Scalar processor = new LinearApproximation(logXColumn, logYColumn);

        return new ProxyPowerFunction(new ProxyLogarithmFunction(processor.value()));
    }
}
