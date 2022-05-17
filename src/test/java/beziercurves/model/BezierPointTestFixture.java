package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static beziercurves.model.BezierPoint.WEIGHT_ROUNDING_MODE;
import static beziercurves.model.BezierPoint.WEIGHT_SCALE;
import static beziercurves.model.TestHelper.getRandomBigDecimalBetween;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

class BezierPointTestFixture {

    private final CoordinateTestFixture coordinateTestFixture = new CoordinateTestFixture();

    public BezierPoint create() {
        return new BezierPoint(getRandomX(),
                               getRandomY());
    }

    public BezierPoint createWeighted() {
        return new BezierPoint(getRandomX(),
                               getRandomY(),
                               getRandomWeight());
    }

    public void assertPropertiesEqual(final BezierPoint actual,
                                      final Coordinate expectedX,
                                      final Coordinate expectedY,
                                      final Optional<BigDecimal> expectedWeight) {

        assertNotNull(actual);
        assertAll(() -> assertEquals(expectedX,
                                     actual.getX()),
                  () -> assertEquals(expectedY,
                                     actual.getY()),
                  () -> assertEquals(expectedWeight,
                                     actual.getWeight()));
    }

    public Coordinate getRandomX() {
        return this.coordinateTestFixture.create();
    }

    public Coordinate getRandomY() {
        return this.coordinateTestFixture.create();
    }

    public BigDecimal getRandomWeight() {
        return getRandomBigDecimalBetween(0,
                                          10,
                                          WEIGHT_SCALE,
                                          WEIGHT_ROUNDING_MODE);
    }
}