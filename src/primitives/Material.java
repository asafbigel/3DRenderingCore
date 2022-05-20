package primitives;

public class Material {
    /**
     * pds class.
     */
    Double3 kD=new Double3(0),kS=new Double3(0);
    int nShininess=0;
    public Double3 kT=new Double3(0.0),kR=new Double3(0.0);


    //for glossy and blurry effects.
    public  int glossy=10;
    public int blurry=10;

    /**
     * setter for attenuation factor kt
     * @param kT Double3 type
     * @return this for builder design pattern.
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * second setter for kt attenuation factor.
     * @param kT double type.
     * @return
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter for kr attenuation factor.
     * @param kR Double3 type.
     * @return
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * second setter forkr attenuation factor.
     * @param kR double type.
     * @return
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
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

    /**
     * return material shininess as an int type.
     * @return shininess as int.
     */
    public int getnShininess() {
        return nShininess;
    }

    /**
     * setter for glossy effect factor.
     * @param glossy int type of how much a material is glossy.
     * @return this for builder design patterns.
     */
    public Material setGlossy(int glossy) {
        this.glossy = glossy;
        return this;
    }

    /**
     *
     * @param blurry
     * @return
     */
    public Material setBlurry(int blurry) {
        this.blurry = blurry;
        return this;
    }

}
