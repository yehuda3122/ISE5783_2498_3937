package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**

 The Plane class represents an infinite two-dimensional plane in three-dimensional space.

 It implements the Geometry interface.
 */
public class Plane implements Geometry {
    final Point p;
    final Vector normal;

    /**

     Creates a new Plane object from three points that lie on the plane.
     @param p1 the first point on the plane
     @param p2 the second point on the plane
     @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        p = p1;
        Vector V1 = p1.subtract(p2);
        Vector V2 = p1.subtract(p3);
        normal = V1.crossProduct(V2).normalize();
    }
    /**

     Creates a new Plane object from a point on the plane and the plane's normal vector.
     @param p1 a point on the plane
     @param normal the plane's normal vector
     */
    public Plane(Point p1, Vector normal) {
        p = p1;
        this.normal = normal.normalize();
    }
    /**

     Gets the normal vector of the plane.
     @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }
    /**

     Gets the normal vector of the plane at the specified point.
     @param point the point at which to get the normal vector
     @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
        //return normal;
    }
    /**

     Returns a string representation of the plane.
     @return a string representation of the plane
     */
    @Override
    public String toString() {
        return "Plane: p=" + p + ", normal=" + normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        // ray cannot start at plane's origin point
        if(p.equals(P0))
            return null;

        // ray points -> P = p0 + t*v_ (v_ = direction vector)
        // points on plane  if normal vector dot product with vector from
        // origin point to proposed point == 0
        // glossary:  (n,v) = dot product between vectors n,v
        // isolating t ,( scaling factor for ray's direction vector ) ->
        // t = (normal vector, vector from origin to point)/ (normal vector, ray vector)
        // if t is positive ray intersects plane
        double nv = n.dotProduct(v);

        // ray direction cannot be parallel to plane orientation
        if (isZero(nv)){
            return null;
        }

        // vector from origin to point
        Vector Q_P0 = p.subtract(P0);

        double nQMinusP0 = alignZero(n.dotProduct(Q_P0));

        //t should not be equal to 0
        if( isZero(nQMinusP0)){
            return null;
        }

        // scaling factor for ray , if value is positive
        // ray intersects plane
        double t = alignZero(nQMinusP0 / nv);
        if (t > 0 ){
            //return immutable List
            return List.of(ray.getPoint(t));
        }
        // no intersection point  - ray and plane in opposite  direction
        return null;
    }

}
