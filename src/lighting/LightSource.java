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
     * i don't know yet!!
     * @param p
     * @return
     */
    public Vector getL(Point p);
    double getDistance(Point point);
}
