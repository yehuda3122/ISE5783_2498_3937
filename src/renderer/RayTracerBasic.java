package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    /**
     * constructor
     *
     * @param scene {@link Scene} for {@link Color} calculations to be executed on
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * find the closest intersection point between ray and geometries in scene
     *
     * @param ray ray constructed from camera to scene
     * @return closest intersection {@link Point}
     */
    private Point findClosestIntersection(Ray ray) {
        // check if ray constructed through the pixel intersects any of geometries
        List<Point> intersections = scene.getGeometries().findGeoIntersectionsHelper(ray);

        // return closest point if list is not empty
        return intersections == null ? null : ray.findClosestPoint(intersections);

    }

    /**
     * calculate a {@link  Color} of a pixel (i,j)
     *
     * @param ray ray constructed from camera through the pixel in view plane
     * @return {@link  Color} of the pixel
     */
    @Override
    public Color traceRay(Ray ray) {

        // find the closest intersection point
        Point closestPoint = findClosestIntersection(ray);
        // if no intersection point was found , return basic background color of scene
        if (closestPoint == null)
            return scene.getBackground();

        // intersection was found, calculate color of the of pixel.
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of a pixel
     *
     * @param gp  the {@link Point} viewed through the pixel to calculate color of
     * @param ray ray of camera through pixel in view plane where the point is located
     * @return color of the pixel
     */
    private Color calcColor(Point gp, Ray ray) {
//        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
//                .add(scene.getAmbientLight().getIntensity());
        return scene.getBackground();

    }
}
