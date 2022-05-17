package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

public class BezierPoint {

    static final int WEIGHT_SCALE = 3;
    static final RoundingMode WEIGHT_ROUNDING_MODE = RoundingMode.HALF_UP;
    static final BigDecimal DEFAULT_WEIGHT = BigDecimal.ONE.setScale(WEIGHT_SCALE,
                                                                     WEIGHT_ROUNDING_MODE);

    private Coordinate x;
    private Coordinate y;
    private Optional<BigDecimal> weight = empty();

    public BezierPoint(final Coordinate x,
                       final Coordinate y) {

        setX(x);
        setY(y);
    }

    public BezierPoint(final Coordinate x,
                       final Coordinate y,
                       final BigDecimal weight) {

        setX(x);
        setY(y);
        setWeight(of(assertNotNull(weight)));
    }

    public Coordinate getX() {
        return this.x;
    }

    private void setX(final Coordinate x) {
        this.x = assertNotNull(x);
    }

    public Coordinate getY() {
        return this.y;
    }

    private void setY(final Coordinate y) {
        this.y = assertNotNull(y);
    }

    public Optional<BigDecimal> getWeight() {
        return this.weight;
    }

    private void setWeight(final Optional<BigDecimal> weight) {
        this.weight = weight.map(value -> value.setScale(WEIGHT_SCALE,
                                                         WEIGHT_ROUNDING_MODE));
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
        return Objects.equals(this.x,
                              that.x)
               && Objects.equals(this.y,
                                 that.y)
               && Objects.equals(this.weight,
                                 that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x,
                            this.y,
                            this.weight);
    }

    @Override
    public String toString() {
        return "BezierPoint{" +
               "x=" + this.x +
               ", y=" + this.y +
               ", weight=" + this.weight.orElse(DEFAULT_WEIGHT) +
               '}';
    }
}