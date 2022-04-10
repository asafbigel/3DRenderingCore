package renderer;

import primitives.*;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * ctor for RayTracerBase class.
     * @param scene the scene.
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * public method for returning the color of first
     * intersection point with ray r.
     * @param r ray that we cast (from camera object, see camera implementation.)
     * @return Color instance with point rgb color values (type of Color witch contains double3 type.).
     */
    public abstract Color traceRay(Ray r);
}
