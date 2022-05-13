package beziercurves.common;

public class ParamValidationHelper {

    public static <T> T assertNotNull(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("The value should not be null.");
        }
        return value;
    }

    private ParamValidationHelper() {
    }
}