package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry{
    final protected Ray axis;
    public Tube(Ray axis,double radius) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector direction = axis.getDir();
        Point P0 = axis.getP0();

        // find distance of point from origin and scale direction
        // to get the point on tube parallel to given point
        double t = (direction.dotProduct(point.subtract(P0)));
        Point O = P0.add(direction.scale((int) t));

        //given point is on axis ray
        if (point.equals(O))
            throw new IllegalArgumentException("point cannot be on the axis ray");

        // point is against tube origin point
        if (isZero(t))
            return point.subtract(P0).normalize();
            // any other point
        else
            return point.subtract(O).normalize();
    }
}
