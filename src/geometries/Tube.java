package geometries;

import primitives.*;

import java.util.List;

/**
 * 3d shape, it is a cylinder with infinite height.
 */
public class Tube extends Geometry {
    Ray axisRay;
    double radius;

    /**
     * ctor, initiates all field.
     *
     * @param axisRay-the ray in the center of the tube.
     * @param radius-of   the circle created by the tube.
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * get axis ray
     *
     * @return axis ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }
    /**
     * get radius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * to print a formated string representing a tube.
     *
     * @return formatted string representation of a tube.
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * yet to be implemented, function returns the normal vector to the tube at a specific point.
     *
     * @param p-the point on the tube.
     * @return a new primitive object vector.
     */
    @Override
    public Vector getNormal(Point p) {

        double t=this.axisRay.getDir().dotProduct(p.subtract(this.axisRay.getP0()));
        //Point O=this.axisRay.getP0().add(axisRay.getDir().scale(t));
        Point O =this.axisRay.getP0();
        if (t != 0){
            O = O.add(axisRay.getDir().scale(t));
        }
        return p.subtract(O).normalize();
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
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
        return null;
    }
}
