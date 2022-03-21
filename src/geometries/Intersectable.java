package geometries;
import primitives.*;
import java.util.List;

public interface Intersectable {
    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    public List<Point> findIntersections(Ray ray);
}
