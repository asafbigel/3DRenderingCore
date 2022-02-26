package primitives;

/**
 * primotive object point.
 */
public class Point {
   final Double3 xyz;

    /**
     * ctor for point object, must have all three parameters.
     * @param x
     * @param y
     * @param z
     */
    public Point(double x,double y,double z) {

        this.xyz=new Double3(x,y,z);
    }

    /**
     * for comparing tow points.
     * class uses superior class method,to overcome the accuracy challenge.
     * @param o - any object.
     * @return boolean type, true if it is equal and falls otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * for printing a format style of point.
     * @return format string of point.
     */
    @Override
    public String toString() {
        return this.xyz.toString();
    }

    /**
     * function adds a vector to a point (cuasing it to move).
     * @param v- another primitive object vector.
     * @return a new point, preventing rots.
     */
    public Point add(Vector v){
        //return new Point(xyz.d1+v.xyz.d1,xyz.d2+v.xyz.d2,xyz.d3+v.xyz.d3);
        Double3 d = xyz.add(v.xyz);
        return new Point(d.d1,d.d2, d.d3);
    }

    /**
     * fundtion subtructs tow points, creating a new vector.
     * @param p- point type object.
     * @return- a new vector.
     */
    public Vector subtract(Point p){
        //return new Vector(xyz.d1-p.xyz.d1,xyz.d2-p.xyz.d2,xyz.d3- p.xyz.d3);
        Double3 d = xyz.subtract(p.xyz);
        return new Vector(d.d1, d.d2,d.d3);
    }
}
