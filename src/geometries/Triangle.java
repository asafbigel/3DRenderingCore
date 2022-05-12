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
     * to print a formatted string representing a triangle.
     *
     * @return formatted string representing a triangle.
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

    /**
     * NOTICE: triangle dont have findGeointersectionsHelper function, for it is in plane class.
     * we only find intersection with triangle plane and then in this function check if point is in triangle or not.
     * @param ray the ray we cast (can be primary ray (camera) or secondary ray (shadow,reflection,or refraction).
     * @param maxDistance the max distance of the intersection, an intersection point farther than this distance won't be included,
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance){
        /*create a new plane first and sets its emission light with this triangle
        emission light (so they would have the same color.),
        and then finding its geopoint with findGeointersection function (of that plane).
        */
        List<GeoPoint> l1 = new Plane(this.vertices.get(0),this.getNormal(this.vertices.get(0))).
                setEmission(this.getEmission()).setMaterial(this.getMaterial() ).findGeoIntersections(ray,maxDistance);
        if (l1 == null) //case1: ray in parallel to triangle plane.
            return null;
        /*after that we check the signs of all constructed vectors to see rather there
        is an intersection in triangle or not.
        */
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
