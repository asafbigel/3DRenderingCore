package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector v = new Vector(sqrt3, sqrt3, sqrt3);
        Vector normal = tr.getNormal(new Point(0, 0, 1));
        assertTrue(v.equals(normal) || v.equals(normal.scale(-1)), "Bad normal to triangle");
    }
}