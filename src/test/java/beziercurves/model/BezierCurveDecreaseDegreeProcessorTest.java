package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BezierCurveDecreaseDegreeProcessorTest {

    private final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor = new BezierCurveDecreaseDegreeProcessor();
    private final BezierCurveTestFixture bezierCurveTestFixture = new BezierCurveTestFixture();

    @Test
    void decreaseDegree_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.decreaseDegreeProcessor.decreaseDegree(null));
    }

    @Test
    void decreaseDegree_returnsExpectedResult_whenCurveIsALineSegment() {
        final BezierPoint beginningPoint = createPoint(0,
                                                       0);
        final BezierPoint endingPoint = createPoint(2,
                                                    2);
        final BezierCurve bezierCurve = new BezierCurve(Arrays.asList(beginningPoint,
                                                                      createPoint(1,
                                                                                  1),
                                                                      endingPoint),
                                                        this.bezierCurveTestFixture.getRandomColor());
        final List<BezierPoint> expectedControlPoints = Arrays.asList(beginningPoint,
                                                                      endingPoint);

        final BezierCurve actual = this.decreaseDegreeProcessor.decreaseDegree(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }

    @Test
    void decreaseDegree_returnsExpectedResult_whenCurveIsComplex() {
        final BezierPoint beginningPoint = createPoint(0,
                                                       0);
        final BezierPoint endingPoint = createPoint(6,
                                                    6);
        final BezierCurve bezierCurve = new BezierCurve(Arrays.asList(beginningPoint,
                                                                      createPoint(2,
                                                                                  2),
                                                                      createPoint(4,
                                                                                  4),
                                                                      endingPoint),
                                                        this.bezierCurveTestFixture.getRandomColor());
        final List<BezierPoint> expectedControlPoints = Arrays.asList(beginningPoint,
                                                                      createPoint(3,
                                                                                  3),
                                                                      endingPoint);

        final BezierCurve actual = this.decreaseDegreeProcessor.decreaseDegree(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }

    private BezierPoint createPoint(final int x,
                                    final int y) {

        return new BezierPoint(Coordinate.valueOf(x),
                               Coordinate.valueOf(y));
    }
}