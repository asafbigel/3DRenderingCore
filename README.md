# 3D Rendering Core

## Overview

**3D Rendering Core** is a powerful Java-based rendering engine designed to demonstrate the fundamental principles of 3D graphics. This project implements a ray tracing algorithm from scratch, complete with advanced features like reflection, refraction, and performance optimizations. It serves as a practical guide to understanding the mathematics and logic behind creating realistic 3D scenes.

---

## Key Features

### 🧱 Geometric Primitives
The engine supports a variety of fundamental shapes that can be combined to create complex scenes:
- **Core Geometries:** `Sphere`, `Plane`, `Triangle`, `Tube`, and `Cylinder`.
- **Composite Objects:** A `Cube` implementation that can contain other geometries.
- **Collections:** The `Geometries` class acts as a container to manage multiple shapes as a single entity.

### 💡 Lighting and Shading
Our lighting model brings scenes to life with realistic illumination:
- **Light Sources:** Support for `Ambient`, `Directional`, `Point`, and `Spot` lights.
- **Phong Illumination:** A full implementation of the Phong reflection model, which calculates `diffuse` and `specular` highlights to simulate how light interacts with surfaces.

### 🎨 Materials and Effects
Customize the appearance of objects with a flexible material system:
- **Material Properties:** Control attributes like `diffuse` and `specular` colors, `shininess`, `transparency`, and `reflectivity`.
- **Advanced Effects:** Simulate `glossy` surfaces and `blurry` refractions through sophisticated ray beam sampling techniques.

### 🎥 Camera and Viewing
A versatile camera model allows for complete control over the viewpoint:
- **Flexible Placement:** Position and orient the camera anywhere in the 3D space.
- **Customizable View Plane:** Adjust the size and distance of the view plane to control the field of view.
- **Image Generation:** The `ImageWriter` class handles the process of rendering the scene to an image file.

### 🔍 Ray Tracing Engine
The core of the project is a carefully implemented ray tracer with advanced capabilities:
- **Basic Ray Tracing:** The `RayTracerBasic` class implements the fundamental ray casting algorithm.
- **Recursive Tracing:** Achieves realistic reflections and refractions by recursively tracing rays as they bounce through the scene.
- **Shadows:** Accurately calculates hard and soft shadows, taking into account the transparency of objects.

### 🚀 Performance Optimizations
To handle complex scenes efficiently, the engine includes key optimizations:
- **Multi-Threading:** The `renderImageWithThread` method significantly speeds up render times by distributing the workload across multiple CPU cores.
- **Bounding Volume Hierarchy (BVH):** A BVH is used to accelerate the process of finding ray-object intersections, especially for composite objects like the `Cube`.

### 🧰 Utility Classes
A set of primitive classes provides the mathematical foundation for the engine:
- **Core Types:** `Color`, `Double3`, `Point`, `Vector`, `Ray`, and `Util` for floating-point comparisons.

---

## Project Structure

The project is organized into a clean and modular structure:

```
3DRenderingCore/
├── src/
│   ├── Main.java             // Main executable for rendering scenes
│   ├── geometries/           // Geometric shapes (Sphere, Plane, etc.)
│   ├── lighting/             // Light sources and shading models
│   ├── primitives/           // Core math and utility classes
│   ├── renderer/             // Camera, RayTracer, and ImageWriter
│   └── scene/                // Scene class to hold all elements
└── unittests/                // JUnit tests for all components
```

---

## Getting Started

### ✅ Prerequisites
- **Java JDK 17** or higher.
- **JUnit 5** for running the unit tests.

### ▶️ Build and Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/asafbigel/3DRenderingCore.git
   cd 3DRenderingCore
   ```
2. **Open in your favorite IDE:**
   - Import the project as a standard Java project.
   - Make sure the IDE is configured to use JDK 17.
3. **Run the Renderer:**
   - Open the `src/Main.java` file.
   - Run the `main()` method to render the predefined scenes.
   - The output images will be saved in a newly created `images/` directory at the project root.
4. **Run Unit Tests:**
   - Navigate to the `unittests/` directory.
   - Run the test classes individually or as a suite to verify the functionality of the engine components.

---

## Code Examples

The `Main.java` file contains several methods that demonstrate the engine's capabilities. To render a specific scene, simply uncomment the corresponding method call in the `main()` method.

- **`groupPicture()`:** Renders a scene with multiple objects and advanced effects like reflection and transparency, comparing the performance of different optimizations (multi-threading, BVH).
- **`Helicopter()`:** Renders a helicopter model built from various geometric primitives, showcasing how to create complex objects.
- **`DNA()`:** Creates a DNA-like spiral structure using spheres, demonstrating the use of mathematical formulas to place objects in a scene.
- **`testGlossy()` and `testBlurry()`:** These methods specifically test the glossy and blurry material effects on simple scenes.

You can create your own scenes by adding new methods to `Main.java` and building a `Scene` object with your desired geometries, lights, and camera setup.

---

## Authors

- **Ron Shaull**
- **Asaf Bigel**

