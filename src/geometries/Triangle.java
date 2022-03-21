package geometries;

import primitives.Point;
import primitives.Ray;

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
        return null;
    }
}
