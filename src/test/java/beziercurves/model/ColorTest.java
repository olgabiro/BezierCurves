package beziercurves.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ColorTest {

    private static final int INVALID_CHANNEL_VALUE_TOO_SMALL = ColorHelper.COLOR_CHANNEL_MIN_VALUE - 1;
    private static final int INVALID_CHANNEL_VALUE_TOO_BIG = ColorHelper.COLOR_CHANNEL_MAX_VALUE + 1;

    private final ColorTestFixture testFixture = new ColorTestFixture();

    @Test
    void constructor_setsProperties() {
        final int randomRedChannelValue = this.testFixture.getRandomChannelValue();
        final int randomGreenChannelValue = this.testFixture.getRandomChannelValue();
        final int randomBlueChannelValue = this.testFixture.getRandomChannelValue();

        final Color actual = new Color(randomRedChannelValue,
                                       randomGreenChannelValue,
                                       randomBlueChannelValue);

        this.testFixture.assertPropertiesEqual(actual,
                                               randomRedChannelValue,
                                               randomGreenChannelValue,
                                               randomBlueChannelValue);
    }

    @Test
    void setRedChannel_throwsIllegalArgumentException_whenValueTooSmall() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setRedChannel(INVALID_CHANNEL_VALUE_TOO_SMALL));
    }

    @Test
    void setRedChannel_throwsIllegalArgumentException_whenValueTooBig() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setRedChannel(INVALID_CHANNEL_VALUE_TOO_BIG));
    }

    @Test
    void setRedChannel() {
        final Color color = this.testFixture.create();
        final int channelValue = this.testFixture.getRandomChannelValue();

        color.setRedChannel(channelValue);

        assertEquals(channelValue,
                     color.getRedChannel());
    }

    @Test
    void setGreenChannel_throwsIllegalArgumentException_whenValueTooSmall() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setGreenChannel(INVALID_CHANNEL_VALUE_TOO_SMALL));
    }

    @Test
    void setGreenChannel_throwsIllegalArgumentException_whenValueTooBig() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setGreenChannel(INVALID_CHANNEL_VALUE_TOO_BIG));
    }

    @Test
    void setGreenChannel() {
        final Color color = this.testFixture.create();
        final int channelValue = this.testFixture.getRandomChannelValue();

        color.setGreenChannel(channelValue);

        assertEquals(channelValue,
                     color.getGreenChannel());
    }

    @Test
    void setBlueChannel_throwsIllegalArgumentException_whenValueTooSmall() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setBlueChannel(INVALID_CHANNEL_VALUE_TOO_SMALL));
    }

    @Test
    void setBlueChannel_throwsIllegalArgumentException_whenValueTooBig() {
        final Color color = this.testFixture.create();

        assertThrows(IllegalArgumentException.class,
                     () -> color.setBlueChannel(INVALID_CHANNEL_VALUE_TOO_BIG));
    }

    @Test
    void setBlueChannel() {
        final Color color = this.testFixture.create();
        final int channelValue = this.testFixture.getRandomChannelValue();

        color.setBlueChannel(channelValue);

        assertEquals(channelValue,
                     color.getBlueChannel());
    }
}