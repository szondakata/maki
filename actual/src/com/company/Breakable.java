package com.company;

/**
 * A torheto csempet megvalosito osztaly
 */
public class Breakable extends Field {
    /**
     * A csempe elettartama
     */
    private int remainLifetime = 5;

    /** A csempe elettartamat megado fuggveny
     * @return a csempe elettartama
     */
    public int getRemainLifetime() {
        return remainLifetime;
    }

    /** A fuggveny a parameterben kapott ertekre allitja a csempe elettartamat
     * @param remainLifetime a maradek elet
     */
    public void setRemainLifetime(int remainLifetime) {
        this.remainLifetime = remainLifetime;
    }


    /** A fuggveny a parameterben kapott egyseg nemnullasagara elettartamcsokkenest visz veghez.
     * Ha a csempe elete elfogyott, megoli az egyseget es kitorli magat a szomszedai kozul
     * @param u az egyseg
     */
    @Override
    public void stepped(Unit u) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".stepped(+" + u.toString() + ")");
        if (u != null) {
            remainLifetime--;
            if (remainLifetime < 1)
            {
                if (getContain() != null) {
                    u.die();
                }
                for (Field f : getNei()) {
                    f.getNei().remove(this);
                }
                setNei(null);
            }
        }
        logger.depthM();
    }

    /**
     * TODO Ez egy butasag, de nem en irtam es nem is tudom mit csinal, ugyhogy itt hagyom
     */
    @Override
    public void Update() {
        if (remainLifetime <= 0)
            stepped(new Panda());
    }
}
