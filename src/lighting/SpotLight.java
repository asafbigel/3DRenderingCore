package lighting;

import primitives.*;

public class SpotLight extends  PointLight{

    private Vector Direction;

    /**
     * ctor for spotlight class.
     * @param intensity Color type, hold Double3 values of rgb.
     * @param position position of spot in space.
     * @param direction direction vector.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        Direction = direction.normalize();
    }

    /**
     * getter for intensity of light source.
     * @param p an intersection point.
     * @return the intensity scaled with attenuation factor.
     */
    @Override
    public Color getIntensity(Point p){
        //d is the attenuation factor, a spotlight light weakened when we move from light source
        //center, there for in a 90 degree angle between spotlight source and shape, we
        //get no light at all.
        double d=getL(p).dotProduct(Direction);
        if (d<=0)
            return Color.BLACK;
        return super.getIntensity(p).scale(d);
    }

    public SpotLight setNarrowBeam(double i) {
        return this;
    }
}
