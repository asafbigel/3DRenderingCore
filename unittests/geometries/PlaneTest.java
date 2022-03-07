package geometries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    public void testConstructor() {
        assertThrows(Exception.class , () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0)), "2 points is same point");
        assertThrows(Exception.class , () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)), "3 points on same line");

    }

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector v = new Vector(sqrt3, sqrt3, sqrt3);
        Vector normal = pl.getNormal(new Point(0, 0, 1));
        assertTrue(v.equals(normal) || v.equals(normal.scale(-1)), "Bad normal to plane");
    }
}