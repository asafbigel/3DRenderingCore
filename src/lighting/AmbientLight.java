package lighting;

import primitives.*;

public class AmbientLight {
    Color intensity;

    public AmbientLight(Color intensity, Double3 d) {
       this.intensity=intensity.reduce(d);
    }

    public AmbientLight() {
        intensity=Color.BLACK;
    }

    public Color getIntensity(){
        return intensity;
    }
}
