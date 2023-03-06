package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane {
    protected double radius;

    public Plane(double radius) {
        this.radius = radius;
    }

    public Plane(Point vertex, Point vertex1, Point vertex2) {
    }

    public Vector getNormal() {
    }
}
