package lighting;
import primitives.Color;
import primitives.Double3;
import primitives.Color;

/**
 * Ambient Light as color for all object in the scene
 */
public class AmbientLight extends Light{

//    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    /**
     * Primary Constructor
     * @param IA light intensity
     * @param KA Attenuation coefficient of color
     */
    public AmbientLight(Color IA, Double3 KA) {
        super(IA.scale(KA));
    }
//    public AmbientLight(double KA) {
//        this.KA = new Double3(KA);
//    }
    /**
     * default constructor
     */
    public AmbientLight() {
        super( Color.BLACK);
    }



}
