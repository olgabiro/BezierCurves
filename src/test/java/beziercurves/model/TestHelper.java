package beziercurves.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestHelper {

    private static final int BIG_DECIMAL_DEFAULT_MIN_VALUE = -100;
    private static final int BIG_DECIMAL_DEFAULT_MAX_VALUE = 100;

    public static int getRandomInt() {
        return getRandomIntBetween(Integer.MIN_VALUE,
                                   Integer.MAX_VALUE);
    }

    public static int getRandomIntBetween(final int minValue,
                                          final int maxValue) {

        return (int) (getRandomDoubleBetween(minValue,
                                             maxValue) + minValue);
    }

    private static double getRandomDoubleBetween(final int minValue,
                                                 final int maxValue) {
        return Math.random() * (maxValue - minValue);
    }

    public static BigDecimal getRandomBigDecimal(final int scale,
                                                 final RoundingMode roundingMode) {

        return getRandomBigDecimalBetween(BIG_DECIMAL_DEFAULT_MIN_VALUE,
                                          BIG_DECIMAL_DEFAULT_MAX_VALUE,
                                          scale,
                                          roundingMode);
    }

    public static BigDecimal getRandomBigDecimalBetween(final int minValue,
                                                        final int maxValue,
                                                        final int scale,
                                                        final RoundingMode roundingMode) {

        return BigDecimal.valueOf(getRandomDoubleBetween(minValue,
                                                         maxValue))
                         .setScale(scale,
                                   roundingMode);
    }

    public static BezierPoint createPoint(final int x,
                                          final int y) {

        return new BezierPoint(Coordinate.valueOf(x),
                               Coordinate.valueOf(y));
    }

    private TestHelper() {
    }
}
