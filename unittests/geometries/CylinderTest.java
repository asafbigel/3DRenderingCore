package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() {
        Cylinder c = new Cylinder(new Ray(new Point(0,0,0),new Vector(1,0,0)), 1 , 2);
        // ============ Equivalence Partitions Tests ==============
        Vector normal1 = c.getNormal(new Point(1,1,0));
        //tc00: a simple point on the body of cylinder.
        assertEquals(normal1,(new Vector(0,1,0)), "Wrong tube normal");
        Vector normal2 = c.getNormal(new Point(2,0.5,0));
        //tc01: a point at the second base of the cylinder, wich is the end where the center of the base is p:(1,0,0).
        //ass expected the normal should be the dir vector.
        assertEquals(normal2,(new Vector(1,0,0)), "Wrong tube normal");
        Vector normal3 = c.getNormal(new Point(0,0.5,0));
        //tc02: a point at the first base of the cylinder.
        //normal shoud be the dir vector but in the opposite way.
        assertEquals(normal3,(new Vector(-1,0,0)), "Wrong tube normal");
        // =============== Boundary Values Tests ==================
        Vector normal4 = c.getNormal(new Point(2,0,0));
        //tc03: a point at the very end of the base, we defines the normal to be the dir vector.
        assertEquals(normal4,(new Vector(1,0,0)), "Wrong tube normal");
        Vector normal5 = c.getNormal(new Point(0,0,0));
        //tc04: a point at the center of the cylinder base.
        //excepted normal is the dir vector on the other way.(so it wont point inside the cylinder.
        assertEquals(normal5,(new Vector(-1,0,0)), "Wrong tube normal");
    }

    @Test
    void findIntsersections() {
    }
}