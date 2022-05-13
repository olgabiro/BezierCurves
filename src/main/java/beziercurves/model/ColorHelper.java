package beziercurves.model;

public class ColorHelper {

    static final int COLOR_CHANNEL_MIN_VALUE = 0;
    static final int COLOR_CHANNEL_MAX_VALUE = 255;

    static void assertValidChannelValue(final int value) {
        if (!isValudChannelValue(value)) {
            throw new IllegalArgumentException(String.format("Invalid color channel value %1$d. The value should be between %2$d and %3$d.",
                                                             value,
                                                             COLOR_CHANNEL_MIN_VALUE,
                                                             COLOR_CHANNEL_MAX_VALUE));
        }
    }

    private static boolean isValudChannelValue(final int value) {
        return value >= COLOR_CHANNEL_MIN_VALUE && value <= COLOR_CHANNEL_MAX_VALUE;
    }

    private ColorHelper() {
    }
}
