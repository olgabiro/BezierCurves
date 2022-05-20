package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertHasMinSize;
import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.util.List;

public class BezierCurve {

    private static final int CONTROL_POINTS_MIN_SIZE = 2;

    private List<BezierPoint> controlPoints;
    private Color color;

    public BezierCurve(final List<BezierPoint> controlPoints,
                       final Color color) {

        setControlPoints(controlPoints);
        setColor(color);
    }

    public List<BezierPoint> getControlPoints() {
        return this.controlPoints;
    }

    public void setControlPoints(final List<BezierPoint> controlPoints) {
        this.controlPoints = assertHasMinSize(controlPoints,
                                              CONTROL_POINTS_MIN_SIZE);
    }

    public boolean canDegreeBeLowered() {
        return this.controlPoints.size() > CONTROL_POINTS_MIN_SIZE;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(final Color color) {
        assertNotNull(color);
        this.color = color;
    }
}