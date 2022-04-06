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
     * intersection point with r.
     * @param r ray that we cast.
     * @return Color instance with point rgb color values.
     */
    public abstract Color traceRay(Ray r);
}
