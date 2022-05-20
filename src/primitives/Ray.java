package primitives;

import java.util.ArrayList;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * primitive object ray, composed by a point and a direction vector
 */

public class Ray {
    private static final double DELTA = 0.001;

    final Point p0;
    final Vector dir;

    /**
     * ctor, must have all parameters.
     *
     * @param p0-       rays' point.
     * @param dir-rays' vector, must be a unit vector.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Ray(Point head, Vector direction, Vector normal){
        if (direction.dotProduct((normal))>0)
            this.p0 = head.add(normal.scale(DELTA));
        else
            this.p0 = head.subtract(normal.scale(DELTA));
        this.dir = direction;
    }


    /**
     * getters and setters for object fields.
     *
     * @return P0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * @return Dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * for comparing two rays, using superior class method.
     *
     * @param o- any object.
     * @return boolean type, true if o equals to this ray, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * for printing a formated string of a ray.
     *
     * @return formatted string of ray
     */
    @Override
    public String toString() { //ray= (a,b,c)+t(v1,v2,v3).
        return p0.toString() + "+t" + dir.toString();
    }

    /**
     * get a specific point on the ray (that is matches t value).
     *
     * @param t the t of the (v+t*u): the scalar of the ray.
     * @return a new point.
     */
    public Point getPoint(double t) {

        return p0.add(dir.scale(t));
    }

    /**
     * function now call upon the new find colsest geo point to find the first intersection point with ray beam.
     * @param intersections a list of intersection points.
     * @return Point type of the closest intersection of all points delivered in param.
     */
    public Point findClosestPoint(List<Point> intersections) {
        return (intersections == null || intersections.size()==0)  ? null
                : findClosestGeoPoint(intersections.stream().map(p -> new GeoPoint(null, p)).toList()
        ).point;
    }

    /**
     * function finds the closest intersection point to the ray p0.
     * @param intersections list of intersection points. notice that the geometry field in this list is null for all point.
     *                      because we dont care about the shape only the point.
     * @return GeoPoint type of the closest point to p0 from the list.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        if (intersections==null)
            return null;
        double min = Double.POSITIVE_INFINITY;
        GeoPoint p1 = null;
        for (GeoPoint p : intersections) {
            double dis = this.p0.distance(p.point);
            if (dis < min) {
                min = dis;
                p1 = p;
            }
        }
        return p1;
    }

    public List<Ray> listOfRays(int size,double gridSquareSize,double distance){
        Vector v=this.getDir().normalize();
        Vector unit=new Vector(1,0,0);
        if (v==unit)
            unit=new Vector(0,1,0);
        Vector right=v.crossProduct(unit).scale(gridSquareSize);
        Vector down=v.crossProduct(right).scale(gridSquareSize);
        Point center=this.p0.add(v.scale(distance));
        Point leftUpCorner=center.add(down.scale(size*-0.5)).add(right.scale(size*-0.5));
        List<Ray> listOfRay=new ArrayList<>(size*size);//we determine the size of the list to prevent the copying in resizing.
        for (int i=1;i<size+1;i++){
            for (int j=1;j<size+1;j++){
                listOfRay.add(new Ray(this.p0,leftUpCorner.add(right.scale(i)).add(down.scale(j)).subtract(this.getP0())));
            }
        }
        return listOfRay;
    }
}
