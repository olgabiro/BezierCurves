package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CoordinateTest {

    @Test
    void add_throwsIllegalArgumentException_whenCoordinateIsNull() {
        final Coordinate coordinate = Coordinate.valueOf(1);
        assertThrows(IllegalArgumentException.class,
                     () -> coordinate.add(null));
    }

    @Test
    void add_returnsExpectedResult() {
        final Coordinate coordinate1 = Coordinate.valueOf(1);
        final Coordinate coordinate2 = Coordinate.valueOf(2);
        final Coordinate expected = Coordinate.valueOf(3);

        final Coordinate actual = coordinate1.add(coordinate2);
        assertAll(() -> assertEquals(Coordinate.valueOf(1),
                                     coordinate1,
                                     "The operand value should not be changed."),
                  () -> assertEquals(Coordinate.valueOf(2),
                                     coordinate2,
                                     "The operand value should not be changed."),
                  () -> assertEquals(expected,
                                     actual));
    }

    @Test
    void subtract_throwsIllegalArgumentException_whenCoordinateIsNull() {
        final Coordinate coordinate = Coordinate.valueOf(1);
        assertThrows(IllegalArgumentException.class,
                     () -> coordinate.subtract(null));
    }

    @Test
    void subtract_returnsExpectedResult() {
        final Coordinate coordinate1 = Coordinate.valueOf(1);
        final Coordinate coordinate2 = Coordinate.valueOf(2);
        final Coordinate expected = Coordinate.valueOf(-1);

        final Coordinate actual = coordinate1.subtract(coordinate2);
        assertAll(() -> assertEquals(Coordinate.valueOf(1),
                                     coordinate1,
                                     "The operand value should not be changed."),
                  () -> assertEquals(Coordinate.valueOf(2),
                                     coordinate2,
                                     "The operand value should not be changed."),
                  () -> assertEquals(expected,
                                     actual));
    }

    @Test
    void multiply_throwsIllegalArgumentException_whenCoordinateIsNull() {
        final Coordinate coordinate = Coordinate.valueOf(1);
        assertThrows(IllegalArgumentException.class,
                     () -> coordinate.multiply(null));
    }

    @Test
    void multiply_returnsExpectedResult() {
        final Coordinate coordinate1 = Coordinate.valueOf(2);
        final Coordinate coordinate2 = Coordinate.valueOf(3);
        final Coordinate expected = Coordinate.valueOf(6);

        final Coordinate actual = coordinate1.multiply(coordinate2);
        assertAll(() -> assertEquals(Coordinate.valueOf(2),
                                     coordinate1,
                                     "The operand value should not be changed."),
                  () -> assertEquals(Coordinate.valueOf(3),
                                     coordinate2,
                                     "The operand value should not be changed."),
                  () -> assertEquals(expected,
                                     actual));
    }

    @Test
    void divide_throwsIllegalArgumentException_whenCoordinateIsNull() {
        final Coordinate coordinate = Coordinate.valueOf(1);
        assertThrows(IllegalArgumentException.class,
                     () -> coordinate.divide(null));
    }

    @Test
    void divide_returnsExpectedResult() {
        final Coordinate coordinate1 = Coordinate.valueOf(1);
        final Coordinate coordinate2 = Coordinate.valueOf(2);
        final Coordinate expected = Coordinate.valueOf(0.5);

        final Coordinate actual = coordinate1.divide(coordinate2);
        assertAll(() -> assertEquals(Coordinate.valueOf(1),
                                     coordinate1,
                                     "The operand value should not be changed."),
                  () -> assertEquals(Coordinate.valueOf(2),
                                     coordinate2,
                                     "The operand value should not be changed."),
                  () -> assertEquals(expected,
                                     actual));
    }
}