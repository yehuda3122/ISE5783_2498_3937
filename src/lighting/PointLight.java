package lighting;

import geometries.Sphere;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class PointLight extends Light implements LightSource{

    private Point position;
    private double KC = 1, KL = 0, KQ = 0;

    /**
     * size of radius around the light to create soft shadows
     */
    private Double radius;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    public PointLight setKC(double KC) {
        this.KC = KC;
        return this;
    }

    public PointLight setKL(double KL) {
        this.KL = KL;
        return this;
    }

    public PointLight setKQ(double KQ) {
        this.KQ = KQ;
        return this;
    }

    /**
     * get color of intensity of the point light at a given point
     *
     * @param p {@link Point} to get intensity at
     * @return {@link Color} of intensity of light
     */
    @Override
    public Color getIntensity(Point p) {
        // intensity for point light holds
        //Ip = I0 / (kC + kL*distance + kQ*distance²)
        // calculate distance from light to point
        double distance = p.distance(position);
        // calculate denominator
        double factor = (KC + KL * distance) + KQ * (distance * distance);
        // scale color by 1/denominator
        Color color = getIntensity().reduce(factor);
        return color;
    }

    /**
     * return the direction from the point light to a given point
     *
     * @param p {@link Point} to get intensity at
     * @return {@link Vector} from point light to the point
     */
    @Override
    public Vector getL(Point p) {
        try {
            Vector v = p.subtract(position).normalize();
            return v;
        }
        // point light is at given point
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * get a beam of rays from a point on a geometry towards the light,
     * all the rays are within the radius boundary
     *
     * @param p point on the geometry
     * @return {@link List}of rys from the geometry to the soft shadow radius
     * @author Yona Shmerla
     */
    public List<Vector> getListL(Point p) {

        double distance = p.subtract(position).length();
        Sphere sphere = new Sphere(position, distance / 10);

        List<Vector> vectors = new LinkedList();
        for (double i = -radius; i < radius; i += radius / 10) {
            for (double j = -radius; j < radius; j += radius / 10) {
                if (i != 0 && j != 0) {
                    Point point = position.add(new Vector(i, 0.1d, j));
                    if (point.equals(position)) {
                        vectors.add(p.subtract(point).normalize());
                    } else {
                        try {
                            if (point.subtract(position).dotProduct(point.subtract(position)) <= radius * radius) {
                                vectors.add(p.subtract(point).normalize());
                            }
                        } catch (Exception e) {
                            vectors.add(p.subtract(point).normalize());
                        }

                    }
                }

            }
        }
        vectors.add(getL(p));
        return vectors;
    }

    /**
     * get the distance between a point light to a given point
     *
     * @param p {@link Point} to calculate distance to
     * @return distance
     */
    @Override
    public double getDistance(Point p) {
        return position.distance(p);
    }
}
