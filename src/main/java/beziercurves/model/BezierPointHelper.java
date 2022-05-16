package beziercurves.model;

import static beziercurves.model.BezierPoint.COORDINATE_ROUNDING_MODE;
import static beziercurves.model.BezierPoint.COORDINATE_SCALE;

import java.math.BigDecimal;

public class BezierPointHelper {

    static BigDecimal newBigDecimal(final int value) {
        return BigDecimal.valueOf(value)
                         .setScale(COORDINATE_SCALE,
                                   COORDINATE_ROUNDING_MODE);
    }

    private BezierPointHelper() {
    }
}
