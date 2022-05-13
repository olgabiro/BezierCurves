package beziercurves.model;

import java.util.List;

public class BezierCurve {

    private List<BezierPoint> points;
    private Color color;

    public BezierCurve(final List<BezierPoint> points,
                       final Color color) {

        this.points = points;
        this.color = color;
    }

    public List<BezierPoint> getPoints() {
        return points;
    }

    public void setPoints(final List<BezierPoint> points) {
        this.points = points;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }
}