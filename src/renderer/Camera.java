package renderer;

import primitives.*;

public class Camera {
    private Point location;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height;
    private double width;
    private double distance;

    public Vector getVTo() {
        return vTo;
    }

    public Vector getVUp() {
        return vUp;
    }

    public Vector getVRight() {
        return vRight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public Camera(Point location, Vector vTo, Vector vUp) {
        if (vTo.dotProduct(vUp) != 0)
            throw new IllegalArgumentException();
        this.location = location;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vUp.crossProduct(vTo).normalize();
    }

    public Camera setVPSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
        return null;
    }
}
