import geometries.*;
import org.junit.jupiter.api.parallel.Execution;
import primitives.*;
import renderer.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



/**
 * Testing Integration between camera class and find interactions
 *
 * @author Ron & Asaf
 *
 */
//assertEquals (p1.add(new Vector(-1, -2, -3)),(new Point(0, 0, 0)),"ERROR: Point + Vector does not work correctly");
class IntegrationTest {
    @Test
    void testIntegrationWithSphere() {
        Sphere s1 = new Sphere(new Point(0,0,-3),1);
        Camera c1 = new Camera(new Point(0,0,0), new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        /**
         * tc01: sphere is ahead of camera and has one intersection point.
         */
        assertEquals(sumOfIntersection(c1,s1),2,"TC01");

        Sphere s2 = new Sphere(new Point(0,0,-2.5),2.5);
        Camera c2 = new Camera(new Point(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        /**
         * tc02: sphere is ahead of camera and has 18 intersection points.
         */
        assertEquals(sumOfIntersection(c2,s2),18,"TC02");

        Sphere s3 = new Sphere(new Point(0,0,-2),2);
        // same camera
        /**
         * tc03: sphere is ahead of camera and intersects 10 times. (it does not in the edges of view plane.)
         */
        assertEquals(sumOfIntersection(c2,s3),10,"TC03");

        Sphere s4 = new Sphere(new Point(0,0,-2),10);
        // same camera
        /**
         * tc04: the camera is in the sphere.
         */
        assertEquals(sumOfIntersection(c2,s4),9,"TC04");

        Sphere s5 = new Sphere(new Point(0,0,1),0.25);
        // same camera
        /**
         * tc05: sphere is behind the camera and has 0 intersection points.
         */
        assertEquals(sumOfIntersection(c2,s5),0,"TC05");
    }
    @Test
    void testIntegrationWithPlane() {
        Camera c1= new Camera(new Point(1,1,1),new Vector(-1,0,0),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Plane p1 = new Plane(new Point(-1,-1,-1),new Vector(-1,0,0));
        /**
         * tc11: parallel plane to the view plane. has 9 intersection points.
         */
        assertEquals(sumOfIntersection(c1,p1),9,"TC11");

        //same camera
         Plane p2 = new Plane(new Point(-10,1,1), new Point(-10,0,1), new Point(-9,1,3));
        /**
         * tc12: plane is not parallel to view plane and is infront of the camera, has 9 intersections.
         */
        assertEquals(sumOfIntersection(c1,p2),9,"TC12");

        //same camera
        Plane p3 = new Plane(new Point(-1,1,1), new Point(-1,0,1), new Point(0,1,1.5));
        /**
         * tc13: same as tc12, but has only 6 intersection point.
         */
        assertEquals(sumOfIntersection(c1,p3),6,"TC13");
    }
    @Test
    void testIntegrationWithTriangle() {
        Camera c1= new Camera(new Point(0,0,5),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Triangle t1 = new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        /**
         * tc21: small triangle with one intersection point.
         */
        assertEquals(sumOfIntersection(c1,t1),1,"TC21");

        // same camera
        Triangle t2 = new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        /**
         * bigger triangle with 2 intersection point.
         */
        assertEquals(sumOfIntersection(c1,t2),2,"TC22");
    }

    /**
     *
     * @param c the camera
     * @param g a geometry
     * @return sum of the intersections
     */
    int sumOfIntersection(Camera c, Geometry g){
        int sum =0;
       // for (int i=0; i<c.getWidth();i++)
       //             for (int j = 0; j<c.getHeight();j++)

        for (int i=0; i<3;i++)
            for (int j = 0; j<3;j++) {
                try {
                    sum += g.findIntersections(c.constructRay(3, 3, j, i)).size();
                } catch (java.lang.NullPointerException e) {
                }
            }
        return sum;
    }
}
