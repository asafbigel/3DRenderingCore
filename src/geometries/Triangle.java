package geometries;

import primitives.Point;

public class Triangle extends Polygon{
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public String toString() {
        return "Triangle{} " + super.toString();
    }
}
