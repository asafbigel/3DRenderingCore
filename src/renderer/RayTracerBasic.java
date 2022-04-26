package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;

public class RayTracerBasic extends RayTracerBase{
    /**
     * ctor for RayTracerBasic class, this class extends RayTracerBase class witch contains the
     * scene parameter.
     * @param scene this parameter appears in father class.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * public method for returning the color of first
     * intersection point with Ray r,this ray is cast from camera instance towards the view plane.
     * and intersects with the 3d objects.
     * @param r ray that we cast (from the camera instance).
     * @return Color instance with point rgb color values (represented in a double 3 type).
     */
    @Override
    public Color traceRay(Ray r) {
        /*
       Point intersection=r.findClosestPoint(scene.geometries.findIntersections(r));
       if (intersection==null)
           return scene.background;
       return calcColor(intersection);
         */
        //create list of all intersection point with r.
        var intersections = scene.geometries.findGeoIntersections(r);
        //if there is no intersection then we see background color.
        if (intersections == null) return scene.background;
        //if we do have intersections, we only see the closest intersection point.
        GeoPoint closestPoint = r.findClosestGeoPoint(intersections);
        //now we want to add color to point, so we use Geopoint.
        return calcColor(closestPoint);
        //calColor function is below.

    }

    /**
     * for now return the unified color of point from Ambient light class function
     * that uses light intensity.
     * @param p Point of intersection (observable from camera).
     * @return Color type of this Point color.
     */
    private Color calcColor(Point p){
        return scene.ambientLight.getIntensity();
    }
    private Color calcColor(GeoPoint gp) {
        //now we add the emission light, wich is the light that scatter from shape surface.
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission());
    }

}
