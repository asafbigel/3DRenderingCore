package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
       Point intersection=  r.findClosestPoint(scene.geometries.findIntersections(r));
       if (intersection==null)
           return scene.background;
       return calcColor(intersection);
    }

    private Color calcColor(Point p){
        return scene.ambientLight.getIntensity();
    }

}
