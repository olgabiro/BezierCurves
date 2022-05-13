package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static beziercurves.model.TestHelper.getRandomBigDecimal;
import static beziercurves.model.TestHelper.getRandomIntBetween;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

class BezierPointTestFixture {

    public BezierPoint create() {
        return new BezierPoint(getRandomX(),
                               getRandomY(),
                               getRandomWeight());
    }

    public void assertPropertiesEqual(final BezierPoint actual,
                                      final BigDecimal expectedX,
                                      final BigDecimal expectedY,
                                      final int expectedWeight) {

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

    public int getRandomWeight() {
        return getRandomIntBetween(0,
                                   10);
    }
}