package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    List<Intersectable> shapes;

   public Geometries(){
        shapes=new LinkedList<Intersectable>();
        //we choose linked list because the complexity of adding and deleting an object is better than array list.
    }

    /**
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        shapes= List.of(geometries);
    }


    /**
     * @param geometries
     */
    public void add(Intersectable... geometries){
        for (Intersectable i:geometries) {
            shapes.add(i);
        }
    }

    /**
     * function finds intersaections between ray and the geometric shape (or entity).
     *
     * @param ray ray is shots from camera.
     * @return a list of all the points that are on geometric shapes that our ray intersects with
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
