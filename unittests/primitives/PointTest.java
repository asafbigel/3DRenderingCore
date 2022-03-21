package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p1 = new Point(1, 2, 3);
    //tc00: we test the add function at the point class.
    //we create a certain point and using the add function to add a vector wo suppose the bring us to the axis origin.
    //expected: get the (0,0,0) point.
    @Test
    void testAdd() {
        assertEquals (p1.add(new Vector(-1, -2, -3)),(new Point(0, 0, 0)),"ERROR: Point + Vector does not work correctly");
    }
    //tc01: same as the above, but now we subtract, this time we get a vector as a return, and we compare it with the hand checked vector.
    //expected: both vectors should be equal.
    @Test
    void testSubtract() {
        assertEquals (new Vector(1, 1, 1),(new Point(2, 3, 4).subtract(p1)),"ERROR: Point - Point subtract does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        assertEquals(new Point(1,1,1).distanceSquared( new Point(4,5,6)),50,0.001, "ERROR: Point - Point distanceSquared does not work correctly");
    }

    @Test
    void testDistance() {
        assertEquals(new Point(1,1,1).distance( new Point(4,5,1)),5,0.001, "ERROR: Point - Point distance does not work correctly");
    }
}