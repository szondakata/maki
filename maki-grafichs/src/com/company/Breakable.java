package com.company;

/**
 * A törhető csempe osztály egy speciális csempét jelöl.
 */
public class Breakable extends Field {
    /**
     * A csempe hátra lévő életei
     */
    private int remainLifetime = 5;//Hátralévő élet

    /** A csempe hátralévő életét visszaadó funkció
     * @return a csempe hátralévő élete
     */
    public int getRemainLifetime() {
        return remainLifetime;
    }

    /** A csempe hátralévő életét a paraméterben kapott mennyiségre beállító funkció
     * @param remainLifetime a kívánt mennyiség
     */
    public void setRemainLifetime(int remainLifetime) {
        this.remainLifetime = remainLifetime;
    }

    /** A funkció hatására csökken a csempe élettartama. Ha a csempe élete elfogy, a paraméterben kapott
     * egység meghal és a csempe elérhetetlenné teszi magát a szomszédairól.
     * @param u az egység
     */
    @Override
    public void stepped(Unit u) {//Ha rálépnek meghívódik Unit u:aki rá lépett
        logger.depthP();
        logger.writeMessage(this.toString() + ".stepped(+" + (u == null ? "null" : u.toString()) + "  ");
        if (u != null) {
            remainLifetime--;
            if (remainLifetime < 1)//Ha eltörik, aki rajta áll meghal
            {
                u.die();
                for (Field f : getNei()) {//Törli a hozzátartozó szomszédi kapcsolatokat a vele szomszédos mezőkben
                    f.getNei().remove(this);
                }
            }
        }
        logger.depthM();
    }

    /** A függvény megvizsgálja, van-e még élete a csempének. Ha van, nem történik semmi.
     * Ha nincs, akkor létrehoz egy temporális egységet, amely a csempére lép.
     */
    @Override
    public void Update() {
        if (remainLifetime > 0) return;
            Panda temp = new Panda();
            temp.setIamon(this);
            this.setContain(temp);
    }
}
