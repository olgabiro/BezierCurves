package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.util.List;

public class BezierCurve {

    private List<BezierPoint> points;
    private Color color;

    public BezierCurve(final List<BezierPoint> points,
                       final Color color) {

        setPoints(points);
        setColor(color);
    }

    public List<BezierPoint> getPoints() {
        return points;
    }

    public void setPoints(final List<BezierPoint> points) {
        assertNotNull(points);
        this.points = points;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        assertNotNull(color);
        this.color = color;
    }
}