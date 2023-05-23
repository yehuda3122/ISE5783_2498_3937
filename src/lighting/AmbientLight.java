package lighting;
import primitives.Color;
import primitives.Double3;
import primitives.Color;
public class AmbientLight {
    public Color  IA;
    public Double3 KA;
    public  Color intensity;
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    public AmbientLight(Color IA, Double3 KA) {
        this.IA = IA;
        this.KA = KA;
    }
    public AmbientLight(double KA) {
        this.KA = new Double3(KA);
    }


    public Color getIntensity() {
        return intensity;
    }
}
