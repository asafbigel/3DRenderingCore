import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;


import java.util.Date;
import java.util.Timer;

import static java.lang.System.out;

/**
 * Test program for the 1st stage
 *
 * @author ron shaull and asaf bigel.
 */
public final class Main {

	/**
	 * Main program to tests initial functionality of the 1st stage
	 *
	 * @param args irrelevant here
	 */
	public static void main(String[] args) {
		out.println(System.currentTimeMillis());
		var start = System.currentTimeMillis();
		Helicopter();
		TenObjects();
		out.println(System.currentTimeMillis());
		out.print("all: ");
		out.println((System.currentTimeMillis()- start)/1000);
	}

	public static void Helicopter() {
		Scene scene = new Scene("My Helicopter");

		Camera camera = new Camera(new Point(60, 0, -30), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
				.setVPSize(500, 500).setVPDistance(1000);

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
				.setKl(0.0004).setKq(0.0000006));
		scene.lights.add(new PointLight(new Color(200, 0, 0), new Point(25, 25, 25))
				.setKl(0.0009).setKq(0.00006));
		scene.ambientLight = new AmbientLight(new Color(35, 70, 120), 0.3);
		scene.background = new Color(120, 170, 150);
		scene.geometries.add(
				new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))
						.setEmission(Color.GREEN)
						.setMaterial(new Material())
		);
		camera.moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30);
		Point p = new Point(0, 0, 57.5);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 50), 7)
						.setEmission(Color.BLUE)
						.setMaterial(new Material().setKt(0.3).setKr(0.1).setKd(0.4).setKs(0.2).setShininess(3)),
				new Sphere(new Point(0, 0, 58), 1)
						.setEmission(Color.GREEN)
						.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
				new Triangle(p, new Point(1, 10, 57.5), new Point(-1, 10, 57.5))
						.setEmission(Color.GREEN)
						.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
				new Triangle(p, new Point(1, -10, 57.5), new Point(-1, -10, 57.5))
						.setEmission(Color.GREEN)
						.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
				new Triangle(p, new Point(10, 1, 57.5), new Point(10, -1, 57.5))
						.setEmission(Color.GREEN)
						.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
				new Triangle(p, new Point(-10, 1, 57.5), new Point(-10, -1, 57.5))
						.setEmission(Color.GREEN)
						.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
				new Triangle(new Point(0, 7, 50), new Point(0, 10, 55), new Point(0, 10, 45))
						.setEmission(new Color(214, 78, 156))
						.setMaterial(new Material().setKr(0.7).setKd(0.3).setKs(0.3).setShininess(2))
		);

		int i;
		for (i = 0; i < 139; i++) {
			camera.moveRight(-3)
					.moveForward(-0.025)
					.moveUp(-0.2)
					.rotationLeft(-1)
					.setImageWriter(new ImageWriter("helicopter" + i, 500, 500)) //
					.setRayTracer(new RayTracerBasic(scene))
					.renderImage() //
					.writeToImage("helicopter");
		}
		for (; i < 361; i++) {
			camera.moveRight(0.25)
					.moveForward(-0.025)
					.moveUp(-0.1)
					//.rotationLeft(-1)
					.setImageWriter(new ImageWriter("helicopter" + i, 500, 500)) //
					.setRayTracer(new RayTracerBasic(scene)) //
					.renderImage() //
					.writeToImage("helicopter");
		}
	}

	public static void TenObjects() {
		Scene scene = new Scene("Ten objects");

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

/*
        camera.moveRight(-2)
                .moveForward(15)
                .moveUp(-1)
                .rotationLeft(-30)
                .setImageWriter(new ImageWriter("MyTest", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

 */
		for (int i = 0; i < 361; i++) {
			camera.moveRight(-0.4)
					.moveForward(-0.025)
					.moveUp(0.01)
					.rotationLeft(-1)
					.setImageWriter(new ImageWriter("MyTest" + i, 500, 500)) //
					.setRayTracer(new RayTracerBasic(scene)) //
					.renderImage() //
					.writeToImage("STAM_IMG");
		}
	}
}
