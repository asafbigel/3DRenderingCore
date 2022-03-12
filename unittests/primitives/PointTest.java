package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p1 = new Point(1, 2, 3);
    @Test
    void add() {
        assertEquals (p1.add(new Vector(-1, -2, -3)),(new Point(0, 0, 0)),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void subtract() {
        assertEquals (new Vector(1, 1, 1),(new Point(2, 3, 4).subtract(p1)),"ERROR: Point - Point subtract does not work correctly");
    }

    @Test
    void distanceSquared() {
        assertEquals(new Point(1,1,1).distanceSquared( new Point(4,5,6)),50,0.001, "ERROR: Point - Point distanceSquared does not work correctly");
    }

    @Test
    void distance() {
        assertEquals(new Point(1,1,1).distance( new Point(4,5,1)),5,0.001, "ERROR: Point - Point distance does not work correctly");
    }
}