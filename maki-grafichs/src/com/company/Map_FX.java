package com.company;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Random;

public class Map_FX {

    /**
     * A függvény segítségével átadhatjuk a szomszédságot ábrázoló tömböt.
     *
     * @return A szomszédságot ábrázoló kétdimenziós boolean tömb.
     */
    public boolean[][] getNei() {
        return nei;
    }

    /**
     * Ha előre definiált ponthalmazt használunk a mező értéke reprezentálja a hamis koordináták határát.
     * Hamis koordináta: a megfelelő topológia kialakitásához szükséges koordináták, ezekből nem lesz valós használható mező.
     * Amenyibben nincs használatban értéke: -1
     * Pl.: Ha van 82 koordinátánk és ebből az első 42 koordinátából lesz mező, a fake_points értéke 42 lesz.
     */
    int fake_points;

    /**
     * A kirajzoláshoz szükséges koordinátákat tartalmazó tömb.
     * Tartalma a helyiértékekkel megegyező ID-u Tile középpontja.
     */
    koor[] start;

    /**
     * A függvény segítségével átadhatjuk a mezők középpontját tartalmazó koor tömböt.
     *
     * @return A mezők középpontját tartalmazó koor tömb.
     */
    public koor[] getStart() {
        return start;
    }


    /*public double terulet(koor a1, koor a2,koor a3)
    {
        return a1.x*(a2.y-a3.y)-a1.y*(a2.x-a3.x)+((a2.x*a3.y)-(a2.y*a3.x));
    }*/

    /**
     * A mezők valós számát tároló integer.
     */
    int cells;

    /**
     * A képernyőre kirajzolt gráf kiterjedése az x tengelyen.
     */
    int size_x;

    /**
     * A képernyőre kirajzolt gráf kiterjedése az y tengelyen.
     */
    int size_y;

    /**
     * Ezzel a konstruktorral előre nem definiált (random generált) ponthalmazzal hoz létre gráfot.
     *
     * A kód működése:
     * (1). Inicializálás
     *      A létrehozott változók inicializálása, feltöltése a paraméterekkel kapott értékekkel.
     *
     * (2). A Random gráf pontjainak generálása
     *      Első lépésként létrehozunk egy új randomot.
     *      Ezután végigiterálunk a start tömbön és...
     *          ...inicializáljuk.
     *          ...feltöltjük random koordinátákkal
     *
     * @param c A mezők száma
     * @param x A gráf kiterjedése az x tengelyen
     * @param y A gráf kiterjedése az y tengelyen
     */
    public Map_FX(int c, int x, int y) {
        //(1).
        fake_points = -1;
        nei = new boolean[c][c];
        size_x = x;
        size_y = y;
        cells = c;
        start = new koor[cells];
        //(2).
        Random rand = new Random();
        for (int i = 0; i < cells; i++) {
            start[i] = new koor();
            start[i].x = rand.nextInt(size_x);
            start[i].y = rand.nextInt(size_y);
        }
    }

    /**
     * Ezzel a konstruktorral előre definiált ponthalmazzal hoz létre gráfot.
     *
     * A kód működése:
     *  (1). Inicializálás
     *      A létrehozott változók inicializálása, feltöltése a paraméterekkel kapott értékekkel.
     *
     * @param pontoka Előre definiált ponthalmaz
     * @param c A mezők száma
     * @param x A gráf kiterjedése az x tengelyen
     * @param y A gráf kiterjedése az y tengelyen
     */
    public Map_FX(koor[] pontoka, int c, int x, int y) {
        //(1).
        fake_points = c - 1;
        size_x = x;
        size_y = y;
        cells = pontoka.length;
        nei = new boolean[cells][cells];
        start = pontoka;
    }

    /**
     * A szomszédságot ábrázoló kétdimenziós boolean tömb.
     */
    boolean[][] nei;

