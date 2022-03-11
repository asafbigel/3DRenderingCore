package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p1 = new Point(1, 2, 3);
    @Test
    void add() {
      //  assertTrue (p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0)),"ERROR: Point + Vector does not work correctly");
        assertEquals(p1.add(new Vector(-1, -2, -3)),new Point(0, 0, 0),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void subtract() {
       // assertTrue (new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)),"ERROR: Point - Point does not work correctly");
        assertEquals (new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
    }
}