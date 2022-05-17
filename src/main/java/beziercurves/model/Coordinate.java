package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Coordinate {

    static final int COORDINATE_SCALE = 3;
    static final RoundingMode COORDINATE_ROUNDING_MODE = RoundingMode.HALF_UP;

    public static Coordinate valueOf(final int value) {
        return new Coordinate(BigDecimal.valueOf(value));
    }

    public static Coordinate valueOf(final double value) {
        return new Coordinate(BigDecimal.valueOf(value));
    }

    private final BigDecimal value;

    private Coordinate(final BigDecimal value) {
        this.value = assertNotNull(value).setScale(COORDINATE_SCALE,
                                                   COORDINATE_ROUNDING_MODE);
    }

    public Coordinate add(final Coordinate coordinate) {
        assertNotNull(coordinate);
        return new Coordinate(this.value.add(coordinate.value));
    }

    public Coordinate subtract(final Coordinate coordinate) {
        assertNotNull(coordinate);
        return new Coordinate(this.value.subtract(coordinate.value));
    }

    public Coordinate multiply(final Coordinate coordinate) {
        assertNotNull(coordinate);
        return new Coordinate(this.value.multiply(coordinate.value));
    }

    public Coordinate divide(final Coordinate coordinate) {
        assertNotNull(coordinate);
        return new Coordinate(this.value.divide(coordinate.value,
                                                COORDINATE_ROUNDING_MODE));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coordinate that = (Coordinate) o;
        return Objects.equals(this.value,
                              that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}