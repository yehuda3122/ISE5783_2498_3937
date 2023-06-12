package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;


public class Ray {
    Point p0;
    Vector dir;
    /**
     * delta used to move a ray origin point by a small margin
     */
    private static final double EPS = 0.1;
    public Ray(Point point,Vector vector) {
        p0 = point;
        dir = vector.normalize();
    }

    /**
     * constructor - construct a ray slightly removed from a given point
     * @param p original point
     * @param normal normal vector to geometry at the point
     * @param dir vector of light direction
     */
    public Ray(Point p,Vector normal, Vector dir) {
        if (dir.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector (0,0,0) not valid");

        Vector epsVector = normal.scale(normal.dotProduct(dir) >0 ? EPS : - EPS);
        this.p0=p.add(epsVector);
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * find the closest point to ray origin from a list of points
     * @param pointList list of intersection points
     * @return the closest {@link Point}
     */
    public Point findClosestPoint(List<Point> pointList) {
        if (pointList==null)
            return null;

        Point result =null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;
        for (Point pt : pointList) {
            ptDistance = p0.distance(pt);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                result = pt;
            }
        }
        return result;
    }


    /**
     * find the closest GeoPoint to ray origin from a list of GeoPoints
     * @param pointList list of intersection points
     * @return the closest {@link GeoPoint}
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointList) {
        if (pointList==null)
            return null;

        GeoPoint result =null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;
        for (var pt : pointList) {
            ptDistance = p0.distance(pt.point);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                result = pt;
            }
        }
        return result;
    }

    /**
     * creating a {@link Point} at a specific distance in the ray's direction
     *
     * @param t scale factor
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        if (isZero(t))
            return p0;
        return p0.add(dir.scale(t));
    }



}
