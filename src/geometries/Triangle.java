package geometries;

import org.junit.jupiter.engine.descriptor.TestInstanceLifecycleUtils;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.io.PipedInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * a 3d geometric shape,
 */
public class Triangle extends Polygon {
    /**
     * ctor, gets 3 primitive points and uses polygon ctor to initialize the fields.
     *
     * @param vertices-3 primitive points.
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    /**
     * to print a formated strign representing a triangle.
     *
     * @return-formated string representing a triangle.
     */
    @Override
    public String toString() {
        return "Triangle{} " + super.toString();
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> l1 = new Plane(this.vertices.get(0),this.getNormal(this.vertices.get(0))).findIntersections(ray);
        if (l1 == null) //case1: ray in parallel to triangle plane.
            return null;
        Vector v1=this.vertices.get(0).subtract(ray.getP0());
        Vector v2=this.vertices.get(1).subtract(ray.getP0());
        Vector v3=this.vertices.get(2).subtract(ray.getP0());
        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();
        if (ray.getDir().dotProduct(n1)>0&&ray.getDir().dotProduct(n2)>0&&ray.getDir().dotProduct(n3)>0)
            return l1;
        else if (ray.getDir().dotProduct(n1)<0&&ray.getDir().dotProduct(n2)<0&&ray.getDir().dotProduct(n3)<0)
            return l1;
        return null;
    }

    public List<GeoPoint> findGeoIntersections(Ray ray){
        List<GeoPoint> l1 = new Plane(this.vertices.get(0),this.getNormal(this.vertices.get(0))).findGeoIntersections(ray);
        if (l1 == null) //case1: ray in parallel to triangle plane.
            return null;
        Vector v1=this.vertices.get(0).subtract(ray.getP0());
        Vector v2=this.vertices.get(1).subtract(ray.getP0());
        Vector v3=this.vertices.get(2).subtract(ray.getP0());
        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();
        if (ray.getDir().dotProduct(n1)>0&&ray.getDir().dotProduct(n2)>0&&ray.getDir().dotProduct(n3)>0)
            return l1;
        else if (ray.getDir().dotProduct(n1)<0&&ray.getDir().dotProduct(n2)<0&&ray.getDir().dotProduct(n3)<0)
            return l1;
        return null;
    }
}
