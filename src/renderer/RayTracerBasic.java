package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.lang.Math;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * ctor for RayTracerBasic class, this class extends RayTracerBase class witch contains the
     * scene parameter.
     *
     * @param scene this parameter appears in father class.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * public method for returning the color of first
     * intersection point with Ray r,this ray is cast from camera instance towards the view plane.
     * and intersects with the 3d objects.
     *
     * @param r ray that we cast (from the camera instance).
     * @return Color instance with point rgb color values (represented in a double 3 type).
     */
    @Override
    public Color traceRay(Ray r) {
        //create list of all intersection point with r.
        var intersections = scene.geometries.findGeoIntersections(r);
        //if there is no intersection then we see background color.
        if (intersections == null) return scene.background;
        //if we do have intersections, we only see the closest intersection point.
        GeoPoint closestPoint = r.findClosestGeoPoint(intersections);
        //now we want to add color to point, so we use Geopoint.
        return calcColor(closestPoint, r);
        //calColor function is below.

    }

    /**
     * for now return the unified color of point from Ambient light class function
     * that uses light intensity.
     *
     * @param p Point of intersection (observable from camera).
     * @return Color type of this Point color.
     */
    private Color calcColor(Point p) {
        return scene.ambientLight.getIntensity();
    }

    private Color calcColor(GeoPoint gp, Ray ray,) {
        /**
         * now we add the emission light, which is the light that scatter from shape surface.
         * remainder that we work by phong lighting model, that is additive.
         * so we simply ADD the emission light to point color.
         */
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, MIN_CALC_COLOR_K)
                .add(scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {
        Color color = calcLocalEffects(geopoint, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDir(), level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.scale(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kr, kkr);
        Double3 kkt = material.kT.scale(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kt, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level – 1, kkx)
        ).scale(kx));
    }

    /**
     * function claculate the local effect of all light sources on one geopoint.
     *
     * @param intersection an intersection point on a specific shape, that we want to know it's color.
     * @param ray          the ray that we casted that intersected in that point.
     * @return the point color, so we could right its pixel to the image.
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getnShininess();
        Double3 kd = intersection.geometry.getMaterial().getkD(), ks = intersection.geometry.getMaterial().getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            //color=color.add(lightSource.getIntensity(intersection.point));
            if (nl * nv > 0 && unshaded(intersection, l, n, lightSource)) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));

            }
        }
        return color;
    }

    /**
     * function claculate the specular light effect of a light ray intersects with shape.
     * and add it to the phong additive model.
     *
     * @param ks             attenuation factor
     * @param l              light beam vector (from light source to shape).
     * @param n              normal of shape, in intersection point.
     * @param v              camera ray we cast.
     * @param nl             n*l
     * @param nShininess     how much does the shapes' surface area is reflective.
     * @param lightIntensity light source intensity.
     * @return Color type that is the specular effect of this point.
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * nl)).normalize();
        double d = alignZero(-v.dotProduct(r));
        if (d <= 0)
            return Color.BLACK;
        return lightIntensity.scale(ks.scale(Math.pow(d, nShininess)));
        //return lightIntensity.scale(ks * Math.pow(v.scale(-1).dotProduct(r),nShininess));
    }

    /**
     * function claculate the diffusive effect of a point that intersects with light beam.
     *
     * @param kd             attenuation factor.
     * @param nl             light beam vector (from light source to shape) * normal of shape, in intersection point.
     * @param lightIntensity light source intensity.
     * @return Color type, that is the deffusive effect of this point.
     */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        // double d = kd.scale(l.dotProduct(n));
        if (nl < 0) nl = -nl;
        return lightIntensity.scale(kd).scale(nl);
    }

    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA); //we want to know either we need to move in the direction of the normal or not
        //because the normal can be inwards.
        Point point = gp.point.add(epsVector);//moving point outside the shape so we can see it.

        Ray lightRay = new Ray(point, lightDirection);// a ray from intersection point to light source, and if there is an intersection with other shape then piont of ray is shaded.
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;
        double distance = lightSource.getDistance(gp.point);
        for (GeoPoint Geopoint : intersections) {
            if (gp.point.subtract(Geopoint.point).length() < distance)
                return false;
        }
        return true;
    }
}

