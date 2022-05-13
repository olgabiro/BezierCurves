package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class BezierPoint {

    private BigDecimal x;
    private BigDecimal y;
    private Optional<BigDecimal> weight = empty();

    public BezierPoint(final BigDecimal x,
                       final BigDecimal y) {

        setX(x);
        setY(y);
    }

    public BezierPoint(final BigDecimal x,
                       final BigDecimal y,
                       final BigDecimal weight) {

        setX(x);
        setY(y);
        setWeight(of(assertNotNull(weight)));
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

    public Optional<BigDecimal> getWeight() {
        return weight;
    }

    private void setWeight(final Optional<BigDecimal> weight) {
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
        return Objects.equals(x,
                              that.x)
               && Objects.equals(y,
                                 that.y)
               && Objects.equals(weight,
                                 that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,
                            y,
                            weight);
    }
}