package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Interface for geometric objects that intersect with a ray in 3D space
 */
public abstract class  Intersectable {

    /**
     * class representing a point on/in a geometric shape
     */
    public static class GeoPoint {
        /**
         * geometric shape
         */
        public Geometry geometry;
        /**
         * point in/on the geometric shape
         */
        public Point point;

        /**
         * constructor
         * @param geometry geometric shape
         * @param point point on/in geometric shape
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
        @Override
        public String toString() {
            return "GeoPoint: " +
                    "geometry: " + geometry +
                    ", point: " + point ;
        }
    }

//    public List<Point> findIntersections(Ray ray) {
//        var geoList = findGeoIntersections(ray);
//        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
//    }

    /**
     * find all intersection {@link Point}s between ray and an object (geometry)
     *
     * @param ray ray towards the object
     * @return immutable list of intersection points
     */
    public final List<Point> findIntersections(Ray ray) {
        // get list of intersection points as geoPoint objects
        List<GeoPoint> geoList = findGeoIntersections(ray);
        // return list of intersection points, as point objects, extracted from the geoPoint objects list
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .toList();
    }

    /**
     * find all intersection {@link  GeoPoint}s between ray and a geometric object
     * calls abstract helper method, each implementing class , implements helper method
     * to return list of intersection {@link GeoPoint}s for that specific geometry
     * @param ray ray towards the object
     * @return immutable list of intersection {@link  GeoPoint}s
     */
//    public List<GeoPoint> findGeoIntersections(Ray ray){
//        return findGeoIntersectionsHelper(ray);
//    }


    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }



    /**
     * abstract helper method , gets list of intersection {@link GeoPoint}s between a ray and geometry
     * that are closer to rya origin than the upper distance boundary
     * implemented by interface implementing classes,calculating intersection
     * for the specific type of the class's geometry
     * @param ray ray towards the object
     * @param maxDistance upper boundary for distance of intersection points from ray
     * @return immutable list of intersection {@link  GeoPoint}s
     */
    protected abstract List<GeoPoint>
    findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
