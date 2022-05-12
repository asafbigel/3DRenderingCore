package geometries;
import primitives.*;
import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray,Double.POSITIVE_INFINITY);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * a new geo intersection function, we deleted the old one in refactoring process,
     * new the function gets max distance of intersection point, usually in primary rays we send POSITIVE_INFINITY
     * so we get all intersection points, but in shadow ray for instance we send the distance from intersection point
     * to light source preventing objects that are behind the light source to cast a shadow upon our intersection point.
     * @param ray the ray we cast (can be primary ray (camera) or secondary ray (shadow,reflection,or refraction).
     * @param maxDistance the max distance of the intersection, an intersection point farther than this distance won't be included,
     * @return a list of geo points with all intersection points.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance){

        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * each subclass of this intersectable will implement this part of
     * nvi function above.
     * @param ray Ray of intersection. (a cast ray)
     * @param maxDistance
     * @return List of all Geopoint intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance);

    /**
     * an assistance class for saving not only the intersection point but the shape that
     * it intersects on, so we can tell point color.
     */
    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;

        /**
         * ctor for pds assist class GeoPoint.
         * @param geometry primitive type of geometry.
         * @param point primitive type of point.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * equals function override.
         * @param o an objecct type, to compare.
         * @return boolean type true if objects are equal false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry==geoPoint.geometry && point.equals(geoPoint.point);
        }

        /**
         * converting class fields to string.
         * @return a formatted string of class fields.
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


}
