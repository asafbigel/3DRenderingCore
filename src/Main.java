import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;


import static java.lang.System.out;

/**
 * Test program for the 1st stage
 *
 * @author ron shaull and asaf bigel.
 */
public final class Main {

	static Scene scene;
	static Camera camera;
	static Cube cube;
	/**
	 * Main program to tests initial functionality of the 1st stage
	 *
	 * @param args irrelevant here
	 */
	public static void main(String[] args) {
		setSceneAndCamera();
		var start = System.currentTimeMillis();
		out.println(start);
		groupPicture();
		var middle1 = System.currentTimeMillis();
		out.println("first picture (no improvements): " + (middle1 - start) / 1000 + "sec");
		groupPicture_Cube();
		var middle2 = System.currentTimeMillis();
		out.println("second picture (cube): " + (middle2 - middle1) / 1000 + "sec");
		groupPictureWithThreads();
		var middle3 = System.currentTimeMillis();
		out.println("third picture (threads): " + (middle3 - middle2) / 1000 + "sec");
		groupPicture_CubeAndThreads();
		var end = System.currentTimeMillis();
		out.println("last picture (cube and threads): " + (end - middle3) / 1000 + "sec");

		out.println(System.currentTimeMillis());
		out.println("Total time: " + (end - start) / 1000 + "sec");
	}


	public static void Helicopter() {
		Scene scene = new Scene("My Helicopter");
		Point p = new Point(0, 0, 57.5);

		Camera camera = new Camera(new Point(60, 0, -30), new Point(0,0,50)) //
				.setVPSize(500, 500).setVPDistance(5);

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
		//camera.moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30);
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

		camera.setImageWriter(new ImageWriter("check", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene))
				.renderImage() //
				.writeToImage("all");
/*
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

 */
	}


	public static void Helicopter1() {
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


	public static void TenObjects() {
		Scene scene = new Scene("My Helicopter");

		Camera camera = new Camera(new Point(60, 0, -30), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
				.setVPSize(500, 500).setVPDistance(1000);

		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
				.setKl(0.0004).setKq(0.0000006));
		scene.lights.add(new PointLight(new Color(200, 0, 0), new Point(25, 25, 25))
				.setKl(0.0009).setKq(0.00006));
		scene.ambientLight = new AmbientLight(new Color(35, 70, 120), 0.3);
		scene.background = new Color(120, 170, 150);

		double j;
		double i;
		Point p = new Point(0, 0, 57.5);
		camera.moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30);


		for (j = 0; j < 361 * 5; j = j + 10) {
			i = (j / 360) * 2 * Math.PI;
			scene.geometries = new Geometries(
					new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))
							.setEmission(Color.RED)
							.setMaterial(new Material()),
					new Sphere(new Point(0, 0, 50), 7)
							.setEmission(Color.BLUE)
							.setMaterial(new Material().setKt(0.3).setKr(0.1).setKd(0.4).setKs(0.2).setShininess(3)),
					new Sphere(new Point(0, 0, 58), 1)
							.setEmission(Color.GREEN)
							.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
					new Triangle(new Point(0, 7, 50), new Point(0, 10, 55), new Point(0, 10, 45))
							.setEmission(new Color(214, 78, 156))
							.setMaterial(new Material().setKr(0.7).setKd(0.3).setKs(0.3).setShininess(2)),
					// KNAFAIM
					new Triangle(p, new Point(10 * Math.sin(i), 10 * Math.cos(i), 57.5), new Point(10 * Math.sin(i + 1), 10 * Math.cos(i + 1), 57.5))
							.setEmission(Color.GREEN)
							.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
					new Triangle(p, new Point(10 * Math.sin((Math.PI) / 2 + i), 10 * Math.cos((Math.PI) / 2 + i), 57.5), new Point(10 * Math.sin((Math.PI) / 2 + i + 0.3), 10 * Math.cos((Math.PI) / 2 + i + 0.3), 57.5))
							.setEmission(Color.GREEN)
							.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
					new Triangle(p, new Point(10 * Math.sin((Math.PI) + i), 10 * Math.cos((Math.PI) + i), 57.5), new Point(10 * Math.sin((Math.PI) + i + 0.3), 10 * Math.cos((Math.PI) + i + 0.3), 57.5))
							.setEmission(Color.GREEN)
							.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)),
					new Triangle(p, new Point(10 * Math.sin((Math.PI) * 3 / 2 + i), 10 * Math.cos((Math.PI) * 3 / 2 + i), 57.5), new Point(10 * Math.sin((Math.PI) * 3 / 2 + i + 0.3), 10 * Math.cos((Math.PI) * 3 / 2 + i + 0.3), 57.5))
							.setEmission(Color.GREEN)
							.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6))
			);
			camera.setImageWriter(new ImageWriter("helicopter" + j, 500, 500)) //
					.setRayTracer(new RayTracerBasic(scene)) //
					.renderImage() //
					.writeToImage("helicopter1");

		}


