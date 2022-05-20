package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class BezierCurveTest {

    private final BezierCurveTestFixture testFixture = new BezierCurveTestFixture();

    @Test
    void constructor_throwsIllegalArgumentException_whenControlPointsIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurve(null,
                                           this.testFixture.getRandomColor()),
                     "Constructor should throw IllegalArgumentException.");
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenColorIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurve(this.testFixture.getRandomControlPoints(),
                                           null),
                     "Constructor should throw IllegalArgumentException.");
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenTooLittleControlPoints() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierCurve(List.of(this.testFixture.getRandomControlPoint()),
                                           this.testFixture.getRandomColor()));
    }

    @Test
    void constructor_setsProperties() {
        final List<BezierPoint> randomControlPoints = this.testFixture.getRandomControlPoints();
        final Color randomColor = this.testFixture.getRandomColor();

        final BezierCurve actual = new BezierCurve(randomControlPoints,
                                                   randomColor);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomControlPoints,
                                               randomColor);
    }

    @Test
    void setControlPoints_throwsIllegalArgumentException_whenPointsIsNull() {
        final BezierCurve bezierCurve = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> bezierCurve.setControlPoints(null));
    }

    @Test
    void setControlPoints_throwsIllegalArgumentException_whenTooLittleControlPoints() {
        final BezierCurve bezierCurve = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> bezierCurve.setControlPoints(List.of(this.testFixture.getRandomControlPoint())));
    }

    @Test
    void setPoints() {
        final BezierCurve bezierCurve = this.testFixture.create();
        final List<BezierPoint> randomControlPoints = this.testFixture.getRandomControlPoints();

        bezierCurve.setControlPoints(randomControlPoints);

        assertEquals(randomControlPoints,
                     bezierCurve.getControlPoints());
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