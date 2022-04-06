package scene;

import geometries.Geometries;
import geometries.Geometry;
import primitives.*;
import lighting.AmbientLight;
public class Scene {

    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight= new AmbientLight();
    public Geometries geometries=new Geometries();
    //ctor
    public Scene(String name) {
        this.name = name;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