/*
		camera.moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30);

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

 */
	}

	public static void groupPicture() {

		/*
		camera.setImageWriter(new ImageWriter("group picture without effects", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(false)) //
				.renderImage() //
				.writeToImage("all");

		 */
		scene.geometries = new Geometries().addAll(cube.getGeometry());
		camera.setImageWriter(new ImageWriter("without improvements", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImage() //
				.writeToImage("Final");
		/*
		scene.geometries = new Geometries(cube,cube1);
		camera.setImageWriter(new ImageWriter("group picture with effects & cube", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImage() //
				.writeToImage("all");
		 */


	}
	public static void groupPicture_Cube() {
		/*
		camera.setImageWriter(new ImageWriter("group picture without effects", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(false)) //
				.renderImage() //
				.writeToImage("all");

		 */
		scene.geometries=new Geometries(cube);
		camera.setImageWriter(new ImageWriter("Cube", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImage()
				.writeToImage("Final");
	}
	public static void groupPictureWithThreads() {

		/*
		camera.setImageWriter(new ImageWriter("group picture without effects", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(false)) //
				.renderImage() //
				.writeToImage("all");

		 */
		scene.geometries = new Geometries().addAll(cube.getGeometry());
		camera.setImageWriter(new ImageWriter("threads", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImageWithThread()
				.writeToImage("Final");
		/*
		scene.geometries = new Geometries(cube,cube1);
		camera.setImageWriter(new ImageWriter("group picture with effects & cube", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImage() //
				.writeToImage("all");
		 */


	}
	public static void groupPicture_CubeAndThreads() {
		/*
		camera.setImageWriter(new ImageWriter("group picture without effects", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(false)) //
				.renderImage() //
				.writeToImage("all");

		 */
		scene.geometries=new Geometries(cube);
		camera.setImageWriter(new ImageWriter("Cube+Threads", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImageWithThread()
				.writeToImage("Final");
	}
	public static void setSceneAndCamera(){
		scene = new Scene("new group picture");

		camera = new Camera(new Point(0, -10, 30), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setVPSize(500, 500).setVPDistance(500).rotationUp(-25);

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		//	scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
		//			.setKl(0.0004).setKq(0.0000006));
		Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
		Point lightPoint =  new Point(-50, 0, 200);
		Point sphre1Point = new Point(-8,20,4);
		scene.lights.add(new SpotLight(Color.WHITE.scale(0.1),lightPoint ,sphre1Point.subtract(lightPoint) )
				.setKl(1E-15).setKq(1.5E-21));
		//scene.ambientLight = new AmbientLight(new Color(35, 70, 120), 0.3);
		scene.background = Color.GREEN;
		Cube cube1 = new Cube(new Point(-13,10,0), new Point(15,90,40));

		//sphere adding
		cube1.add(
				new Cube(new Point(-11,17,1),new Point(-5,23,7)).add(
						new Sphere(sphre1Point,3)
								.setEmission(Color.BLUE.scale(0.3))
								.setMaterial(new Material().setKd(0.15).setKs(1).setShininess(70).setKr(0.6))),
				new Cube(new Point(-3,20,9),new Point(6,26,15)).add(
						new Sphere(new Point(0,23,12),3)
								.setEmission(Color.BLUE.scale(0.5))
								.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(10).setKt(0.8).setKr(0.5))),
				new Cube(new Point(-8,35,12),new Point(6,41,18)).add(
						new Triangle(new Point(6,35,12),new Point(-8,41,12),new Point(0,41,18))
								.setEmission(Color.BLUE.scale(0.5))
								.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(5).setKt(0.8).setBlurry(5))),
				new Cube(new Point(-14,75,12), new Point(12,80,35)).add(
						new Triangle(new Point(12,80,12),new Point(-14,80,12),new Point(0,75,35))
								.setEmission(Color.BLACK)
								.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(5).setKr(1).setGlossy(8)))
		);
		// the floor
		Material mat = new Material().setKd(1).setKs(0.5).setShininess(2);
		int size = 15;
		Cube cube2 = new Cube(new Point(-50,0,-0.1), new Point(50+size,100+size,0.1));
		for (int x = -50; x < 50; x += size) {
			for (int y = 0; y < 100; y += size) {
				if ((x + y+50) % (2*size) == 0)
					cube2.add(
							new Cube(new Point(x,y,-0.1),new Point(x+size,y+size,0.1)).add(
									new Triangle(new Point(x, y, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
											.setEmission(Color.RED.scale(0.6))
											.setMaterial(mat),
									new Triangle(new Point(x + size, y + size, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
											.setEmission(Color.RED.scale(0.6))
											.setMaterial(mat))
					);
				else
					cube2.add(new Cube(new Point(x,y,-0.1),new Point(x+size,y+size,0.1)).add(
							new Triangle(new Point(x, y, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.YELLOW.scale(0.6))
									.setMaterial(mat),
							new Triangle(new Point(x + size, y + size, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.YELLOW.scale(0.6))
									.setMaterial(mat))

					);
			}
		}
		cube=new Cube(new Point(-50,0,-0.1),new Point(50+size,100+size,35));
		cube.add(cube1,cube2);
	}

	public static void groupPictureThread() {
		Scene scene = new Scene("new group picture");

		Camera camera = new Camera(new Point(0, -10, 30), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setVPSize(500, 500).setVPDistance(500).rotationUp(-25);

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		//	scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
		//			.setKl(0.0004).setKq(0.0000006));
		Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
		Point lightPoint =  new Point(-50, 0, 200);
		Point sphre1Point = new Point(-8,20,4);
		scene.lights.add(new SpotLight(Color.WHITE.scale(0.1),lightPoint ,sphre1Point.subtract(lightPoint) )
				.setKl(1E-15).setKq(1.5E-21));
		//scene.ambientLight = new AmbientLight(new Color(35, 70, 120), 0.3);
		scene.background = Color.GREEN;

		//sphere adding
		scene.geometries.add(
				new Sphere(sphre1Point,3)
						.setEmission(Color.BLUE.scale(0.3))
						.setMaterial(new Material().setKd(0.15).setKs(1).setShininess(70).setKr(0.6)),
				new Sphere(new Point(0,23,12),3)
						.setEmission(Color.BLUE.scale(0.5))
						.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(10).setKt(0.8).setKr(0.5)),
				new Triangle(new Point(6,35,12),new Point(-8,41,12),new Point(0,41,18))
						.setEmission(Color.BLUE.scale(0.5))
						.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(5).setKt(0.8).setBlurry(5)),
				new Triangle(new Point(12,80,12),new Point(-14,80,12),new Point(0,75,35))
						.setEmission(Color.BLACK)
						.setMaterial(new Material().setKd(0.25).setKs(0.50).setShininess(5).setKr(1).setGlossy(8))
		);
		// the floor
		Material mat = new Material().setKd(1).setKs(0.5).setShininess(2);
		int size = 5;
		for (int x = -50; x < 50; x += size) {
			//	for (int x = -20; x < 20; x += size) {
			for (int y = 0; y < 100; y += size) {
				//		for (int y = 0; y < 60; y += size) {
				if ((x + y+50) % (2*size) == 0)
					scene.geometries.add(
							new Triangle(new Point(x, y, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.RED.scale(0.6))
									.setMaterial(mat),
							new Triangle(new Point(x + size, y + size, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.RED.scale(0.6))
									.setMaterial(mat)
					);
				else
					scene.geometries.add(
							new Triangle(new Point(x, y, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.YELLOW.scale(0.6))
									.setMaterial(mat),
							new Triangle(new Point(x + size, y + size, 0), new Point(x + size, y, 0), new Point(x, y + size, 0))
									.setEmission(Color.YELLOW.scale(0.6))
									.setMaterial(mat)

					);
			}
		}

		camera.setImageWriter(new ImageWriter("group picture without effects- with threads", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(false)) //
				.renderImageWithThread() //
				.writeToImage("all");
		/*
		camera.setImageWriter(new ImageWriter("group picture with effects- with threads", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setGlussyAndBlurry(true)) //
				.renderImageWithThread() //
				.writeToImage("all");

		 */
	}

	public static void DNA() {
		Scene scene = new Scene("DNA");
		Camera camera = new Camera(new Point(30, 5, 10), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
				.setVPSize(500, 500).setVPDistance(50)
				.moveUp(700).rotationUp(-10);

		double angle;
		double x1,y1,x2,y2;
		int OUT_SIZE = 10;
		int IN_SIZE = 10;
		int R = 100;
		for (double i = 0 ; i <360*3; i+=20) {
			angle = (i / 360) * 2 * Math.PI;
			x1 = R * Math.cos(angle);
			y1 = R * Math.sin(angle);
			x2 = R * Math.cos(angle + Math.PI);
			y2 = R * Math.sin(angle + Math.PI);
			if (i % 100 == 0) {
				scene.geometries.add(
						new Sphere(new Point(x1, y1, i), OUT_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.RED)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
				scene.geometries.add(
						new Sphere(new Point(x2, y2, i), OUT_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.RED)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
				scene.geometries.add(
						new Sphere(new Point((x1 + x2) / 2, (y1 + y2) / 2, i), IN_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.BLUE)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
				scene.geometries.add(
						new Sphere(new Point(x1 / 4 + x2 * (0.75), y1 / 4 + y2 * 0.75, i), IN_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.BLUE)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
				scene.geometries.add(
						new Sphere(new Point(x2 / 4 + x1 * (0.75), y2 / 4 + y1 * 0.75, i), IN_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.BLUE)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
			}
			else{
				scene.geometries.add(
						new Sphere(new Point(x1, y1, i), OUT_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.RED)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
				scene.geometries.add(
						new Sphere(new Point(x2, y2, i), OUT_SIZE)
								//	.setEmission(new Color((68+(7*i))%256,(179+(3*i))%256,(245+(6*i))%256))
								.setEmission(Color.RED)
								.setMaterial(new Material().setKr(0.1).setKd(0.4).setKs(0.8).setShininess(6)));
			}
		}


		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));
		scene.lights.add( //
				new PointLight(new Color(200, 0, 0), new Point(25,25,25) )
						.setKl(0.0009).setKq(0.00006));

		scene.ambientLight= new AmbientLight(new Color(35,70,120),0.3);
		scene.background = new Color(240,170,150);


		camera.setImageWriter(new ImageWriter("DNA", 500, 500))
				.moveRight(-70).moveForward(-200).moveUp(100).rotationLeft(-30)
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	public static void testGlossy() {
		//     private static final double DISTANCE=10
		//    private static final double GRID_SQUARE_SIZE =0.1
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1).setGlossy(15)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("test glossy", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage("all");
	}

	public static void testBlurry() {
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1).setBlurry(7)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("test blurry", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage("all");
	}
}