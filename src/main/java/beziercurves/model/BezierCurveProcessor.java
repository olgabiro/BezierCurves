package beziercurves.model;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BezierCurveProcessor {

    public BezierCurve increaseDegree(final BezierCurve curve) {
        List<BezierPoint> newControlPoints = new ArrayList<>();
        final List<BezierPoint> controlPoints = curve.getControlPoints();
        final BigDecimal degree = valueOf(controlPoints.size());

        newControlPoints.add(controlPoints.get(0));

        for (int k = 1; k < controlPoints.size(); k++) {
            newControlPoints.add(calculateMiddlePoint(controlPoints.get(k - 1),
                                                      controlPoints.get(k),
                                                      degree,
                                                      valueOf(k)));
        }

        newControlPoints.add(controlPoints.get(controlPoints.size() - 1));

        curve.setControlPoints(newControlPoints);
        return curve;
    }

    private BezierPoint calculateMiddlePoint(final BezierPoint previousPoint,
                                             final BezierPoint currentPoint,
                                             final BigDecimal degree,
                                             final BigDecimal currentIndex) {
        BigDecimal factor = currentIndex.divide(degree,
                                                HALF_UP);
        BigDecimal x = factor.multiply(previousPoint.getX())
                             .add(ONE.subtract(factor)
                                     .multiply(currentPoint.getX()));
        BigDecimal y = factor.multiply(previousPoint.getY())
                             .add(ONE.subtract(factor)
                                     .multiply(currentPoint.getY()));
        return new BezierPoint(x,
                               y);
    }
}