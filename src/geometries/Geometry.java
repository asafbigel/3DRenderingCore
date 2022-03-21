package geometries;
import primitives.*;

/**
 * this is an interface for all 3d geometrics
 */
public interface Geometry extends Intersectable {
    public Vector getNormal(Point p);
}
