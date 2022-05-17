package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static java.util.Collections.reverse;

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

    private List<BezierPoint> calculateFirstHalfOfPoints(final List<BezierPoint> controlPoints) {
        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final BezierPoint firstPoint = controlPoints.get(0);
        newControlPoints.add(firstPoint);
        BezierPoint previousPoint = firstPoint;

        final int n = controlPoints.size() - 1;
        for (int i = 1; i < n / 2; i++) {
            final Coordinate factor = Coordinate.valueOf(i)
                                                .divide(Coordinate.valueOf(n - i));
            final BezierPoint currentPoint = controlPoints.get(i);

            final Coordinate x = Coordinate.valueOf(1)
                                           .add(factor)
                                           .multiply(currentPoint.getX())
                                           .subtract(factor.multiply(previousPoint.getX()));
            final Coordinate y = Coordinate.valueOf(1)
                                           .add(factor)
                                           .multiply(currentPoint.getY())
                                           .subtract(factor.multiply(previousPoint.getY()));
            final BezierPoint newPoint = new BezierPoint(x,
                                                         y);
            newControlPoints.add(newPoint);
            previousPoint = newPoint;
        }
        return newControlPoints;
    }

    private List<BezierPoint> calculateSecondHalfOfPoints(final List<BezierPoint> controlPoints) {
        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final int n = controlPoints.size() - 1;
        BezierPoint previousPoint = new BezierPoint(Coordinate.valueOf(0),
                                                    Coordinate.valueOf(0));
        for (int i = n; i > n / 2; i--) {
            final Coordinate factor = Coordinate.valueOf(n)
                                                .divide(Coordinate.valueOf(i));
            final BezierPoint currentPoint = controlPoints.get(i);

            final Coordinate x = factor.multiply(currentPoint.getX())
                                       .add(Coordinate.valueOf(1)
                                                      .subtract(factor)
                                                      .multiply(previousPoint.getX()));
            final Coordinate y = factor.multiply(currentPoint.getY())
                                       .add(Coordinate.valueOf(1)
                                                      .subtract(factor)
                                                      .multiply(previousPoint.getY()));

            final BezierPoint newPoint = new BezierPoint(x,
                                                         y);
            newControlPoints.add(newPoint);
            previousPoint = newPoint;
        }
        reverse(newControlPoints);
        return newControlPoints;
    }

    private void correctMiddlePoint(final List<BezierPoint> controlPoints,
                                    final List<BezierPoint> newControlPoints) {

        final int n = controlPoints.size() - 1;
        final int middleIndex = n / 2;
        final Coordinate factor = Coordinate.valueOf(n)
                                            .divide(Coordinate.valueOf(middleIndex)
                                                              .add(Coordinate.valueOf(1)));

        final Coordinate x = factor.multiply(controlPoints.get(middleIndex + 1)
                                                          .getX())
                                   .add(Coordinate.valueOf(1)
                                                  .subtract(factor)
                                                  .multiply(newControlPoints.get((n + 1) / 2)
                                                                            .getX()));
        final Coordinate y = factor.multiply(controlPoints.get(middleIndex + 1)
                                                          .getY())
                                   .add(Coordinate.valueOf(1)
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

        final Coordinate x = point1.getX()
                                   .add(point2.getX())
                                   .divide(Coordinate.valueOf(2));
        final Coordinate y = point1.getY()
                                   .add(point2.getY())
                                   .divide(Coordinate.valueOf(2));
        return new BezierPoint(x,
                               y);
    }
}