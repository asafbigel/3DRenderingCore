package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {

        List<Point> pl = new LinkedList<>();
        Ray r=new Ray(new Point(1,1,1), new Vector(1,1,1));
        //tc00: list is empty.
        assertNull(r.findClosestPoint(pl), "tc00: list is empty.");

        pl.add(new Point(2,2,2));
        pl.add(new Point(4,4,4));
        pl.add(new Point(3,3,3));
        pl.add(new Point(5,5,5));
        //tc01:closest point is first in list.
        assertEquals(new Point(2,2,2),r.findClosestPoint(pl),"tc01: closest point is first in list.");
        Ray r1=new Ray(new Point(6,6,6),new Vector(-1,-1,-1));
        //tc02:point is last point is list.
        assertEquals(new Point(5,5,5), r1.findClosestPoint(pl),"tc02 Point is the last point in list.");

        //####EP#####
        pl.remove(pl.get(0));
        Ray r2=new Ray(new Point(2.5,2.5,2.5), new Vector(1,1,1));
        //tc11: point is in middle of list.
        assertEquals(new Point(3,3,3),r2.findClosestPoint(pl),"tc11: Point is in middle of list.");
    }

}