package geometries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================
        //tc00: 2 equal points for the ctor, a plane must have at least 3 different point to be defined.
        //expect: an Exception.
        assertThrows(Exception.class , () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0)), "2 points is same point");
        //tc01: all point is at the same line, point must not be on the same line to define a plane.
        //expect: an Exception.
        assertThrows(Exception.class , () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)), "3 points on same line");

    }

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //tc02: creating a simple plane, and constructing its normal values by hand.
        //expected: the get normal will return either the hand calculated normal, or the same vector on the other way.
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector v = new Vector(sqrt3, sqrt3, sqrt3);
        Vector normal = pl.getNormal(new Point(0, 0, 1));
        assertTrue(v.equals(normal) || v.equals(normal.scale(-1)), "Bad normal to plane");
    }

    @Test
    void findIntsersections() {
    }
}