package de1.ra.validate;

public class ByteRange {
    private final byte min;
    private final byte max;

    public ByteRange(byte min, byte max) {
        this.min = min;
        this.max = max;
    }

    public byte getMin() {
        return min;
    }

    public byte getMax() {
        return max;
    }

    public boolean isWithinRange(byte value) {
        return value >= min && value <= max;
    }
}
