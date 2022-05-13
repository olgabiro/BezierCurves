package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.math.BigDecimal;
import java.util.Objects;

public class BezierPoint {

    private BigDecimal x;
    private BigDecimal y;
    private int weight;

    public BezierPoint(final BigDecimal x,
                       final BigDecimal y,
                       final int weight) {

        setX(x);
        setY(y);
        setWeight(weight);
    }

    public BigDecimal getX() {
        return x;
    }

    private void setX(final BigDecimal x) {
        this.x = assertNotNull(x);
    }

    public BigDecimal getY() {
        return y;
    }

    private void setY(final BigDecimal y) {
        this.y = assertNotNull(y);
    }

    public int getWeight() {
        return weight;
    }

    private void setWeight(final int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BezierPoint that = (BezierPoint) o;
        return weight == that.weight
               && Objects.equals(x,
                                 that.x)
               && Objects.equals(y,
                                 that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,
                            y,
                            weight);
    }
}
