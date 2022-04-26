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
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * non virtual interface function, each class that extends this class
     * will only override the special part of this function ths is suited
     * for that class, and the main structure will be the same.
     * @param ray Ray of intersection. (a cast ray)
     * @return List of all Geopoint intersections.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * each subclass of this intersectable will implement this part of
     * nvi function above.
     * @param ray Ray of intersection. (a cast ray)
     * @return List of all Geopoint intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

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
