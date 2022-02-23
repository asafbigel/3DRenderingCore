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
}
