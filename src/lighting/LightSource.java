package lighting;
import primitives.*;

public interface LightSource {
    /**
     * gets light intensity which distinguish light color as well.
     * @param p an intersection point.
     * @return Color type hold double3 type of rgb values.
     */
    public Color getIntensity(Point p);

    /**
     * function return light source direction vector.
     * @param p
     * @return
     */
    public Vector getL(Point p);

    /**
     * return the distance from point to light source.
     * @param point that we want to know its distance to light source.
     * @return double type, distance.
     */
    double getDistance(Point point);
}
