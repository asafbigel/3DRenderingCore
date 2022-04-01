package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        //tc00: like in plane, we create a simple triangle and calculate its normal by hand.
        //expected: the normal can be at tow opposite directions, either one is a suitable answer.
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector v = new Vector(sqrt3, sqrt3, sqrt3);
        Vector normal = tr.getNormal(new Point(0, 0, 1));
        assertTrue(v.equals(normal) || v.equals(normal.scale(-1)), "Bad normal to triangle");
    }

    @Test
    void testFindIntsersections() {
        // ============ Equivalence Partitions Tests =============
        Triangle t=new Triangle(new Point(2,0,0),new Point(0,2,0),new Point(-1,0,0));
        //tc00: Ray intersects inside the triangle.
        List<Point> result=t.findIntersections(new Ray(new Point(0.5,0.5,1),new Vector(0,0,-1)));
        assertEquals(1,result.size(),"number of intersections is invalid.");
        assertEquals(new Point(0.5,0.5,0),result.get(0),"tc00: Ray intersects inside the triangle.");
        //tc01: Ray does not intersects with triangle, and "hits" against edge.
        assertNull(t.findIntersections(new Ray(new Point(2,2,1),new Vector(0,0,-1))));
        //tc02: Ray does not intersect with trieangle, and "hits" agiansts vertex.
        assertNull(t.findIntersections(new Ray(new Point(-0.04,2.17,1),new Vector(0,0,-1))));

        // =============== Boundary Values Tests ==================
        //tc10:Ray itersects on edge.
        assertNull(t.findIntersections(new Ray(new Point(1,0,1),new Vector(0,0,-1))),"tc10:Ray itersects on edge.");
        //tc11:Ray intersects in vertex.
        assertNull(t.findIntersections(new Ray(new Point(2,0,1),new Vector(0,0,-1))),"tc11:Ray intersects in vertex.");
        //tc12: RAy intersects on edges continuation.
        assertNull(t.findIntersections(new Ray(new Point(3,0,1),new Vector(0,0,-1))),"c12: RAy intersects on edges continuation.");



        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(0,0,-1))));
    }
}