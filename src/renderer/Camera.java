package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {

    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height;
    private double width;
    private double distance;
    private ImageWriter iw;
    private RayTracerBase rtb;

    /**
     * getter for Point p0.
     *
     * @return value of p0.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for Vto.
     *
     * @return Vto value (as Vector instance).
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * getter for Vup.
     *
     * @return Vup value (as Vector instance).
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * getter for Vright.
     *
     * @return Vright value (as Vector intance).
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * getter for height field, representing the view plane height.
     *
     * @return Height of view plane (double).
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter for Width, representing Width of veiw plane.
     *
     * @return Weidth of view plane (double).
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for Distance field, representing the distance of the view plane from the camera.
     *
     * @return Distance of view plane from camera (double).
     */
    public double getDistance() {
        return distance;
    }

    /**
     * ctor for camra.
     *
     * @param location Point type, rpreseting the camera location in space.
     * @param vTo      Vector type, representing the camera direction of viewing.
     * @param vUp
     */
    public Camera(Point location, Vector vTo, Vector vUp) {
        if (vTo.dotProduct(vUp) != 0)
            throw new IllegalArgumentException();
        //#####we should catch this expception some where!!!!!#########
        this.p0 = location;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        //should it be toxup or upxto?
        this.vRight = this.vTo.crossProduct(this.vUp);
        iw = null;
        rtb = null;
    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Camera setImageWriter(ImageWriter iw) {
        this.iw = iw;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rtb) {
        this.rtb = rtb;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        //image center.
        Point pc = p0.add(vTo.scale(distance));
        //ratio.
        double Ry = alignZero(height / nY);
        double Rx = alignZero(width / nX);
        //pixel[i,j].
        double Xj = alignZero(Rx * (j - (nX - 1d) / 2d));
        double Yi = alignZero(-Ry * (i - (nY - 1d) / 2d));
        //Point Pij=pc.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        Point Pij = pc;
        if (!isZero(Xj))
            Pij = Pij.add(vRight.scale(Xj));
        if (!isZero(Yi))
            Pij = Pij.add(vUp.scale(Yi));
        return new Ray(p0, Pij.subtract(p0));
        //return null;
    }

    public void renderImage() {
        if (height == 0 || width == 0 || iw == null || rtb == null) {
            throw new MissingResourceException("not enough variables.", "Camera", "1");
        }
        for (int j = 0; j < iw.getNx(); j++)
            for (int i = 0; i < iw.getNy(); i++) {
                Color c = castRay(j,i);
                iw.writePixel(i, j, c);
            }
    }

    private Color castRay(int i, int j) {
        return rtb.traceRay(constructRay(iw.getNx(), iw.getNy(), j, i));
    }

    public void printGrid(int interval, Color color) {
        //check if iw is not empty first.
        if (iw == null)
            throw new MissingResourceException("no image writer value", "Camera", "2");
        for (int j = 0; j < iw.getNx(); j += interval) {
            for (int i = 0; i < iw.getNy(); i++) {
                iw.writePixel(j, i, color);
            }
        }
        for (int i = 0; i < iw.getNx(); i += interval) {
            for (int j = 0; j < iw.getNy(); j++) {
                iw.writePixel(j, i, color);
            }
        }
    }

    public void writeToImage() {
        //check if iw is not empty first.
        if (iw == null)
            throw new MissingResourceException("no image writer value", "Camera", "3");
        iw.writeToImage();
    }
}
