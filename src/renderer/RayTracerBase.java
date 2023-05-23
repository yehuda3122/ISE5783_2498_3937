package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    /**
     * {@link Scene} for {@link Color} calculations to be executed on
     */
    protected final Scene scene;

    /**
     * constructor
     * @param scene {@link Scene}
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * abstract method - calculate color of a pixel in  image
     * @param ray ray constructed from camera through the pixel in view plane
     * @return {@link Color} of pixel
     */
    public abstract Color traceRay(Ray ray);
}
