package primitives;

/**
 * primitive type object vector.
 * inherits one field type xyz from calss point.
 */
public class Vector extends Point {
    /**
     * ctor for object, vector must not be a zero vector, we asure that with equals function
     * if it is a zero vector, we throw an exception.
     *
     * @param x-  x value.
     * @param y-y value.
     * @param z-z value.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("zero vector");
        }

    }

    /**
     * 2nd ctor.
     *
     * @param _xyz-Double3 primitive object.
     */
    public Vector(Double3 _xyz) {
        super(_xyz);
    }

    /**
     * for printing formated string of a vector.
     *
     * @return- formated string of a vector.
     */
    @Override
    public String toString() {

        return xyz.toString() + "->";
    }

    /**
     * function adds tow vectr together, creating a new vector, to prevendt rots.
     *
     * @param v- another primitive object vector.
     * @return- a new vector, witch values are the sum of the original vectors.
     */
    public Vector add(Vector v) {
        return new Vector(add(v).xyz);
    }

    /**
     * function multiply a vector with a scalar.
     *
     * @param d1- the scalar we multiply our vector with.
     * @return- a new vector.
     */
    public Vector scale(double d1) {
        //return new Vector (xyz.d1*d, xyz.d2*d, xyz.d3*d);
        return new Vector(xyz.scale(d1));
    }

    /**
     * funtion preform a cross product on two vectors.
     *
     * @param v-other vector in the process.
     * @return- a new vector, witch is the product.
     */
    public Vector crossProduct(Vector v) {
        double x = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double y = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double z = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;
        return new Vector(x, y, z);
    }

    /**
     * function returns the length of this vector squared.
     *
     * @return- length of this vector squared.
     */
    public double lengthSquared() {
        // return xyz.d1*xyz.d1+ xyz.d2*xyz.d2+xyz.d3* xyz.d3;
        // Double3 d = xyz.product(xyz);
        // return d.d1 + d.d2 + d.d3;
        return this.dotProduct(this);

    }

    /**
     * function returns the actuall length of a vector.
     *
     * @return-length of this vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * function changes the length of this vector to be 1.
     *
     * @return- a new vector, with the same direction, and length 1.
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    /**
     * function perform a dot product process.
     *
     * @param v- the other vector in th process.
     * @return- a scalar that is the product of the process.
     */
    public double dotProduct(Vector v) {
        //return xyz.d1*v.xyz.d1+ xyz.d2*v.xyz.d2+xyz.d3*v.xyz.d3;
        // maybe this is better ???
        Double3 d = xyz.product(v.xyz);
        return d.d1 + d.d2 + d.d3;
    }
}

