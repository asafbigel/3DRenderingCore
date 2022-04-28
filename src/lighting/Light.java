package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;

    /**
     * Light class ctor.
     * @param intensity Color type, distinguish light intensity, contains double3 type
     *                  and reduction factor.
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity filed,
     * @return Color type instance of intensity.
     */
    public Color getIntensity() {
        return intensity;
    }
}