    /**
     * A generate függvény segítségével a bemenetnek számító start tömbből egy két dimenziós ArrayList-et hoz létre,
     * amely belső listájában a gráf éleinek pontjainak koordinátáit tartalmazza
     * a külső lista alapján, mely színekre (kezdő pontokra) bontva tartalmazza azokat.
     *
     * A kód működése:
     *  (1).szele és ImageMap inicializálás
     *      A szele tömb tartalmazza a gráf celláinak pontjait, színek szerint.
     *      Az ImageMap ez alapján egy szín mátrixot tartalmaz.
     *
     *  (2).szele feltöltése és ImageMap színeinek számolása
     *      Kiszámoljuk az ImageMap pontjainak színeit.
     *      Majd ez alapján feltöltjük a szel tömböt.
     *
     *  (3).Fölösleges (amik nem élek) pontok eltávolítása
     *      Inicializáljuk a nei tömböt.
     *      Azokat a pontokat amik nem a cellák széleit képezik, -1;-1 re állítjuk,
     *      ezzel jelezzük hogy nincs rá szükség
     *      Eközben feltöltjük a nei tömböt is ami a cellék szomszédságát tárolja.
     *
     *  (4).szele átalakítása
     *      A szele tömböt átalakítjuk a két dimenziós listává, amivel visszatérünk.
     *
     * @param size_x A generált gráf kiterjedése az x tengelyen.
     * @param size_y A generált gráf kiterjedése az y tengelyen.
     * @return Kétdimenziós ArrayList ami a gráf éleinek pontjait tartalmazza színekre bontva.
     */
    public ArrayList<ArrayList<koor>> generate(int size_x, int size_y) {
        //(1).
        ImageMap I;
        int n = 0;
        Random rand = new Random();
        I = new ImageMap(size_x, size_y);
        koor[][] szele = new koor[cells][size_x * size_y / cells * 5];
        int[] szeles = new int[cells];
        for (int a = 0; a < cells; a++) {
            for (int b = 0; b < (size_x * size_y / cells * 5); b++) {
                szele[a][b] = new koor();
            }
            szeles[a] = 0;
        }

        //(2).
        for (int x = 0; x < size_x; x++) {
            for (int y = 0; y < size_y; y++) {
                n = 0;
                for (byte i = 0; i < cells; i++) {

                    if (distance(start[i].x, x, start[i].y, y) < distance(start[n].x, x, start[n].y, y)) {
                        n = i;

                    }
                }
                I.setRGB(x, y, n);
                //System.out.println(n);
                szele[n][szeles[n]].x = x;
                szele[n][szeles[n]].y = y;
                szeles[n]++;
            }
        }
        //(3).
        for (int a = 0; a < cells; a++) {
            for (int b = 0; b < cells; b++) {
                nei[a][b] = false;
            }
        }
        for (int a = 0; a < cells; a++) {
            for (int b = 0; b < (size_x * size_y / cells * 5); b++) {
                //System.out.println("X: "+szele[a][b].x+";Y: "+szele[a][b].y);
                if (szele[a][b].x != 0 && szele[a][b].y != 0 && szele[a][b].x != size_x - 1 && szele[a][b].y != size_y - 1 && szele[a][b].x != -1 && szele[a][b].y != -1) {

                    int ax = I.getRGB(szele[a][b].x, szele[a][b].y);
                    int fent = I.getRGB(szele[a][b].x + 1, szele[a][b].y);
                    int lent = I.getRGB(szele[a][b].x - 1, szele[a][b].y);
                    int jobbra = I.getRGB(szele[a][b].x, szele[a][b].y + 1);
                    int balra = I.getRGB(szele[a][b].x, szele[a][b].y - 1);
                    if (ax == fent && ax == lent && ax == jobbra && ax == balra) {
                        szele[a][b].x = (-1);
                        szele[a][b].y = (-1);
                    } else {
                        nei[ax][fent] = ax != fent ? true : nei[ax][fent];
                        nei[ax][lent] = ax != lent ? true : nei[ax][lent];
                        nei[ax][jobbra] = ax != jobbra ? true : nei[ax][jobbra];
                        nei[ax][balra] = ax != balra ? true : nei[ax][balra];
                    }
                }
            }
        }

        //(4).
        ArrayList<ArrayList<koor>> ret = new ArrayList<>();
        ArrayList<koor> ki;
        for (int a = 0; a < cells; a++) {
            ki = new ArrayList<>();
            for (int b = 0; b < (size_x * size_y / cells * 5); b++) {
                if (szele[a][b].x != -1 && szele[a][b].y != -1) {
                    ki.add(szele[a][b]);
                }
            }
            ret.add(ki);
        }

        return ret;
    }


