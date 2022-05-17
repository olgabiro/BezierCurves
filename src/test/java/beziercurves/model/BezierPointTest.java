package beziercurves.model;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BezierPointTest {

    private final BezierPointTestFixture testFixture = new BezierPointTestFixture();

    @Test
    void constructor_throwsIllegalArgumentException_whenXIsNull() {
        assertAll(() -> assertThrows(IllegalArgumentException.class,
                                     () -> new BezierPoint(null,
                                                           this.testFixture.getRandomY(),
                                                           this.testFixture.getRandomWeight())),
                  () -> assertThrows(IllegalArgumentException.class,
                                     () -> new BezierPoint(null,
                                                           this.testFixture.getRandomY())));
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenYIsNull() {
        assertAll(() -> assertThrows(IllegalArgumentException.class,
                                     () -> new BezierPoint(this.testFixture.getRandomX(),
                                                           null,
                                                           this.testFixture.getRandomWeight())),
                  () -> assertThrows(IllegalArgumentException.class,
                                     () -> new BezierPoint(this.testFixture.getRandomX(),
                                                           null)));
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenWeightIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierPoint(this.testFixture.getRandomX(),
                                           this.testFixture.getRandomY(),
                                           null));
    }

    @Test
    void constructor_setsProperties() {
        final Coordinate randomX = this.testFixture.getRandomX();
        final Coordinate randomY = this.testFixture.getRandomY();

        final BezierPoint actual = new BezierPoint(randomX,
                                                   randomY);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomX,
                                               randomY,
                                               empty());
    }

    @Test
    void constructor_setsProperties_whenWeightProvided() {
        final Coordinate randomX = this.testFixture.getRandomX();
        final Coordinate randomY = this.testFixture.getRandomY();
        final BigDecimal randomWeight = this.testFixture.getRandomWeight();

        final BezierPoint actual = new BezierPoint(randomX,
                                                   randomY,
                                                   randomWeight);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomX,
                                               randomY,
                                               of(randomWeight));
    }
}