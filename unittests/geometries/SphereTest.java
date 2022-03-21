package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}