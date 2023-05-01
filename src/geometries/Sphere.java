package geometries;

import primitives.Point;
import primitives.Vector;

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
        return super.getNormal(point);
    }
}
