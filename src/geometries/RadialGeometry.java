package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    final protected double radius;


    public RadialGeometry(double radius) {
        this.radius = radius;
    }


    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }


}
