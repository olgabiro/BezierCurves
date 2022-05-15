package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BezierCurveIncreaseDegreeProcessorTest {

    private final BezierCurveIncreaseDegreeProcessor bezierCurveIncreaseDegreeProcessor = new BezierCurveIncreaseDegreeProcessor();
    private final BezierCurveTestFixture bezierCurveTestFixture = new BezierCurveTestFixture();

    @Test
    void increaseDegree_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.bezierCurveIncreaseDegreeProcessor.increaseDegree(null));
    }

    @Test
    void increaseDegree_returnsExpectedResult_whenCurveIsALineSegment() {
        final BezierPoint beginningPoint = createPoint(0,
                                                       0);
        final BezierPoint endingPoint = createPoint(2,
                                                    2);
        final BezierCurve bezierCurve = new BezierCurve(Arrays.asList(beginningPoint,
                                                                      endingPoint),
                                                        this.bezierCurveTestFixture.getRandomColor());
        final List<BezierPoint> expectedControlPoints = Arrays.asList(beginningPoint,
                                                                      createPoint(1,
                                                                                  1),
                                                                      endingPoint);

        final BezierCurve actual = this.bezierCurveIncreaseDegreeProcessor.increaseDegree(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }

    @Test
    void increaseDegree_returnsExpectedResult_whenCurveIsComplex() {
        final BezierPoint beginningPoint = createPoint(0,
                                                       0);
        final BezierPoint endingPoint = createPoint(12,
                                                    12);
        final BezierCurve bezierCurve = new BezierCurve(Arrays.asList(beginningPoint,
                                                                      createPoint(4,
                                                                                  4),
                                                                      createPoint(8,
                                                                                  8),
                                                                      endingPoint),
                                                        this.bezierCurveTestFixture.getRandomColor());
        final List<BezierPoint> expectedControlPoints = Arrays.asList(beginningPoint,
                                                                      createPoint(3,
                                                                                  3),
                                                                      createPoint(6,
                                                                                  6),
                                                                      createPoint(9,
                                                                                  9),
                                                                      endingPoint);

        final BezierCurve actual = this.bezierCurveIncreaseDegreeProcessor.increaseDegree(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }

    private BezierPoint createPoint(final int x,
                                    final int y) {
        return new BezierPoint(BigDecimal.valueOf(x),
                               BigDecimal.valueOf(y));
    }
}