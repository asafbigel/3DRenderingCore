package primitives;

public class Material {
    /**
     * pds class.
     */
    Double3 kD=new Double3(0),kS=new Double3(0);
    int nShininess=0;
    public Double3 kT=new Double3(0.0),kR=new Double3(0.0);

    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter for kD field.
     * @param kD Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * second setter for kD.
     * @param kD double type.
     * @return this instance for builder design pattern.
     */
    public Material setKd(double kD) {
        this.kD=new Double3(kD);
        return this;
    }

    /**
     *
     * @return
     */
    public Double3 getkD() {
        return kD;
    }

    /**
     *
     * @return
     */
    public Double3 getkS() {
        return kS;
    }

    /**
     * setter for kS field.
     * @param kS Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * second setter for kS.
     * @param kS double type.
     * @return this instance for builder design pattern.
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * setter for nShininess field.
     * @param nShininess Double3 type.
     * @return this instance for builder design pattern.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public int getnShininess() {
        return nShininess;
    }
}
