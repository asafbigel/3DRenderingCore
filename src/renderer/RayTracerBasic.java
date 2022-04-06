package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * public method for returning the color of first
     * intersection point with r.
     *
     * @param r ray that we cast.
     * @return Color instance with point rgb color values.
     */
    @Override
    public Color traceRay(Ray r) {
        return null;
    }
}
