package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class BezierCurveTestFixture {

    private final BezierPointTestFixture bezierPointTestFixture = new BezierPointTestFixture();
    private final ColorTestFixture colorTestFixture = new ColorTestFixture();

    public BezierCurve create() {
        return new BezierCurve(getRandomPoints(),
                               getRandomColor());
    }

    public BezierCurve createRational() {
        return new BezierCurve(getRandomWeightedPoints(),
                               getRandomColor());
    }

    public void assertPropertiesEqual(final BezierCurve actual,
                                      final List<BezierPoint> expectedPoints,
                                      final Color expectedColor) {

        assertNotNull(actual);
        assertAll(() -> assertEquals(expectedPoints,
                                     actual.getPoints()),
                  () -> assertEquals(expectedColor,
                                     actual.getColor()));
    }

    public BezierPoint getRandomPoint() {
        return this.bezierPointTestFixture.create();
    }

    public BezierPoint getRandomWeightedPoint() {
        return this.bezierPointTestFixture.createWeighted();
    }

    public List<BezierPoint> getRandomPoints() {
        return Arrays.asList(getRandomPoint(),
                             getRandomPoint(),
                             getRandomPoint());
    }

    public List<BezierPoint> getRandomWeightedPoints() {
        return Arrays.asList(getRandomWeightedPoint(),
                             getRandomWeightedPoint(),
                             getRandomWeightedPoint());
    }

    public Color getRandomColor() {
        return this.colorTestFixture.create();
    }
}