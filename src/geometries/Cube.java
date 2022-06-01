package geometries;

import primitives.*;

import java.util.List;

public class Cube extends Intersectable {
    Point[] bounds = new Point[2];
    Geometries geometry; //all the shapes that is in this cube.

    public Cube(Point min, Point max) {
        this.bounds[0] = min;
        this.bounds[1] = max;
    }

    /**
     * each subclass of this intersectable will implement this part of
     * nvi function above.
     *
     * @param ray         Ray of intersection. (a cast ray)
     * @param maxDistance
     * @return List of all Geopoint intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (!intersect(ray))
            return null;
        return geometry.findGeoIntersectionsHelper(ray,maxDistance);
    }

    boolean intersect(Ray r) {
        int[] sign = new int[3];
        Vector invdir=r.getDir().scale(-1);
        sign[0]=(invdir.getX()<0?1:0);
        sign[1]=(invdir.getY()<0?1:0);
        sign[2]=(invdir.getZ()<0?1:0);
        double tmin, tmax, tymin, tymax, tzmin, tzmax;

        tmin = (bounds[sign[0]].getX() - r.getP0().getX()) * invdir.getX();
        tmax = (bounds[1 - sign[0]].getX() - r.getP0().getX()) * invdir.getX();
        tymin = (bounds[sign[1]].getY() - r.getP0().getY()) * invdir.getY();
        tymax = (bounds[1 - sign[1]].getY() - r.getP0().getY()) * invdir.getY();

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;
        if (tymax < tmax)
            tmax = tymax;

        tzmin = (bounds[sign[2]].getZ() - r.getP0().getZ()) * invdir.getZ();
        tzmax = (bounds[1 - sign[2]].getZ() - r.getP0().getZ()) * invdir.getZ();

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        if (tzmin > tmin)
            tmin = tzmin;
        if (tzmax < tmax)
            tmax = tzmax;

        return true;
    }
}
