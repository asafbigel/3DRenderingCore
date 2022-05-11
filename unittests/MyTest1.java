import geometries.Sphere;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class MyTest1 {
    private Scene scene = new Scene("My Test");
    @Test
    public void tenObjects() {
        Camera camera = new Camera(new Point(30, 5, 10), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(500, 500).setVPDistance(250);
        double angle;
        double x,y;
        for (double i = 0 ; i <360*3; i+=20){
            angle = (i/360) *2*Math.PI;
            x = 50*Math.cos(angle);
            y = 50*Math.sin(angle);
            scene.geometries.add(
                    new Sphere(new Point(x,y,i/4),10)
                            .setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
                            .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
        }

        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add( //
                new PointLight(new Color(200, 0, 0), new Point(25,25,25) )
                        .setKl(0.0009).setKq(0.00006));

        scene.ambientLight= new AmbientLight(new Color(35,70,120),0.3);
        scene.background = new Color(240,170,150);


        camera.setImageWriter(new ImageWriter("MyBalls", 500, 500))
                .moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage("spiral");
    }
}