package ru.vodobryshkin.ols.scalar;

import ru.vodobryshkin.ols.function.MathematicalFunction;

public class CachedScalar implements Scalar {
    private final Scalar origin;
    private MathematicalFunction cached;

    public CachedScalar(Scalar origin) {
        this.origin = origin;
    }

    @Override
    public MathematicalFunction value() {
        if (this.cached == null) {
            this.cached = origin.value();
        }

        return this.cached;
    }
}
