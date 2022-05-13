package beziercurves.model;

import static beziercurves.model.ColorHelper.assertValidChannelValue;

import java.util.Objects;

public class Color {

    private int redChannel;
    private int greenChannel;
    private int blueChannel;

    public Color(final int redChannel,
                 final int greenChannel,
                 final int blueChannel) {

        setRedChannel(redChannel);
        setGreenChannel(greenChannel);
        setBlueChannel(blueChannel);
    }

    public int getRedChannel() {
        return redChannel;
    }

    public void setRedChannel(final int redChannel) {
        assertValidChannelValue(redChannel);
        this.redChannel = redChannel;
    }

    public int getGreenChannel() {
        return greenChannel;
    }

    public void setGreenChannel(final int greenChannel) {
        assertValidChannelValue(greenChannel);
        this.greenChannel = greenChannel;
    }

    public int getBlueChannel() {
        return blueChannel;
    }

    public void setBlueChannel(final int blueChannel) {
        assertValidChannelValue(blueChannel);
        this.blueChannel = blueChannel;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Color color = (Color) o;
        return redChannel == color.redChannel && greenChannel == color.greenChannel && blueChannel == color.blueChannel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redChannel,
                            greenChannel,
                            blueChannel);
    }
}