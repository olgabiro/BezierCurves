package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;
import static beziercurves.model.ColorHelper.COLOR_CHANNEL_MAX_VALUE;
import static beziercurves.model.ColorHelper.COLOR_CHANNEL_MIN_VALUE;
import static beziercurves.model.TestHelper.getRandomIntBetween;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTestFixture {

    public Color create() {
        return new Color(getRandomChannelValue(),
                         getRandomChannelValue(),
                         getRandomChannelValue());
    }

    public void assertPropertiesEqual(final Color actual,
                                      final int expectedRedChannel,
                                      final int expectedGreenChannel,
                                      final int expectedBlueChannel) {

        assertNotNull(actual);
        assertAll(() -> assertEquals(expectedRedChannel,
                                     actual.getRedChannel()),
                  () -> assertEquals(expectedGreenChannel,
                                     actual.getGreenChannel()),
                  () -> assertEquals(expectedBlueChannel,
                                     actual.getBlueChannel()));
    }

    public int getRandomChannelValue() {
        return getRandomIntBetween(COLOR_CHANNEL_MIN_VALUE,
                                   COLOR_CHANNEL_MAX_VALUE);
    }
}