package geometries;
import primitives.*;
public class Plane implements Geometry {
    Point q0;
    Vector normal;
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
    public Vector getNormal(){
        return normal;
    }

    public Plane(Point q1, Point q2, Point q3) {
        this.q0 = q1;
        Vector v1 = q3.subtract(q2);
        Vector v2 = q3.subtract(q1);
        /* can do at one line, but maybe it looks like anti Demeter low */
        Vector normal1 = v1.crossProduct(v2);
        normal1 = normal1.normalize();
        this.normal = normal1;
    }
}
