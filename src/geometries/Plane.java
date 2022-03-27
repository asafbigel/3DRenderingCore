package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * a geometric type, representing an infinite 2d plane.
 * using a primitive point and vector (direction).
 */
public class Plane implements Geometry {
    Point q0;
    Vector normal;

    /**
     * yet to be implemented, function returns the normal vector at a specific point on the plane.
     * notice:point must be on the plane.
     *
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {

        return getNormal();
    }

    /**
     * yet to be implemented, function return the normal vector to this plane at any point.
     *
     * @return
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * ctor, at this point initiate a random point to the attribution point for this plane.
     * normal vector is yet to be calculated, will be.
     *
     * @param q1
     * @param q2
     * @param q3
     */
    public Plane(Point q1, Point q2, Point q3) {
        this.q0 = q1;
        Vector v1 = q3.subtract(q2);
        Vector v2 = q3.subtract(q1);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * 2nd ctor, difine a plane with a point and a vector.
     * initialize fields as such.
     * notice that the vector is being normalized.
     *
     * @param q0-              primitive object point.
     * @param normal-primitive object vector.
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * geter for point field.
     *
     * @return
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * to print a formated representation of a plane, using point and vector field.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // case parallel
        if (getNormal().dotProduct(ray.getDir()) == 0){
            return null;
        }
        // try is to catch case that ray's point and Q0 is same (so we will get zero vector)
        try {
            double t = (getNormal().dotProduct(getQ0().subtract(ray.getP0()))) / (getNormal().dotProduct(ray.getDir()));
        if (t<=0)
            return null;
        LinkedList<Point> l1 = new LinkedList<Point>();
        l1.add(ray.getP0().add(ray.getDir().scale(t)));
        return l1;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}
