package beziercurves.common;

import java.util.Collection;

public class ParamValidationHelper {

    public static <T> T assertNotNull(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("The value should not be null.");
        }
        return value;
    }

    public static <T extends Collection> T assertHasMinSize(final T collection,
                                                            final int minSize) {
        assertNotNull(collection);
        if (collection.size() < minSize) {
            throw new IllegalArgumentException(String.format("The collection size must be greater than %1$s",
                                                             minSize));
        }
        return collection;
    }

    private ParamValidationHelper() {
    }
}