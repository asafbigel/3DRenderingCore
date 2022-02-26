package geometries;
import primitives.*;

/**
 * a 3d geometric shape sphere.
 * represented with primitive objects point for canter and radius.
 */
public class Sphere implements Geometry{
    Point center;
    double radius;

    /**
     * ctor.
     * @param center- primitive object point.
     * @param radius-primitive value double.
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter function for center field.
     * @return- this sphere center point (Point).
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter function for radius field.
     * @return- this plane radius (double).
     */
    public double getRadius() {
        return radius;
    }

    /**
     * to print a formated string representing a sphere using its center and radius.
     * @return-formated string representation of a sphere.
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
     * @param p-the point.
     * @return-the normal to this sphere at this point.
     */
    public Vector getNormal(Point p) {
        return null;
    }
}
