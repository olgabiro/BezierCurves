package beziercurves.model;

import static beziercurves.model.TestHelper.createPoint;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BezierCurveApproximateProcessorTest {

    private final BezierCurveApproximateProcessor approximateProcessor = new BezierCurveApproximateProcessor();
    private final BezierCurveTestFixture bezierCurveTestFixture = new BezierCurveTestFixture();

    @Test
    void approximate_throwsIllegalArgumentException_whenCurveIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> this.approximateProcessor.approximate(null));
    }

    @Test
    void approximate_returnsExpectedResult_whenCurveIsALineSegment() {
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

        final BezierCurve actual = this.approximateProcessor.approximate(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }

    @Test
    void approximate_returnsExpectedResult_whenCurveIsComplex() {
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

        final BezierCurve actual = this.approximateProcessor.approximate(bezierCurve);

        this.bezierCurveTestFixture.assertPropertiesEqual(actual,
                                                          expectedControlPoints,
                                                          bezierCurve.getColor());
    }
}