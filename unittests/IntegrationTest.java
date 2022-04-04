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
        assertEquals(sumOfIntersection(c1,s1),2,"TC01");

        Sphere s2 = new Sphere(new Point(0,0,-2.5),2.5);
        Camera c2 = new Camera(new Point(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        assertEquals(sumOfIntersection(c2,s2),18,"TC02");

        Sphere s3 = new Sphere(new Point(0,0,-2),2);
        // same camera
        assertEquals(sumOfIntersection(c2,s3),10,"TC03");

        Sphere s4 = new Sphere(new Point(0,0,-2),10);
        // same camera
        assertEquals(sumOfIntersection(c2,s4),9,"TC04");

        Sphere s5 = new Sphere(new Point(0,0,1),0.25);
        // same camera
        assertEquals(sumOfIntersection(c2,s5),0,"TC05");
    }
    @Test
    void testIntegrationWithTriangle() {
        Camera c1= new Camera(new Point(1,1,1),new Vector(-1,0,0),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Plane p1 = new Plane(new Point(-1,-1,-1),new Vector(-1,0,0));
        assertEquals(sumOfIntersection(c1,p1),9,"TC11");

        Camera c2= new Camera(new Point(1,1,1),new Vector(-1,0,0),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
       // TODO:
        // Plane p2 = new Plane(new Point(-1,-1,-1),new Vector(-1,0,0),);
       // assertEquals(sumOfIntersection(c2,p2),9,"TC12");
        //TC13



    }
    @Test
    void testIntegrationWithPlane() {
        Camera c1= new Camera(new Point(1,1,1),new Vector(-1,0,0),new Vector(0,1,0)).setVPDistance(1).setVPSize(3,3);
        Triangle t1 = new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
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
               // try {
                    sum += g.findIntersections(c.constructRay(3, 3, j, i)).size();
              //  } catch (java.lang.NullPointerException e) {
               // }
            }
        return sum;
    }
}
