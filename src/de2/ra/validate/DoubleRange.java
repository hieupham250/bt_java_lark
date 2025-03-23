package de2.ra.validate;

public class DoubleRange {
    private double min;
    private double max;

    public DoubleRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isWithinRange(float value) {
        return value >= min && value <= max;
    }
}
