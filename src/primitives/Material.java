package primitives;

public class Material {
    /**
     * attenuation coefficient for diffusion of light on the material
     */
    public Double3 kD=Double3.ZERO;
    /**
     * attenuation coefficient for specular level of the material
     */
    public Double3 kS=Double3.ZERO;
    /**
     * coefficient for transparency level of material
     */
    public Double3 kT=Double3.ZERO;

    /**
     *  coefficient for reflectiveness level of material
     */
    public Double3 kR=Double3.ZERO;
    public int nShininess;

    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
