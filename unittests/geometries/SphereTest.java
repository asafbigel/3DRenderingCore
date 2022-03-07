package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        Sphere s = new Sphere(new Point(0,0,0),1);
        Vector normal = s.getNormal(new Point(1,0,0));
        assertTrue(normal.equals(new Vector(1,0,0)), "wrong sphere normal");
    }
}