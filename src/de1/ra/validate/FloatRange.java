package de1.ra.validate;

public class FloatRange {
    private final float min;
    private final float max;

    public FloatRange(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public boolean isWithinRange(float value) {
        return value >= min && value <= max;
    }
}
