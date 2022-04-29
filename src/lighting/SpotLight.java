package lighting;

import primitives.*;

public class SpotLight extends  PointLight{

    private Vector Direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        Direction = direction;
    }
    @Override
    public Color getIntensity(Point p){
        double d=getL(p).dotProduct(Direction);
        if (d<=0)
            return Color.BLACK;
        return super.getIntensity(p).scale(d);
    }

    public SpotLight setNarrowBeam(double i) {
        return this;
    }
}
