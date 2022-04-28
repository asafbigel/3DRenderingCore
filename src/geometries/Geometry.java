package geometries;
import primitives.*;

/**
 * this used to be an interface for all 3d geometries, but now it s an abstract class
 * this class field is shape emission light, which is basically shape's color.
 */
public abstract class Geometry extends Intersectable {

    /**
     * type color holds double3 type for rgb colors.
     * has no reduction factor.
     * type material holds its reduction factors and shininess.
     */
     protected Color emission=Color.BLACK;
     private Material material=new Material();

    /**
     * setter for emission light field.
     * @param emission Color type object with value.
     * @return this.emission, a Color type value.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * getter for emission light field, which distinguished the shape color that
     * comes back to our eye.
     * default value is black.(when there is no lighting).
     * @return Color type of emission light.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * * setter for Material field.
     * @param material type, holds 2 reduction factors and shininess.
     * @return this instance for builder design pattern.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public abstract Vector getNormal(Point p);
}
