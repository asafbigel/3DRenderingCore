package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * a 3d geometric shape sphere.
 * represented with primitive objects point for canter and radius.
 */
public class Sphere implements Geometry {
    Point center;
    double radius;

    /**
     * ctor.
     *
     * @param center-          primitive object point.
     * @param radius-primitive value double.
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter function for center field.
     *
     * @return this sphere center point (Point).
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius field.
     *
     * @return this plane radius (double).
     */
    public double getRadius() {
        return radius;
    }

    /**
     * to print a formated string representing a sphere using its center and radius.
     *
     * @return formated string representation of a sphere.
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * yet to be implemented. function return the normal vector to this sphere at a specific point.
     *
     * @param p-the point.
     * @return the normal to this sphere at this point.
     */
    public Vector getNormal(Point p) {

        return p.subtract(this.center).normalize();
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        try {
            Vector u = center.subtract(ray.getP0());
            double tm = ray.getDir().dotProduct(u);

            double d = Math.sqrt(u.lengthSquared() - (tm * tm));
            if (d >= radius)
                return null;
            double th = Math.sqrt((radius * radius) - (d * d));
            if (tm + th <= 0)
                return null;
            LinkedList<Point> l1 = new LinkedList<>();
            l1.add(ray.getPoint(tm + th));
            //l1.add(ray.getP0().add(ray.getDir().scale(tm + th)));
            if (tm > th)
                l1.add(ray.getPoint(tm - th));
                //l1.add(ray.getP0().add(ray.getDir().scale(tm - th)));
            return l1;
        } catch (IllegalArgumentException e) {
            LinkedList<Point> l1 = new LinkedList<>();
            l1.add(center.add(ray.getDir().scale(radius)));
            return l1;
        }
    }
}
