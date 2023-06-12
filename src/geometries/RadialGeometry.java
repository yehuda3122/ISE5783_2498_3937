package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public abstract class RadialGeometry extends Geometry {
    final protected double radius;


    public RadialGeometry(double radius) {
        this.radius = radius;
    }


    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }


}
