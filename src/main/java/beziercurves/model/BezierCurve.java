package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.util.List;

public class BezierCurve {

    private List<BezierPoint> controlPoints;
    private Color color;

    public BezierCurve(final List<BezierPoint> controlPoints,
                       final Color color) {

        setControlPoints(controlPoints);
        setColor(color);
    }

    public List<BezierPoint> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(final List<BezierPoint> controlPoints) {
        assertNotNull(controlPoints);
        this.controlPoints = controlPoints;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        assertNotNull(color);
        this.color = color;
    }
}