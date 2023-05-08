package geometries;

import primitives.*;

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

}