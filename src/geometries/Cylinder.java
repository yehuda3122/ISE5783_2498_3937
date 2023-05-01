package geometries;

import primitives.*;
import primitives.*;

public class Cylinder extends Tube {

    final public double height;
    public Cylinder(double radius, double height,Ray axis) {
        super(axis, radius);
        if (radius < 0 && height < 0)
            throw new IllegalArgumentException("height and radius must b greater then 0");
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
