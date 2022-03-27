package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        //tc00: creating a simple sphere, centered at axis origin, with a radius of 1, and cheking for its normal at a certing point.
        // calculating the normal by hand and checking for equivalence.
        //expected: the vectors should be equal.
        Sphere s = new Sphere(new Point(0,0,0),1);
        Vector normal = s.getNormal(new Point(1,0,0));
        assertEquals(normal,(new Vector(1,0,0)), "wrong sphere normal");
    }

    @Test
    void testFindIntsersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(0.5, 0.5, 0),
                new Vector(3, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0), "Ray crosses sphere in one point");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(5, 2, 0),
                new Vector(3, 1, 0))),"Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1, 1, 0),
                new Vector(0, -1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1,0,1), result.get(0), "Ray crosses sphere in one point");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0),
                new Vector(0, 1, -1))),"TC12:Ray starts at sphere and goes outside");

        // **** ====Group: Ray's line goes through the center=====

        // TC13: Ray starts before the sphere (2 points)
         result = sphere.findIntersections(new Ray(new Point(3, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0,0,0),new Point (2,0,0)), result, "TC13: Ray starts before the sphere ");
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0,0,0), result.get(0), "TC14: Ray starts at sphere and goes inside");
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0,0,0), result.get(0), "TC15: Ray starts inside");
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0,0,0), result.get(0), "TC16: Ray starts at the center ");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0),
                new Vector(1, 0, 0))),"TC17: Ray starts at sphere and goes outside");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0),
                new Vector(1, 0, 0))),"TC18: Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 1),
                new Vector(0, 0, -1))),"TC19: Ray starts before the tangent point");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0),
                new Vector(0, 0, -1))),"TC20: Ray starts at the tangent point");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, -1),
                new Vector(0, 0, -1))),"TC20: Ray starts at the tangent point");

        // **** Group: Special cases

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 1),
                new Vector(0, 0, -1))),"TC19: Ray starts before the tangent point");


    }
}