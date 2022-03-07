package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        // center is (0,0,0), dir vector is (1,0,0), radius is 1
        Tube t = new Tube(new Ray(new Point(0,0,0),new Vector(1,0,0)),1);
        Vector normal1 = t.getNormal(new Point(1,1,0));
        assertTrue(normal1.equals(new Vector(0,1,0)), "Wrong tube normal");
        // =============== Boundary Values Tests ==================
        Vector normal2 = t.getNormal(new Point(0,1,0));
        assertTrue(normal1.equals(new Vector(0,1,0)), "Wrong tube normal");

    }
}