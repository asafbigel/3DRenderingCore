package geometries;
import primitives.*;

/**
 * this is an interface for all 3d geometrics
 */
public abstract class Geometry extends Intersectable {

     protected Color emission=Color.BLACK;

    /**
     * setter for emission light field.
     * @param emission Color type object with value.
     * @return this.emission, a Color type value.
     */
    public Color setEmission(Color emission) {
        this.emission = emission;
        return this.emission;
    }

    /**
     * getter for emission light field.
     * default value is black.(when there is no lighting).
     * @return Color type of emission light.
     */
    public Color getEmission() {
        return emission;
    }

    public abstract Vector getNormal(Point p);
}
