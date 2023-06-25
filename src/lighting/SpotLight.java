package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * get color of intensity of the spotlight at a given point
     * @param p {@link Point} to get intensity at
     * @return {@link Color} of intensity of light
     */
//    @Override
//    public Color getIntensity(Point p) {
//        // intensity for spotlight holds
////        Ip = (I0 / (kC + kL*distance + kQ*distance²)) * max(normalVector.dotProduct(vector from light to point), 0 )
//        //  (I0 / (kC + kL*distance + kQ*distance²)) = intensity for point light
//        //  calculated in parent class
//        Color color =super.getIntensity(p);
//        //vector from light origin point to point p on geometry
//        Vector v=super.getL(p);
//        // get max between dot product and 0 )
//        double factor=max(0,direction.dotProduct(v));
//        // scale intensity returned from parent class with factor
//        return color.scale(factor);
//    }
}
