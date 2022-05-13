package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static beziercurves.model.TestHelper.getRandomBigDecimal;
import static beziercurves.model.TestHelper.getRandomBigDecimalBetween;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

class BezierPointTestFixture {

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
                                      final BigDecimal expectedX,
                                      final BigDecimal expectedY,
                                      final Optional<BigDecimal> expectedWeight) {

        assertNotNull(actual);
        assertAll(() -> assertEquals(expectedX,
                                     actual.getX()),
                  () -> assertEquals(expectedY,
                                     actual.getY()),
                  () -> assertEquals(expectedWeight,
                                     actual.getWeight()));
    }

    public BigDecimal getRandomX() {
        return getRandomBigDecimal();
    }

    public BigDecimal getRandomY() {
        return getRandomBigDecimal();
    }

    public BigDecimal getRandomWeight() {
        return getRandomBigDecimalBetween(0,
                                          10);
    }
}