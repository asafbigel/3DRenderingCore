package lighting;

import primitives.*;

public class AmbientLight extends Light {

    /**
     * ctor for AmbientLight class, notice that this ctor get also the reduction factor,
     * reducing the color of three parameters of rgb.
     * now this class extends Light class, so we send super ctor a reduced intensity.
     * @param intensity of the ambient light.
     * @param d reduction factor, must be <=1.
     */
    public AmbientLight(Color intensity, Double3 d) {
       super(intensity.scale(d));
    }

    /**
     * default ctor setting color to be black.
     * if there is no light then the color of objects is black.
     * nnotice that we use parent ctor.
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
