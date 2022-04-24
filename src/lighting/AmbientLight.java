package lighting;

import primitives.*;

public class AmbientLight {
    /**
     * parameters are:
     * Color intensity- this parameter tells us the ambient light intensity, this will
     * set the unified color for objects.
     */
    Color intensity;

    /**
     * ctor for AmbientLight class, notice that this ctor get also the reduction factor,
     * reducing the color of three parameters of rgb.
     * @param intensity of the ambient light.
     * @param d reduction factor, must be >1.
     */
    public AmbientLight(Color intensity, Double3 d) {
       this.intensity=intensity.scale(d);
    }

    /**
     * default ctor setting color to be black.
     * if there is no light then the color of objects is black.
     */
    public AmbientLight() {
        intensity=Color.BLACK;
    }

    /**
     * getter for AmbientLight intensity.
     * @return intensity parameter of type Color.
     */
    public Color getIntensity(){
        return intensity;
    }
}
