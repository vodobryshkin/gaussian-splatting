package function;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class GaussianWithFixedSigma implements FunctionOfTwoVariables {
    private final double sigma;
    private final double centerX;
    private final double centerY;

    public GaussianWithFixedSigma(double sigma, double centerX, double centerY) {
        this.sigma = sigma;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public double value(double x, double y) {
        double numerator = Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2);
        double denominator = 2 * sigma * sigma;

        return Math.exp(- numerator / denominator);
    }
}
