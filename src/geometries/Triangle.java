package geometries;
import primitives.Point;

/**
 * a 3d geometric shape,
 */
public class Triangle extends Polygon{
    /**
     * ctor, gets 3 primitive points and uses polygon ctor to initialize the fields.
     * @param vertices-3 primitive points.
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    /**
     * to print a formated strign representing a triangle.
     * @return-formated string representing a triangle.
     */
    @Override
    public String toString() {
        return "Triangle{} " + super.toString();
    }
}
