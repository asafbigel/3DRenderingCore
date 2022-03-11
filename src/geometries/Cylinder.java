package geometries;

import primitives.*;
import static primitives.Util.isZero;

/**
 * cylinder is a 3d geometric shape, composed out of primitives objects.
 * object implements Geometry interface, and inherits from tube.
 * (Cylinder is a tube with a constant height).
 */
public class Cylinder extends Tube {
    double height;

    /**
     * ctor.
     *
     * @param axisRay-type    Ray.
     * @param radius-double,  it is the radius of the base circle.
     * @param height-distance between circle bases.
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter for height field.
     * not all fields get a getter.
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * to print a format string representing a cylinder.
     *
     * @return- formated string of a cylinder.
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                "} ";
    }

    /**
     * yet to be implemented, function returns the normal vector of this cylinder at a specific point.
     *
     * @param p- the specific point.
     * @return-a primitive type new vector.
     */
    @Override
    public Vector getNormal(Point p) {

        if (isZero(p.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()))) {
            return axisRay.getDir().scale(-1);
        }
        Point p1=axisRay.getP0().add(axisRay.getDir().scale(height));

        if (isZero(p.subtract(p1).dotProduct(axisRay.getDir()))) {
            return axisRay.getDir();
        }

        return super.getNormal(p);

    }
}
