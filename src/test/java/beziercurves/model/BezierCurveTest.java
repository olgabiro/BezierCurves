package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class BezierCurveTest {

    private final BezierCurveTestFixture testFixture = new BezierCurveTestFixture();

    @Test
    void constructor_throwsIllegalArgumentException_whenPointsIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurve(null,
                                           this.testFixture.getRandomColor()),
                     "Constructor should throw IllegalArgumentException.");
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenColorIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurve(this.testFixture.getRandomPoints(),
                                           null),
                     "Constructor should throw IllegalArgumentException.");
    }

    @Test
    void constructor_setsProperties() {
        final List<BezierPoint> randomPoints = this.testFixture.getRandomPoints();
        final Color randomColor = this.testFixture.getRandomColor();

        final BezierCurve actual = new BezierCurve(randomPoints,
                                                   randomColor);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomPoints,
                                               randomColor);
    }

    @Test
    void setPoints_throwsIllegalArgumentException_whenPointsIsNull() {
        final BezierCurve bezierCurve = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> bezierCurve.setPoints(null));
    }

    @Test
    void setPoints() {
        final BezierCurve bezierCurve = this.testFixture.create();
        final List<BezierPoint> randomPoints = this.testFixture.getRandomPoints();

        bezierCurve.setPoints(randomPoints);

        assertEquals(randomPoints,
                     bezierCurve.getPoints());
    }

    @Test
    void setColor_throwsIllegalArgumentException_whenColorIsNull() {
        final BezierCurve bezierCurve = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> bezierCurve.setColor(null));
    }

    @Test
    void setColor() {
        final BezierCurve bezierCurve = this.testFixture.create();
        final Color randomColor = this.testFixture.getRandomColor();

        bezierCurve.setColor(randomColor);

        assertEquals(randomColor,
                     bezierCurve.getColor());
    }
}