package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final Point p;
    final Vector normal;
    public Plane(Point p1, Point p2, Point p3) {
        p = p1;
        Vector V1 = p1.subtract(p2);
        Vector V2 = p1.subtract(p3);
        normal = V1.crossProduct(V2);
    }

    public Plane (Point p1, Vector normal){
        p =p1;
        this.normal = normal;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }
}
