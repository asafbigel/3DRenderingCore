package primitives;

import geometries.Polygon;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void vector() {
        //tc00: we create the zero vector, and expect an Exception.
        assertThrows(IllegalArgumentException.class, () -> new Vector(0,0,0),"Constructed a zero vector");
//        assertThrows(IllegalArgumentException.class, //
//                () -> new Vector(new Double3(0,0,0)),"Constructed a zero vector");
    }

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void add() {

    }

    @Test
    void scale() {
    }

    @Test
    void crossProduct() {
        //tc: we cross product 2 parallel vectors, and as expected its returning the zero vector and thats an exception.
        assertThrows(IllegalArgumentException.class, () ->v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");

        Vector vr = v1.crossProduct(v3);
        assertTrue(isZero(vr.length() - v1.length() * v3.length()),"ERROR: crossProduct() wrong result length");
        assertTrue(isZero(vr.dotProduct(v1)) && isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void lengthSquared() {
        //tc: we manually calculate the length squared of one of the above vectors, and compare the answers.
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    @Test
    void length() {
        // tc: we use one pythagorean triples to calculate the length of a vector, and compare the answers.
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        // test vector normalization vs vector length and cross-product
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        //tc: for a random vector, we normalize it and checks its length afterwards to make sure it's equal to 1.
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");
        // tc: we make sure normalizing a vector does not affect its direction.
        assertThrows(IllegalArgumentException.class, () ->v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
        //tc: as above we make sure normalizing a vector does not affect is direction this time turning it 180 deg.
        assertFalse (v.dotProduct(u) < 0,"ERROR: the normalized vector is opposite to the original one");
    }

    @Test
    void dotProduct() {
        // tc: we dot product two orthogonal vectors, and expecting to get zero, we use the is zero function to do so.
        assertTrue (isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero");

        assertTrue (isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");
    }
}