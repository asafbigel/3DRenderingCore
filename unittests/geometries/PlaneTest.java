package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
    void testGetNormal() {
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
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane p=new Plane(new Point(1,0,0),new Point(2,0,0),new Point(0,1,0));
        //tc00: Ray intersects the plane.
        List<Point> result=p.findIntersections(new Ray(new Point(7,4,5),
                new Vector(0,2,-1)));
        assertEquals(1,result.size(),"number of intersections is not right.(tc00)");
        assertEquals(new Point(7,14,0),result.get(0),"tc00: Ray intersects the plane.");
        //tc01:Ray does not intersect with plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,5),
                new Vector(0,-2,1))),"tc01:Ray does not intersect with plane.");

        // =============== Boundary Values Tests ==================
        //**** group: Ray is parallel to plane.
        //tc10:not included in the plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,5),
                new Vector(0,-2,0))),"tc10: Ray is parallel to the plane, and not included in the plane.");
        //tc11:included in plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,0),
                new Vector(0,-2,0))),"tc11:Ray is parallel to the plane, and included in plane.");

        //**** group: Ray is orthogonal to plane.
        //tc12:p0 is before plane.
        result=p.findIntersections(new Ray(new Point(7,4,5),
                new Vector(0,0,-1)));
        assertEquals(1,result.size(),"number of intersections is not right.(tc12)");
        assertEquals(new Point(7,4,0),result.get(0),"tc12:p0 is before plane.");
        //tc13:p0 in plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,0),
                new Vector(0,0,-1))),"tc13:p0 in plane.");
        //tc14:p0 is after plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,-5),
                new Vector(0,0,-1))),"tc14:p0 is after plane.");

        //tc15:Ray is neither orthogonal nor parallel o plane, and p0 is in plane.
        assertNull(p.findIntersections(new Ray(new Point(7,4,0),
                new Vector(0,2,-1))),"tc13:p0 in plane.");
        //tc16:Ray is neither orthogonal nor parallel to plane, and begins at reference point of plane.
        assertNull(p.findIntersections(new Ray(new Point(1,0,0),
                new Vector(0,2,-1))),"tc13:p0 in plane.");
    }
}