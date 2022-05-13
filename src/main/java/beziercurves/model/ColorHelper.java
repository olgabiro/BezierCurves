package beziercurves.model;

public class ColorHelper {

    private static final int CHANNEL_MIN_VALUE = 0;
    private static final int CHANNEL_MAX_VALUE = 255;

    static void assertValidChannelValue(final int value) {
        if (!isValudChannelValue(value)) {
            throw new IllegalArgumentException(String.format("Invalid color channel value %1$d. The value should be between %2$d and %3$d.",
                                                             value,
                                                             CHANNEL_MIN_VALUE,
                                                             CHANNEL_MAX_VALUE));
        }
    }

    private static boolean isValudChannelValue(final int value) {
        return value >= CHANNEL_MIN_VALUE && value <= CHANNEL_MAX_VALUE;
    }

    private ColorHelper() {
    }
}
