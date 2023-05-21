package geometries;

import primitives.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
// origin point of cylinder (on bottom base)
        Point basePoint = axis.getP0();
        // point across base point on top base
        Point topPoint = axis.getPoint(height);
        // direction vector of cylinder (orthogonal to base point)
        Vector vC = axis.getDir();

        // find intersection points of ray with bottom base of cylinder
        List<Point> result= new LinkedList<>();
        // crate plane that contains base point in it
        Plane basePlane= new Plane(basePoint,vC);
        // find intersection between ray and plane
        List<Point> intersectionsBase = basePlane.findIntersections(ray);

        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from base point to intersection point holds the equation ->  distance² < from radius²
        if(intersectionsBase!=null){
            for (Point p:intersectionsBase) {
                Point pt = p;
                // intersection point is the base point itself
                if(pt.equals(basePoint))
                    result.add(basePoint);
                    // intersection point is different to base point but is on the bottom base
                else if(pt.subtract(basePoint).dotProduct(pt.subtract(basePoint)) < radius * radius)
                    result.add(pt);
            }
        }

        // find intersection points of ray with bottom base of cylinder
        // crate plane that contains top point in it
        Plane topPlane= new Plane(topPoint,vC);
        // find intersection between ray and plane
        List<Point> intersectionsTop=topPlane.findIntersections(ray);
        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from top point to intersection point holds the equation ->  distance² < from radius²
        if(intersectionsTop!=null){
            for (var p:intersectionsTop) {
                Point pt = p;
                // intersection point is the top point itself
                if(pt.equals(topPoint))
                    result.add(topPoint);
                    // intersection point is different to base point but is on the bottom base
                else if(pt.subtract(topPoint).dotProduct(pt.subtract(topPoint)) < radius * radius)
                    result.add( pt );
            }
        }

        // if rsy intersects both bases , no other intersections possible - return the result list
        if (result.size()==2)
            return result;

        // use tube parent class function to find intersections with the cylinder represented
        // as an infinite tube
        List<Point> intersectionsTube=super.findIntersections( ray );

        // if intersection points were found check that they are within the finite cylinder's boundary
        // by checking if  scalar product fo direction vector with a vector from intersection point
        // to bottom base point is positive, and scalar product of direction vector with a
        // vector from intersection point to top base point is negative
        if(intersectionsTube!=null){
            for (var p:intersectionsTube){
                Point pt = p;
                if(vC.dotProduct(pt.subtract(basePoint))>0 && vC.dotProduct(pt.subtract(topPoint)) < 0)
                    result.add( pt );
            }
        }

        // return an immutable list
        int len = result.size();
        if(len>0)
            if (len ==1)
                return List.of(result.get(0));
            else
                return List.of(result.get(0), result.get(1));

        // no intersections
        return null;
    }
}
