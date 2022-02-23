package primitives;
public class Vector extends Point {
    public Vector(Double x, Double y, Double z) {
        super(x, y, z);
        if(Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("zero vector");
        }

    }
    public Vector(int x, int y, int z) {
        super(x, y, z);
        if(Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("zero vector");
        }
    }

        @Override
    public String toString() {
        return xyz.toString()+"->";
    }
    public Vector add(Vector v){
        return new Vector (xyz.d1+v.xyz.d1, xyz.d2 +v.xyz.d2, xyz.d3+v.xyz.d3);
    }
    public Vector scale(double d){
        return new Vector (xyz.d1*d, xyz.d2*d, xyz.d3*d);
    }
    public Vector crossProduct(Vector v){
        double x=xyz.d2*v.xyz.d3-xyz.d3*v.xyz.d2;
        double y=xyz.d3*v.xyz.d1-xyz.d1*v.xyz.d3;
        double z=xyz.d1*v.xyz.d2-xyz.d2*v.xyz.d1;
        return new Vector(x,y,z);
    }
    public double lengthSquared(){
        return xyz.d1*xyz.d1+ xyz.d2*xyz.d2+xyz.d3* xyz.d3;
    }
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize(){
        return scale(1/length());
    }
    public double dotProduct(Vector v){
        return xyz.d1*v.xyz.d1+ xyz.d2*v.xyz.d2+xyz.d3*v.xyz.d3;
    }
}

