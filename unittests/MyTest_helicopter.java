import geometries.Sphere;
import geometries.Triangle;
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

public class MyTest_helicopter {
    private Scene scene = new Scene("My Test");
    @Test
    public void helicopter() {
        Camera camera = new Camera(new Point(60, 0, -60), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(500, 500).setVPDistance(1000);

        Point p = new Point(0,0,27.5);
        scene.geometries.add(
                new Sphere(new Point(0,0,20),7)
                        .setEmission(Color.BLUE)
                        .setMaterial(new Material().setKt(0.3).setKr(0.1).setKd(0.4).setKs(0.2).setShininess(3)),
                new Sphere(new Point(0,0,28),1)
                        .setEmission(Color.GREEN)
                        .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
                new Triangle(p, new Point(1,10,27),new Point(-1,10,27))
                        .setEmission(Color.GREEN)
                        .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
                new Triangle(p, new Point(1,-10,27),new Point(-1,-10,27))
                        .setEmission(Color.GREEN)
                        .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
                new Triangle(p, new Point(10,1,27),new Point(10,-1,27))
                        .setEmission(Color.GREEN)
                        .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
                new Triangle(p, new Point(-10,1,27),new Point(-10,-1,27))
                        .setEmission(Color.GREEN)
                        .setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
                new Triangle(new Point(0,7,20), new Point(0,10,25), new Point(0,10,15))
                        .setEmission(new Color(214,78,156))
                        .setMaterial(new Material().setKr(0.7).setKd(0.3).setKs(0.3).setShininess(2))
        );

        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add( //
                new PointLight(new Color(200, 0, 0), new Point(25,25,25) )
                        .setKl(0.0009).setKq(0.00006));

        scene.ambientLight= new AmbientLight(new Color(35,70,120),0.3);
        scene.background = new Color(120,170,150);



        camera.setImageWriter(new ImageWriter("helicopter1", 500, 500))
                .moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}