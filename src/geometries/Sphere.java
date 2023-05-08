package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere extends RadialGeometry{
    final public Point center;
    public Sphere(Point center,double radius) {
        super(radius);
        if (radius < 0)
            throw new IllegalArgumentException("radius must b greater then 0");
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    public List<Point> findIntsersections(Ray ray) {
        return null;
    }


}
