package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {
    //tc00:testing that we can add shapes to shapes.
    @Test
    void testAdd() {
       // Geometries g1 = new Geometries(new Sphere(new Point(1,1,1),1));
       // Geometries g2 = new Geometries( new Plane(new Point(0,0,0),new Point(5,6,7), new Point(4,9,1)));
    }

    @Test
    void testFindIntersections() {
        //TODO: implement this tests!
        Ray r1 = new Ray(new Point(30,30,30), new Vector(1,2,3));
        Ray r2 = new Ray(new Point(2,1,1), new Vector(-3,2,3));
        Ray r3 = new Ray(new Point(2,0,1.5), new Vector(3,6,5.5));
        Ray r4 = new Ray(new Point(2,1,2), new Vector(-1,3,3));

        Plane p1 = new Plane(new Point(-1,-1,-1),new Point(-1,-1,-2),new Point(-1,-2,-1));
        Plane p2 = new Plane(new Point(1,1,1),new Point(3,1,2),new Point(1,2,5));
        Sphere s1 = new Sphere(new Point(-10,-10,-10), 2);
        Sphere s2 = new Sphere(new Point(2,2,2), 1);
        Sphere s3 = new Sphere(new Point(2,1,2), 1);
        Triangle t = new Triangle(new Point(0,0,0),new Point(1,1,1),new Point(4,5,6));
        Geometries g = new Geometries(p1,p2,s1,s2);
        //tc11: empty List of shapes.
        assertNull(new Geometries().findIntersections(r1), "tc11: empty List of shapes.");
        //tc12: no intersection with any of the shapes.
        assertNull(g.findIntersections(r1), "tc12: no intersection with any of the shapes.");
        //tc13: only one shapes intersects.
        List<Point> result = g.findIntersections(r2);
        assertEquals(1, result.size(), "tc13: Wrong number of points");
        assertEquals(new Point(-1,3,4), result.get(0), "tc13: only one shapes intersects");
        //tc14: sum of the shapes intersects and some don't.
        result = g.findIntersections(r3);
        assertEquals(2, result.size(), "tc14: Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(2.43,0.86,2.29),new Point (2.6,1.2,2.6)), result, "tc14: sum of the shapes intersects and some don't");
        //tc15: all shapes intersects in scene.
        g= new Geometries(p1,s3,p2);
        result = g.findIntersections(r4);
        assertEquals(3, result.size(), "tc15: Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX() && result.get(1).getX() > result.get(2).getX())
            result = List.of(result.get(2), result.get(1), result.get(0));
        if (result.get(0).getX() > result.get(2).getX() && result.get(2).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(2), result.get(0));
        if (result.get(1).getX() > result.get(0).getX() && result.get(0).getX() > result.get(2).getX())
            result = List.of(result.get(2), result.get(0), result.get(1));
        if (result.get(1).getX() > result.get(2).getX() && result.get(2).getX() > result.get(0).getX())
            result = List.of(result.get(0), result.get(2), result.get(1));
        if (result.get(2).getX() > result.get(0).getX() && result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0), result.get(2));
        assertEquals(List.of(new Point(-1,10,11),new Point(1.77,1.69,2.69),new Point (1.94,1.18,2.18)), result, "tc15: all shapes intersects in scene");
    }
}