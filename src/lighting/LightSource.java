package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

public interface LightSource {
    public Color getIntensity(Point p);
    public Vector getL(Point p);

    /**
     * get a beam of rays from a point on a geometry towards a light,
     * all the rays are constructed within the soft shadow radius boundary
     *
     * @param p point on the geometry
     * @return {@link List}of rys from the geometry to the soft shadow radius
     * @author Yona Shmerla
     */
    public List<Vector> getListL(Point p);
    /**
     * get the distance between a light source to a given point
     * @param p {@link Point} to calculate distance to
     * @return distance
     */
    public double getDistance(Point p);


}
