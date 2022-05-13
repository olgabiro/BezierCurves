package beziercurves.common;

public class ParamValidationHelper {

    public static Object assertNotNull(final Object value) {
        if (value == null) {
            throw new IllegalArgumentException("The value should not be null.");
        }
        return value;
    }

    private ParamValidationHelper() {
    }
}