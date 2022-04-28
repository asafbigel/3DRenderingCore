package scene;

import geometries.Geometries;
import geometries.Geometry;
import lighting.LightSource;
import primitives.*;
import lighting.AmbientLight;

import java.util.LinkedList;
import java.util.List;

/**
 * sence class, representing all aspects of the whole scene.
 */
public class Scene {
    /**
     * parameter are:
     * String name- name of scene.
     * Color background- of the scene.
     * AmbientLight ambientLight- colors each point in a unified color, by the same intensity.
     * Geometries geometries- list of 3d objects in scene.
     */
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight= new AmbientLight();
    public Geometries geometries=new Geometries();
    public List<LightSource> lights=new LinkedList<>();

    /**
     * ctor for class scene.
     * @param name we get only name of scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for background color.
     * @param background Color type, representing background color.
     * @return this scene instance, so we can call other function if we wish to.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for ambient light parameter.
     * @param ambientLight contains light intensity.
     * @return this scene instance, so we can call other function if we wish to.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for geometries parameter.
     * @param geometries list of 3d objects that are on the scene.
     * @return this scene instance, so we can call other function if we wish to.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}
