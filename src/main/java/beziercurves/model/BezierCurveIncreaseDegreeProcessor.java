package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.util.ArrayList;
import java.util.List;

class BezierCurveIncreaseDegreeProcessor {

    BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final List<BezierPoint> controlPoints = curve.getControlPoints();

        newControlPoints.add(controlPoints.get(0));

        for (int k = 1; k < controlPoints.size(); k++) {
            newControlPoints.add(calculateMiddlePoint(controlPoints.get(k - 1),
                                                      controlPoints.get(k),
                                                      controlPoints.size(),
                                                      k));
        }

        newControlPoints.add(controlPoints.get(controlPoints.size() - 1));

        curve.setControlPoints(newControlPoints);
        return curve;
    }

    private BezierPoint calculateMiddlePoint(final BezierPoint previousPoint,
                                             final BezierPoint currentPoint,
                                             final int degree,
                                             final int currentIndex) {

        final Coordinate factor = Coordinate.valueOf(currentIndex)
                                            .divide(Coordinate.valueOf(degree));
        final Coordinate x = factor.multiply(previousPoint.getX())
                                   .add(Coordinate.valueOf(1)
                                                  .subtract(factor)
                                                  .multiply(currentPoint.getX()));
        final Coordinate y = factor.multiply(previousPoint.getY())
                                   .add(Coordinate.valueOf(1)
                                                  .subtract(factor)
                                                  .multiply(currentPoint.getY()));
        return new BezierPoint(x,
                               y);
    }
}