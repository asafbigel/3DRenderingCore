package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
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

    public List<Intersectable> getShapes() {
        return shapes;
    }

    /**
     * @param geometries The geometries
     * @return
     */
    public Geometries add(Intersectable... geometries){
        shapes.addAll(List.of(geometries));
        return this;
    }
    public Geometries addAll(Geometries geometries){
        shapes.addAll(geometries.getShapes());
        return this;
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> l = new ArrayList<>();
        List<Point> newL=new ArrayList<>();
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
        if (l.size()==0)
            return null;
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
    //TODO
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;
        //for (Geometry geometry : Scene.geometries) {
        for (Intersectable shape:shapes) {
            var geoIntersections = shape.findGeoIntersections(ray,maxDistance);
            //we must operate like this, because we cannot add a null in case there is no intersections.
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
