package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{
    /**
     * Position of Light source, and reduction factors.
     */
    private Point position;
    private double kC=1,kL=0,kQ=0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * reduction factor setter
     * @param kC double type, must be <1 and >0.
     * @return this instance, builder design pattern.
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * reduction factor setter
     * @param kL double type, must be <1 and >0.
     * @return this instance, builder design pattern.
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * reduction factor setter
     * @param kQ double type, must be <1 and >0.
     * @return this instance, builder design pattern.
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * gets light intensity which distinguish light color as well.
     *
     * @param p an intersection point.
     * @return Color type hold double3 type of rgb values.
     */
    @Override
    public Color getIntensity(Point p) {
        double d=p.subtract(position).length();
        return getIntensity().reduce(kC+kL*d+kQ*d*d);
    }

    /**
     * i don't know yet!!
     *
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }
}
