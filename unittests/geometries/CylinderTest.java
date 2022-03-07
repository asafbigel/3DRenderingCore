package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void getNormal() {
        Cylinder c = new Cylinder(new Ray(new Point(0,0,0),new Vector(1,0,0)), 1 , 2);
        // ============ Equivalence Partitions Tests ==============
        Vector normal1 = c.getNormal(new Point(1,1,0));
        assertTrue(normal1.equals(new Vector(0,1,0)), "Wrong tube normal");
        Vector normal2 = c.getNormal(new Point(3,0.5,0));
        assertTrue(normal1.equals(new Vector(1,0,0)), "Wrong tube normal");
        Vector normal3 = c.getNormal(new Point(-3,0.5,0));
        assertTrue(normal1.equals(new Vector(-1,0,0)), "Wrong tube normal");
        // =============== Boundary Values Tests ==================
        Vector normal4 = c.getNormal(new Point(3,0,0));
        assertTrue(normal1.equals(new Vector(1,0,0)), "Wrong tube normal");
        Vector normal5 = c.getNormal(new Point(-3,0,0));
        assertTrue(normal1.equals(new Vector(-1,0,0)), "Wrong tube normal");
    }
}