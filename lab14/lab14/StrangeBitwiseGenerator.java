package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;
    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }
    @Override
    public double next() {
        state++;
        int weirdState = state & (state >>> 3) % period;
        return normalize(weirdState);
    }
    private double normalize(int x) {
        return (double) x * 2 / period - 1;
    }
}