    /**
     * A generate által generált él-pont halmaz listából készíti el a szükséges sokszögeket.
     *
     * A kód működése:
     *  (1).Inicializálsa
     *      A szükséges változók inicializálsa.
     *
     *  (2).Ismétlés, feltétel
     *      A kétdimenziós listából színek szerint mindegyikre végrehajtja az alábbiakat ha
     *      a számláló nem haladta meg a fake_points értékét, azaz csak az igazi pontokra hozunk létre mezőt.
     *
     *  (3).Color és Start átszámolása, átlagolása.
     *      Minden csoportra random kreálunk egy színt amivel a sokszöget kitöltjük.
     *      A start tömb értékeit átállítjuk a színek szerinti élek pontjainak átlagára,
     *      ezzel a megjelnített objektumok a sokszög közepére helyezhetőek.
     *
     *  (4).Optimális összekötés
     *      Az élek pontjait egy listában sorbarendezzük úgy, hogy mindig a legközelebbit kötjük a következőhöz,
     *      ezzel elkerüljük hogy furcsa alakokat alakítsanak ki a kész sokszögek.
     *
     *  (5).Sokszög létrehozása
     *      Létrehozzuk a sorrendbe állított pontokból a sokszöget, beállítjuk az ID-át, a start tömben a megfelelő
     *      változóban beállítjuk a referenciát a sokszögre, majd beállítjuk a színét.
     *
     *  (6).Egér kattintás kezelése
     *      A sokszöghöz hozzáadjuk az event listenert és beállítjuk a controller-be található függvényre.
     *
     *  (7).Befejezés
     *      A listánkat ürítjük, a meglévő sokszöget hozzáadjuk a vásznunkhoz, megnöveljük a szükséges változókat,
     *      majd ha kiléptünk az összes ciklusból, visszatérünk.
     *
     * @return A sokszögeket tartalmazo vásznat adja vissza.
     */
    public Pane draw() {
        //(1).
        Pane pane = new Pane();
        Polygon perm;
        ArrayList<Double> kord = new ArrayList<>();
        ArrayList<ArrayList<koor>> halmaz = generate(size_x, size_y);
        int start_id;
        start_id = 0;

        //(2.)
        for (ArrayList<koor> list : halmaz) {
            if (start_id < fake_points) {
                //(3).
                Color csoportszin = Color.color(Math.random(), Math.random(), Math.random());
                int avg_x = 0, avg_y = 0, count = 0;
                for (koor kordinata : list) {
                    avg_x += kordinata.x;
                    avg_y += kordinata.y;
                    count++;
                }
                start[start_id].x = avg_x / count;
                start[start_id].y = avg_y / count;
                //(4).
                koor akt = list.get(0);
                while (list.size() != 0) {
                    akt = mintav(akt, list);
                    kord.add(akt.x * 1.0);
                    kord.add(akt.y * 1.0);
                    list.remove(akt);
                }
                //(5.)
                perm = new Polygon();
                perm.setId(String.valueOf(start_id));
                start[start_id].ki = perm;
                perm.getPoints().addAll(kord);
                perm.setFill(csoportszin);
                //(6).
                int finalStart_id = start_id;
                Controller mk = Controller.getInstance();
                perm.setOnMouseClicked(mouseEvent -> mk.mouse_click(finalStart_id));
                //(7.)
                kord.clear();
                pane.getChildren().add(perm);
            }
            start_id++;
        }
        return pane;
    }

    /**
     * A függvény egyszerű matematikát alkalmazva kiszámolja a két pont távolságát.
     *
     * @param x1 Az egyik pont x koordinátája.
     * @param x2 Az másik pont x koordinátája.
     * @param y1 Az egyik pont y koordinátája.
     * @param y2 Az másik pont y koordinátája.
     * @return  A két pont távolsága.
     */
    public static double distance(int x1, int x2, int y1, int y2) {
        double d;
        d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return d;
    }

    /**
     * A függvény segítségével egy adott ponthoz egy pont listából megkeressük a legközelebbi pontot.
     *
     * @param b A pont amihez hasonlítjuk a többit.
     * @param list  Pontok listája amiben a minimális távolságot keressük.
     * @return  A 'b' ponthoz legközelebbi pont a listában.
     */
    public koor mintav(koor b, ArrayList<koor> list) {
        Map_FX mf = new Map_FX(cells,1500,1500);
        koor ret = list.get(0);
        for (koor akt : list) {
            if (mf.distance(b.x, ret.x, b.y, ret.y) > mf.distance(b.x, akt.x, b.y, akt.y)) {
                ret = akt;
            }
        }
        return ret;
    }
}
