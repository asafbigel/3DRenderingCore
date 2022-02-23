package primitives;

import java.util.Objects;

public class Point {
   Double3 xyz;

    public Point(Double x,Double y,Double z) {

        this.xyz=new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }

    public Point add(Vector v){
        return new Point(xyz.d1+v.xyz.d1,xyz.d2+v.xyz.d2,xyz.d3+v.xyz.d3);
    }
    public Vector subtruct(Point p){
        return new Vector(p.xyz.d1-xyz.d1,p.xyz.d2-xyz.d2,p.xyz.d3- xyz.d3);
    }
}
