# 3DRenderingCore

## Overview

**3DRenderingCore** is a Java-based 3D rendering engine implementing fundamental components for defining and rendering 3D scenes. It features a basic ray tracing algorithm with support for advanced effects such as reflection, refraction, glossiness, blur, and multi-threaded rendering for enhanced performance.

---

## Key Features

### 🧱 Geometric Primitives

- **Sphere**, **Plane**, **Triangle**, **Tube**, **Cylinder**
- **Cube** with support for nested geometries
- `Geometries` class to manage collections of shapes

### 💡 Lighting Models

- Ambient, Directional, Point, and Spot Lights
- Full implementation of the **Phong lighting model** (diffuse and specular reflections)

### 🎨 Materials & Effects

- Configurable material properties: diffuse, specular, shininess, transparency, and reflectivity
- Support for **glossy** and **blurry** surfaces via ray beam sampling

### 🎥 Camera System

- Flexible positioning and orientation
- Customizable view plane (size and distance)
- Pixel-wise ray construction
- Image rendering via `ImageWriter`

### 🔍 Ray Tracing Engine

- Basic ray tracing (`RayTracerBasic`)
- Recursive tracing for **reflections** and **refractions**
- Accurate **shadow casting**, including partial transparency

### 🚀 Performance Optimizations

- **Multi-threaded rendering** (`renderImageWithThread`)
- **Bounding Volume Hierarchy (BVH)** for efficient cube intersections

### 🧰 Utility Classes

- `Color`, `Double3`, `Point`, `Vector`, `Ray`, `Util`
- Core mathematical primitives and floating-point utilities

---

## Project Structure

```
ISE5782_3571_5125-master/
├── src/
│   ├── Main.java
│   ├── geometries/         // Shapes: Sphere, Plane, Triangle, etc.
│   ├── lighting/           // Light sources and ambient light
│   ├── primitives/         // Math and color utilities
│   ├── renderer/           // Camera, ray tracing, image writer
│   └── scene/              // Scene class
├── unittests/              // JUnit test suite
│   ├── Integration, Reflection, Shadow, Spiral tests
│   ├── geometries/, lighting/, primitives/, renderer/
└── .idea/                  // IntelliJ configuration
```

---

## Getting Started

### ✅ Prerequisites

- Java **JDK 17** or higher
- **JUnit 5** (via `junit_jupiter.xml` in `.idea/libraries`)

### ▶️ Build & Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/asafbigel/3DRenderingCore.git
   cd 3DRenderingCore
   ```

2. **Open in IntelliJ IDEA (or another IDE):**

   - Import as a Java project (or use Maven/Gradle)
   - Set up JDK 17+
   - Ensure JUnit Jupiter is recognized

3. **Run the Renderer:**

   - Locate and run `Main.java` → `main()` method
   - Output images are saved in the `images/` directory

4. **Run Unit Tests:**

   - Navigate to the `unittests/` folder
   - Run specific test classes or the full suite

---

## Examples

The `Main.java` file contains sample scenes to demonstrate the engine’s capabilities:

- `groupPicture()` – Basic scene with cube
- `Helicopter()` – Helicopter model rendering
- `DNA()` – DNA-like spiral geometry
- `testGlossy()` / `testBlurry()` – Material visual effects

You can modify these or add your own scenes by extending `Main.java`.

---


## Authors

- **Ron Shaull**
- **Asaf Bigel**

