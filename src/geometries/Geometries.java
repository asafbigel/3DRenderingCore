package geometries;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable{
    List<Intersectable> shapes;

   public Geometries(){
        shapes=new LinkedList<>();
        //we choose linked list because the complexity of adding and deleting an object is better than array list.
    }

    /**
     * @param geometries The geometries
     */
    public Geometries(Intersectable... geometries){
        shapes= List.of(geometries);
    }


    /**
     * @param geometries The geometries
     */
    public void add(Intersectable... geometries){
        shapes.addAll(List.of(geometries));
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> l = null;
        List<Point> newL;
        for (Intersectable shape:shapes) {
            newL=shape.findIntersections(ray);
            // case there are intersections with this shape
            if (newL != null){
                if (l== null)
                    l = newL;
                else
                    l.addAll(newL);
            }
        }
        return l;
    }

    /**
     * each subclass of this intersectable will implement this part of
     * nvi function above.
     *
     * @param ray Ray of intersection. (a cast ray)
     * @return List of all Geopoint intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        //for (Geometry geometry : Scene.geometries) {
        for (Intersectable shape:shapes) {
            var geoIntersections = shape.findGeoIntersections(ray);
            if (geoIntersections != null){
                if (intersections == null)
                    intersections = geoIntersections;
                else
                    intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}
