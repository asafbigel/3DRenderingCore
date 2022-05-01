package lighting;

import primitives.*;


public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * get light intensity for directional light
     * @param p an intersection point.
     * @return the light source intensity.
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
        //in directional light the intensity is the same as source.
    }

    @Override
    public Vector getL(Point p) {
        return this.direction;
    }
}
