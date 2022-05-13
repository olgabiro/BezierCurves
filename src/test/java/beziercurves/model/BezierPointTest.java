package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BezierPointTest {

    private final BezierPointTestFixture testFixture = new BezierPointTestFixture();

    @Test
    void constructor_throwsIllegalArgumentException_whenXIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierPoint(null,
                                           this.testFixture.getRandomY(),
                                           this.testFixture.getRandomWeight()));
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenYIsNull() {
        assertThrows(IllegalArgumentException.class,
                     () -> new BezierPoint(this.testFixture.getRandomX(),
                                           null,
                                           this.testFixture.getRandomWeight()));
    }

    @Test
    void constructor_setsProperties() {
        final BigDecimal randomX = this.testFixture.getRandomX();
        final BigDecimal randomY = this.testFixture.getRandomY();
        final int randomWeight = this.testFixture.getRandomWeight();

        final BezierPoint actual = new BezierPoint(randomX,
                                                   randomY,
                                                   randomWeight);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomX,
                                               randomY,
                                               randomWeight);
    }
}