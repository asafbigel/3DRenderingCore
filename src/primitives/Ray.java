package primitives;

import java.util.List;

/**
 * primitive object ray, composed by a point and a direction vector
 */
public class Ray {
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

    public Point findClosestPoint(List<Point> pointList) {
        if (pointList==null)
            return null;
        double min = Double.POSITIVE_INFINITY;
        Point p1 = null;
        for (Point p : pointList) {
            double dis = this.p0.distance(p);
            if (dis < min) {
                min = dis;
                p1 = p;
            }
        }
        return p1;
    }
}
