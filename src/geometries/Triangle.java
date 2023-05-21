package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**

 The Triangle class represents a two-dimensional polygon with three vertices.

 It is a subclass of the Polygon class.
 */
public class Triangle extends Polygon{

    final Point p0;
    final Point p1;
    final Point p2;
    /**

     Creates a new Triangle object with the specified vertices.
     @param p1 the first vertex of the triangle
     @param p2 the second vertex of the triangle
     @param p3 the third vertex of the triangle
     */
    public Triangle(Point pa, Point pb, Point pc) {
        super(pa, pb, pc);
        this.p0 = pa;
        this.p1 = pb;
        this.p2 = pc;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "p0=" + p0 +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    public List<Point> findIntersections(Ray ray){
        // check if ray intersects plane containing the triangle
        List<Point> result = plane.findIntersections(ray);
        // no intersections
        if (result == null)
            return null;

        // check if intersection points are in Triangle
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        // create three vectors between ray origin and
        //each of triangle vertices
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);

        // n1,n2 ,n3 = value of dot product between ray vector
        // and the result vector of cross product between pairs
        // of vectors from ray origin and triangle vertices
        // if n1 or n2 pr m3 == 0 - intersection on border -> no intersection

        double n1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(n1))
            return null;

        double n2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(n2))
            return null;

        double n3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(n3))
            return null;

        // if sign of all three values ,n1 ,n2 ,n3 is not equal
        // intersection point is not on triangle
        if (!((n1 < 0 && n2 < 0 && n3 < 0 )||( n1 > 0 && n2 > 0 && n3 > 0)))
            return null;

        // ray intersects triangle
        return List.of(result.get(0));

    }

}