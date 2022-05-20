package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    //distance of virtual grid from intersection point, the level of distribution of rays is determine by grid length and width
    //according to the shape it is in.
    private static final double DISTANCE=1;
    private static final double GRID_SQUARE_SIZE =0.1;

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
        /*
        //create list of all intersection point with r.
        var intersections = scene.geometries.findGeoIntersections(r);
        //if there is no intersection then we see background color.
        if (intersections == null) return scene.background;
        //if we do have intersections, we only see the closest intersection point.
        GeoPoint closestPoint = r.findClosestGeoPoint(intersections);
         */
        GeoPoint closestPoint = findClosestIntersection(r,Double.POSITIVE_INFINITY);
        if (closestPoint == null) return scene.background;
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

    /**
     * calculate the color of a Point of a GeoPoint
     * @param gp the GeoPoint
     * @param ray the ray of intersection
     * @return the color of the point of the GeoPoint
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        /**
         * now we add the emission light, which is the light that scatter from shape surface.
         * remainder that we work by phong lighting model, that is additive.
         * so we simply ADD the emission light to point color.
         */
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K))
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * new function that we use, we added it so we could add secondary rays color to our
     * intersection point in case it is transparent, and reflective.
     * @param geopoint intersection geopoint.
     * @param ray intersection ray.
     * @param level of recursion, so we don't and up in an endless recursion.
     * @param k attenuation factor, for transparency.
     * @return point color. (additive phong model.)
     */
    private Color calcColor(GeoPoint geopoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geopoint, ray,k);
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDir(), level, k));
    }

    /**
     * function claculate the local effect of all light sources on one geopoint.
     *
     * @param intersection an intersection point on a specific shape, that we want to know it's color.
     * @param ray          the ray that we casted that intersected in that point.
     * @return the point color, so we could right its pixel to the image.
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));// if primary ray is parallel to object surface so its tangent to object surface,
        //we cannot see the intersection point, there for it returns black.
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getnShininess();
        Double3 kd = intersection.geometry.getMaterial().getkD(), ks = intersection.geometry.getMaterial().getkS();
        Color color = intersection.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            //color=color.add(lightSource.getIntensity(intersection.point));
            // if (nl * nv > 0 && unshaded(intersection, l, n, lightSource)) { // sign(nl) == sing(nv)
            if (nl * nv > 0 ) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(intersection, lightSource,l, n);
                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
                            calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                }

            }
        }
        return color;
    }

    /**
     * calculate the global effect of refraction and reflection and add it to the local
     * effect of the point color.
     * notice that if there is an intersection with the secondary rays so we call the calcolor for that point.
     * so we can add its color (time the attenuation factor) to our point.
     * @param gp the intersection point.
     * @param v light beam vector.
     * @param level for recursion stop condition.
     * @param k transparency attenuation factor.
     * @return Color type of the global effect on the original intersection point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        if (level < 2)
            return color;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);
        List<Ray>listOfRay;

        if (!kkr.lowerThan(MIN_CALC_COLOR_K)){
            Ray reflectedRay = constructReflectedRay(gp.point,v,n);
            GeoPoint reflectedPoint;
            Color colorReflected=Color.BLACK;
            listOfRay=reflectedRay.listOfRays(gp.geometry.getMaterial().glossy, GRID_SQUARE_SIZE,DISTANCE);
            for (Ray ray:listOfRay) {

                reflectedPoint= findClosestIntersection(ray,Double.POSITIVE_INFINITY);
                if (reflectedPoint != null)
                    //colorReflected.add(calcGlobalEffect(ray, level-1,material.kR, kkr));
                    colorReflected= colorReflected.add(calcGlobalEffect(ray, level-1,material.kR, kkr));
            }
            color=colorReflected.reduce(listOfRay.size());
        }
        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)){
            Ray refractedRay = constructRefractedRay(gp.point,v,n);
            listOfRay=refractedRay.listOfRays(gp.geometry.getMaterial().blurry, GRID_SQUARE_SIZE,DISTANCE);
            GeoPoint refractedPoint;
            Color blurryColor=Color.BLACK;
            for (Ray ray:listOfRay) {
                refractedPoint=findClosestIntersection(ray,Double.POSITIVE_INFINITY);
            if (refractedPoint != null)
                blurryColor=blurryColor.add(
                        calcGlobalEffect(ray, level-1,material.kT, kkt));
            }
            color = color.add(blurryColor.reduce(listOfRay.size()));
        }

        return color;
    }

    /**
     * for calculating the secondary intersection point colro.
     * @param ray the reflection or refraction ray.
     * @param level of recursion.
     * @param kx attenuation factor (of recursion)
     * @param kkx attenuation factor of the material. (we start with initial k=1 and go down by product.)
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray,Double.POSITIVE_INFINITY);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    /**
     * find closest intersection point to rays p0.
     * @param ray our casting ray.
     * @param maxDistance point that are farther then this distance wont be included.
     * @return GeoPoint type of the closest intersection point.
     */
    private GeoPoint findClosestIntersection(Ray ray,double maxDistance) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray,maxDistance));
    }

    /**
     * assistance function to construct the refracted rays in a reflective object.
     * @param point intersection point.
     * @param v light beam vector.
     * @param n object normal in intersection point.
     * @return Ray type of secondary refracted ray.
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point,v,n);
    }

    /**
     * assistance function to construct the reflected rays in a reflective object.
     * @param point intersection point.
     * @param v light beam vector.
     * @param n object normal in intersection point.
     * @return Ray type of secondary reflected ray.
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        //return new Ray (point.add(n.scale(DELTA)), v.subtract(n.scale(2*v.dotProduct(n))));
        return new Ray(point, v.subtract(n.scale(2*v.dotProduct(n))),n);
    }

    /**
     * function is used to cast shadow rays to see if intersection point is showed by any other object.
     * @param gp the intersection point (with the primary rays.)
     * @param light light source object (we check them one by one in other function0
     * @param l light source beam vector.
     * @param n object normal in intersection gp.
     * @return double3 that create a partial to full shadow depends on blocking object transparency.
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Double3 ktr = new Double3(1);
        Vector lightDirection = l.scale(-1); // from point to light source (shadow ray)

        Ray lightRay = new Ray(gp.point,lightDirection,n);
        // a ray from intersection point to light source, and if there is an intersection with other shape then piont of ray is shaded.

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,light.getDistance(gp.point));
        //only points that are between gp and light source can affect shadowing.
        if (intersections == null)
            return ktr;
        for (GeoPoint Geopoint : intersections) {
            //we multiply our attenuation factor with with every object that is between our object and light source,
            //so that if it is compliantly transparent than light travels threw it and ktr*1, and 0 in the opposite case.
            ktr = Geopoint.geometry.getMaterial().kT.product(ktr);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
        }
        return ktr;

        // loop over intersections and for each intersection which is closer to the
        // point than the light source multiply ktr by 𝒌𝑻 of its geometry
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

    /*
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

     */
}