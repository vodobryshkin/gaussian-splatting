package ru.vodobryshkin.ols.approximation;

import ru.vodobryshkin.ols.function.MathematicalFunction;
import ru.vodobryshkin.ols.function.ProxyExpFunction;
import ru.vodobryshkin.ols.scalar.Scalar;

import java.util.List;

public class ExpApproximation implements Scalar {
    private final List<Double> xColumn;
    private final List<Double> yColumn;

    public ExpApproximation(List<Double> xColumn, List<Double> yColumn) {
        if (xColumn.size() != yColumn.size()) {
            throw new IllegalArgumentException("xColumn and yColumn must have the same size.");
        }

        if (yColumn.stream().anyMatch(y -> y <= 0 || !Double.isFinite(y))) {
            throw new IllegalArgumentException("ExpApproximation requires positive finite y values.");
        }

        if (xColumn.stream().anyMatch(x -> !Double.isFinite(x))) {
            throw new IllegalArgumentException("ExpApproximation requires finite x values.");
        }

        this.xColumn = xColumn;
        this.yColumn = yColumn;
    }

    @Override
    public MathematicalFunction value() {
        List<Double> logYColumn = yColumn.stream()
                .map(Math::log)
                .toList();

        Scalar processor = new LinearApproximation(xColumn, logYColumn);

        return new ProxyExpFunction(processor.value());
    }
}
