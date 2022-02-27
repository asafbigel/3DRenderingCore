package geometries;

import primitives.*;

/**
 * 3d shape, it is a cylinder with infinite height.
 */
public class Tube implements Geometry {
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
     * getters for the object fields.
     *
     * @return
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * to print a formated string representing a tube.
     *
     * @return-formated string representation of a tube.
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
     * @return-a new primitive object vector.
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
