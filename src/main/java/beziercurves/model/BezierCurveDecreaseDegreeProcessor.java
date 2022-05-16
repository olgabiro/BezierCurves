package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static beziercurves.model.BezierPointHelper.newBigDecimal;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Collections.reverse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BezierCurveDecreaseDegreeProcessor {

    BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        curve.setControlPoints(calculateNewControlPoints(curve));
        return curve;
    }

    private List<BezierPoint> calculateNewControlPoints(final BezierCurve curve) {
        final List<BezierPoint> controlPoints = curve.getControlPoints();
        final List<BezierPoint> newControlPoints = new ArrayList<>(controlPoints.size());

        newControlPoints.addAll(calculateFirstHalfOfPoints(controlPoints));
        newControlPoints.addAll(calculateSecondHalfOfPoints(controlPoints));
        correctMiddlePoint(controlPoints,
                           newControlPoints);

        return newControlPoints;
    }

    private void correctMiddlePoint(final List<BezierPoint> controlPoints,
                                    final List<BezierPoint> newControlPoints) {

        final int n = controlPoints.size() - 1;
        final int middleIndex = n / 2;
        final BigDecimal factor = newBigDecimal(n).divide(newBigDecimal(middleIndex).add(newBigDecimal(1)),
                                                          HALF_UP);

        final BigDecimal x = factor.multiply(controlPoints.get(middleIndex + 1)
                                                          .getX())
                                   .add(newBigDecimal(1)
                                                .subtract(factor)
                                                .multiply(newControlPoints.get((n + 1) / 2)
                                                                          .getX()));
        final BigDecimal y = factor.multiply(controlPoints.get(middleIndex + 1)
                                                          .getY())
                                   .add(newBigDecimal(1)
                                                .subtract(factor)
                                                .multiply(newControlPoints.get((n + 1) / 2)
                                                                          .getY()));

        final BezierPoint newMiddlePoint = findAverage(new BezierPoint(x,
                                                                       y),
                                                       newControlPoints.get(middleIndex));
        correctPoint(newControlPoints,
                     middleIndex,
                     newMiddlePoint);
    }

    private void correctPoint(final List<BezierPoint> pointList,
                              final int index,
                              final BezierPoint newPoint) {
        pointList.remove(index);
        pointList.add(index,
                      newPoint);
    }

    private BezierPoint findAverage(final BezierPoint point1,
                                    final BezierPoint point2) {

        final BigDecimal x = point1.getX()
                                   .add(point2.getX())
                                   .divide(newBigDecimal(2),
                                           HALF_UP);
        final BigDecimal y = point1.getY()
                                   .add(point2.getY())
                                   .divide(newBigDecimal(2),
                                           HALF_UP);
        return new BezierPoint(x,
                               y);
    }

    private List<BezierPoint> calculateSecondHalfOfPoints(final List<BezierPoint> controlPoints) {
        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final int n = controlPoints.size() - 1;
        BezierPoint previousPoint = new BezierPoint(BigDecimal.ZERO,
                                                    BigDecimal.ZERO);
        for (int i = n; i > n / 2; i--) {
            final BigDecimal factor = newBigDecimal(n).divide(newBigDecimal(i),
                                                              HALF_UP);
            final BezierPoint currentPoint = controlPoints.get(i);

            final BigDecimal x = factor.multiply(currentPoint.getX())
                                       .add(newBigDecimal(1).subtract(factor)
                                                            .multiply(previousPoint.getX()));
            final BigDecimal y = factor.multiply(currentPoint.getY())
                                       .add(newBigDecimal(1).subtract(factor)
                                                            .multiply(previousPoint.getY()));

            final BezierPoint newPoint = new BezierPoint(x,
                                                         y);
            newControlPoints.add(newPoint);
            previousPoint = newPoint;
        }
        reverse(newControlPoints);
        return newControlPoints;
    }

    private List<BezierPoint> calculateFirstHalfOfPoints(final List<BezierPoint> controlPoints) {
        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final BezierPoint firstPoint = controlPoints.get(0);
        newControlPoints.add(firstPoint);
        BezierPoint previousPoint = firstPoint;

        final int n = controlPoints.size() - 1;
        for (int i = 1; i < n / 2; i++) {
            final BigDecimal factor = newBigDecimal(i)
                    .divide(newBigDecimal(n - i),
                            HALF_UP);
            final BezierPoint currentPoint = controlPoints.get(i);

            final BigDecimal x = newBigDecimal(1).add(factor)
                                                 .multiply(currentPoint.getX())
                                                 .subtract(factor.multiply(previousPoint.getX()));
            final BigDecimal y = newBigDecimal(1).add(factor)
                                                 .multiply(currentPoint.getY())
                                                 .subtract(factor.multiply(previousPoint.getY()));
            final BezierPoint newPoint = new BezierPoint(x,
                                                         y);
            newControlPoints.add(newPoint);
            previousPoint = newPoint;
        }
        return newControlPoints;
    }
}