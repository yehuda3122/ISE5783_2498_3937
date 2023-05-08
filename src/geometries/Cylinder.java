package geometries;

import primitives.*;
import primitives.*;

import static primitives.Util.isZero;

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
        Vector direction = axis.getDir();
        Point P0 = axis.getP0();

        //given point is on base of cylinder
        if(point.equals(P0)||isZero(point.subtract(P0).dotProduct(direction)))
            return direction.normalize();


        // given point is on top base of the cylinder
        if (point.equals(P0.add(direction.scale((int) height)))||isZero(point.subtract(P0.add(direction.scale((int) height))).dotProduct(direction)))
            return direction.normalize();

        // given point is on the circumference of cylinder
        return super.getNormal(point);
    }
}
