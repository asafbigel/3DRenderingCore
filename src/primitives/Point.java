package primitives;

public class Point {
   final Double3 xyz;

    public Point(double x,double y,double z) {

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
        //return new Point(xyz.d1+v.xyz.d1,xyz.d2+v.xyz.d2,xyz.d3+v.xyz.d3);
        Double3 d = xyz.add(v.xyz);
        return new Point(d.d1,d.d2, d.d3);
    }
    public Vector subtract(Point p){
        //return new Vector(xyz.d1-p.xyz.d1,xyz.d2-p.xyz.d2,xyz.d3- p.xyz.d3);
        Double3 d = xyz.subtract(p.xyz);
        return new Vector(d.d1, d.d2,d.d3);
    }
}
