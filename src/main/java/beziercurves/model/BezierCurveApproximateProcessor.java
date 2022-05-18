package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import beziercurves.Tools;

class BezierCurveApproximateProcessor {

    public BezierCurve approximate(final BezierCurve curve) {
        assertNotNull(curve);

        final List<BezierPoint> newControlPoints = new ArrayList<>();
        final List<BezierPoint> controlPoints = curve.getControlPoints();

        final BezierPoint alpha = calculateAlphaFactor(controlPoints);
        final int degree = controlPoints.size();
        final int n = degree - 1;
        final List<BezierPoint> beta = calculateBetaFactors(controlPoints,
                                                            alpha);

        final Coordinate[] w = new Coordinate[degree];
        final Coordinate[] z = new Coordinate[degree];

        for (int i = 0; i < degree; i++) {
            w[i] = controlPoints.get(i)
                                .getX()
                                .subtract(beta.get(i)
                                              .getX());
            z[i] = controlPoints.get(i)
                                .getY()
                                .subtract(beta.get(i)
                                              .getY());
        }

        newControlPoints.add(new BezierPoint(w[0],
                                             z[0]));

        for (int k = 1; k < n; k++) {
            final Coordinate factor = Coordinate.valueOf(k)
                                                .divide(Coordinate.valueOf(n - k));
            final Coordinate x = factor.add(Coordinate.valueOf(1))
                                       .multiply(w[k])
                                       .subtract(factor.multiply(newControlPoints.get(k - 1)
                                                                                 .getX()));

            final Coordinate y = factor.add(Coordinate.valueOf(1))
                                       .multiply(z[k])
                                       .subtract(factor.multiply(newControlPoints.get(k - 1)
                                                                                 .getY()));

            newControlPoints.add(new BezierPoint(x,
                                                 y));
        }

        curve.setControlPoints(newControlPoints);
        return curve;
    }

    private BezierPoint calculateAlphaFactor(final List<BezierPoint> controlPoints) {
        Coordinate x = Coordinate.valueOf(0);
        Coordinate y = Coordinate.valueOf(0);
        final Coordinate f1 = Coordinate.valueOf(Tools.power(2,
                                                             2 * controlPoints.size() - 2));

        final int n = controlPoints.size() - 1;
        for (int i = 0; i <= n; i++) {
            final Coordinate factor = Coordinate.valueOf(Tools.newtonSymbol(n,
                                                                            i));
            if ((i + n) % 2 != 0) {
                x = x.subtract(controlPoints.get(i)
                                            .getX()
                                            .multiply(factor));
                y = y.subtract(controlPoints.get(i)
                                            .getY()
                                            .multiply(factor));

            } else {
                x = x.add(controlPoints.get(i)
                                       .getX()
                                       .multiply(factor));
                y = y.add(controlPoints.get(i)
                                       .getY()
                                       .multiply(factor));
            }
        }

        return new BezierPoint(x.divide(f1),
                               y.divide(f1));
    }

    private List<BezierPoint> calculateBetaFactors(final List<BezierPoint> controlPoints,
                                                   final BezierPoint alpha) {

        final List<BezierPoint> beta = new ArrayList<>();
        final int n = controlPoints.size() - 1;
        for (int i = 0; i <= n; i++) {
            final Coordinate f2 = Coordinate.valueOf(Tools.newtonSymbol(2 * n,
                                                                        2 * i) / Tools.newtonSymbol(n,
                                                                                                    i));
            final Coordinate f = Coordinate.valueOf(Tools.power(2,
                                                                2 * n - 1));

            Coordinate x = controlPoints.get(i)
                                        .getX()
                                        .multiply(alpha.getX())
                                        .multiply(f2)
                                        .divide(f);
            Coordinate y = controlPoints.get(i)
                                        .getY()
                                        .multiply(alpha.getY())
                                        .multiply(f2)
                                        .divide(f);
            if (((n + i) % 2) != 0) {
                x = x.multiply(Coordinate.valueOf(-1));
                y = y.multiply(Coordinate.valueOf(-1));
            }
            beta.add(new BezierPoint(x,
                                     y));
        }
        return beta;
    }
}
