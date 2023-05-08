package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Interface for geometric objects that intersect with a ray in 3D space
 */
public interface Intersectable {

    /**
     * find all intersection {@link Point}s between ray and an object (geometry)
     *
     * @param ray ray towards the object
     * @return immutable list of intersection points
     */
    public List<Point> findIntsersections(Ray ray);
}
