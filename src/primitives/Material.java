package primitives;

public class Material {
    /**
     * pds class.
     */
    Double3 kD=new Double3(0),kS=new Double3(0);
    int nShininess=0;

    /**
     * setter for kD field.
     * @param kD Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material  setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * second setter for kD.
     * @param kD double type.
     * @return this instance for builder design pattern.
     */
    public Material  setkD(double kD) {
        this.kD=new Double3(kD);
        return this;
    }
    /**
     * setter for kS field.
     * @param kS Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material  setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * second setter for kS.
     * @param kS double type.
     * @return this instance for builder design pattern.
     */
    public Material  setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * setter for nShininess field.
     * @param nShininess Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material  setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
