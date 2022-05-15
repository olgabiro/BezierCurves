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
        return new BezierCurve(getRandomControlPoints(),
                               getRandomColor());
    }

    public BezierCurve createRational() {
        return new BezierCurve(getRandomWeightedControlPoints(),
                               getRandomColor());
    }

    public void assertPropertiesEqual(final BezierCurve actual,
                                      final List<BezierPoint> expectedControlPoints,
                                      final Color expectedColor) {

        assertNotNull(actual);
        assertAll(() -> assertEquals(expectedControlPoints,
                                     actual.getControlPoints()),
                  () -> assertEquals(expectedColor,
                                     actual.getColor()));
    }

    public BezierPoint getRandomControlPoint() {
        return this.bezierPointTestFixture.create();
    }

    public BezierPoint getRandomWeightedControlPoint() {
        return this.bezierPointTestFixture.createWeighted();
    }

    public List<BezierPoint> getRandomControlPoints() {
        return Arrays.asList(getRandomControlPoint(),
                             getRandomControlPoint(),
                             getRandomControlPoint());
    }

    public List<BezierPoint> getRandomWeightedControlPoints() {
        return Arrays.asList(getRandomWeightedControlPoint(),
                             getRandomWeightedControlPoint(),
                             getRandomWeightedControlPoint());
    }

    public Color getRandomColor() {
        return this.colorTestFixture.create();
    }
}