import geometries.Plane;
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

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class MyTest {
    private Scene scene = new Scene("My Test");

    @Test
    public void tenObjects() {
        Camera camera = new Camera(new Point(30, 5, 10), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(500, 500).setVPDistance(250);

        scene.geometries.add( //
                new Sphere(new Point(0, 6, 10), 2)
                        .setEmission(new Color(137, 42, 174))
                        .setMaterial(new Material().setKt(0.9).setKd(0.4).setKs(0.8).setShininess(6)),
                new Sphere(new Point(0, 6, 10), 0.5)
                        .setEmission(new Color(202, 150, 170))
                        .setMaterial(new Material().setKd(0.4).setKs(0.8).setShininess(6)),
                new Sphere(new Point(0, 8, 6), 1)
                        .setEmission(new Color(200, 26, 128))
                        .setMaterial(new Material().setKt(0.9).setKd(0.4).setKs(0.8).setShininess(6)),
                // 4 triangle to the piramida
                new Triangle(new Point(4, 4, 7), new Point(2, 5, 6.5), new Point(3, 3, 7.5))
                        .setEmission(new Color(120, 195, 30))
                        .setMaterial(new Material().setKt(0.3).setKd(0.2).setKs(0.4).setShininess(2)),
                new Triangle(new Point(4, 4, 7), new Point(2, 5, 6.5), new Point(3, 4, 10))
                        .setEmission(new Color(120, 195, 30))
                        .setMaterial(new Material().setKt(0.3).setKd(0.2).setKs(0.4).setShininess(2)),
                new Triangle(new Point(4, 4, 7), new Point(3, 4, 10), new Point(3, 3, 7.5))
                        .setEmission(new Color(120, 195, 30))
                        .setMaterial(new Material().setKt(0.3).setKd(0.2).setKs(0.4).setShininess(2)),
                new Triangle(new Point(3, 4, 10), new Point(2, 5, 6.5), new Point(3, 3, 7.5))
                        .setEmission(new Color(120, 195, 30))
                        .setMaterial(new Material().setKt(0.3).setKd(0.2).setKs(0.4).setShininess(2)),

                // triangle to the SHIPUA
                new Triangle(new Point(23, 2, 8), new Point(-22, 2, 8), new Point(2, 15, 1))
                        .setEmission(new Color(39, 215, 198))
                        .setMaterial(new Material().setKt(0.3).setKd(0.8).setKs(0.3).setShininess(5)),

                new Plane(new Point(0, 1, 0), new Point(1, 0, 0), new Point(2, 1, 0))
                        .setEmission(new Color(90, 25, 25))
                        .setMaterial(new Material().setKd(0.3).setKs(0.9).setShininess(1)),

                new Plane(new Point(0, -20, 1), new Point(2, -20, 3), new Point(5, -20, 13))
                        .setEmission(new Color(50, 48, 79))
                        .setMaterial(new Material().setKr(0.7).setKd(0.2).setKs(0.3).setShininess(1)),

                new Triangle(new Point(-24, 2, 22), new Point(-14, 30, 25), new Point(-15, 35, 13))
                        .setEmission(new Color(39, 215, 198))
                        .setMaterial(new Material().setKt(0.9).setKd(0.1).setKs(0.1).setShininess(2))
        );
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add( //
                new PointLight(new Color(200, 0, 0), new Point(25, 25, 25))
                        .setKl(0.0009).setKq(0.00006));

        scene.ambientLight = new AmbientLight(new Color(35, 70, 120), 0.3);
        camera.moveRight(-0.4)
                .moveForward(-0.025)
                .moveUp(0.01)
                .rotationLeft(-1)
                .setImageWriter(new ImageWriter("STAM_IMG", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

        camera.rotationOnSide(90)
                .setImageWriter(new ImageWriter("STAM_IMG1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}

