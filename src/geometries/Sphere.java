package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry{
    final public Point center;
    public Sphere(Point center,double radius) {
        super(radius);
        if (radius <= 0)
            throw new IllegalArgumentException("radius must b greater then 0");
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {

        return point.subtract(center).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        // ray starts at center point of sphere
        // return point on surface in direction of ray
        if (P0.equals(center)) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        // vector from ray origin to center point
        Vector U = center.subtract(P0);

        // tm = U's projection on ray's vector
        double tm = alignZero(v.dotProduct(U));
        // d between u and ray (at center point)
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        //distance from center to ray is larger than the radius
        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        // th = distance from ray intersection point to point
        // reached by scaling ray vector by tm
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        // t1 = size of distance from first intersection point to
        //  point reached by ray scaled by tm
        double t1 = alignZero(tm - th);
        // t2 = size of distance from point reached by ray scaled by tm
        // to second intersection point
        double t2 = alignZero(tm + th);

        // check that distance from ray origin to intersection points
        // is smaller than max distance parameter set by user
        boolean distanceT1 = alignZero(t1 - maxDistance) <= 0;
        boolean distanceT2 = alignZero(t2 - maxDistance) <= 0;

        // ray constructed outside sphere
        // two intersection points
        if (t1 > 0 && t2 > 0 && distanceT1 && distanceT2) {
            Point P1 =ray.getPoint(t1);    //ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);                      //ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1),new GeoPoint(this, P2));
        }
        // ray constructed inside sphere and intersect in back direction
        if (t1 > 0 && distanceT1) {
            Point P1 =P0.add(v.scale(t1));            //ray.getPoint(t1);
            return List.of(new GeoPoint(this,P1));
        }
        // ray constructed inside sphere and intersect in forward direction
        if (t2 > 0 && distanceT2) {
            Point P2 =P0.add(v.scale(t2));          //ray.getPoint(t2);
            return List.of(new GeoPoint(this,P2));
        }
        // no intersection points found - assurance return
        // code should not be reaching this point
        return null;

    }


}
