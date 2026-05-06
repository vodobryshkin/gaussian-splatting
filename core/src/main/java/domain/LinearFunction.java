package domain;

public class LinearFunction implements MathematicalFunction {
    private final double k;
    private final double b;

    public LinearFunction(Number k, Number b) {
        this.k = k.doubleValue();
        this.b = b.floatValue();
    }

    @Override
    public double value(double x) {
        return k * x + b;
    }
}
