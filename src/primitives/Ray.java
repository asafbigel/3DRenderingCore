package primitives;

import java.util.Objects;

public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0)&&dir.equals(ray.dir);
    }

    @Override
    public String toString() { //ray= (a,b,c)+t(v1,v2,v3).
        return p0.toString()+"+t"+dir.toString();
    }
}
