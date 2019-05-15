package com.company;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 * Singleton osztály az eseménykezelésért és a modell és a view közötti kapcsolat
 * megteremtéséért felelős.
 */
public class Controller {

    /**
     * Singleton példány
     */
    static Controller instance;
    /**
     * A player 1-hez tartozó orángután grafikus megjelenítéséért felelős függvények
     */
    Ornagutan_FX player1;
    /**
     * A player 2-hez tartozó orángután grafikus megjelenítéséért felelős függvények
     */
    Ornagutan_FX player2;
    /**
     * Pandák grafikus példányai megjelenítéséért felelős függvények
     */
    ArrayList<Panda_FX> pandas;
    /**
     * A játéktábla csempéinek szpmszédsági mátrixa
     */
    boolean[][] nei;
    /**
     * A cellák középpontjainak a koordinátái
     */
    koor[] start;
    /**
     * Orángutánok grafikus példányainak a tömbje
     */
    ArrayList<Ornagutan_FX> orangutan;
    /**
     * Utasítások osztály mellyel a control a modellel kommunikál
     */
    utasitasok utasitasok;
    /**
     * A felső label ami a szövegeket tárolja
     */
    Label label;
    /**
     * Az összekötött pandák és orángutánok közti vonalak
     */
    Group lines;
    /**
     * Az éppen aktuális orángután tartózkodási helye index szerint
     */
    int hol;
    /**
     * Melyik orángután következik épp
     */
    boolean egyes = false;
    /**
     * Körszámláló
     */
    int turnCount = 0;

    /**
     * Singletonhoz szükséges privát konstruktor
     */
    private Controller() {
        instance = this;
    }

    /**
     * A controller példány visszadásáért felelős függvény
     * @return visszadja a contoller példányt
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Szomszédsági mátrix beállításáért felelős setter függvény
     * @param nei boolean tömb szomszédsági mátrix
     */
    public void setNei(boolean[][] nei) {
        this.nei = nei;
    }

    /**
     * Start beállításáért felelős setter függvény
     * @param start koor tipusú kezdőpontok
     */
    public void setStart(koor[] start) {
        this.start = start;
    }

    /**
     * Setter függvény ami az egyes playerhez tartozó orángután beállításáért felelős
     * @param player1 erre állítjuk be a player 1 attribútumot
     */
    public void setPlayer1(Ornagutan_FX player1) {
        this.player1 = player1;
    }

    /**
     *  Setter függvény ami az 2 playerhez tartozó orángután beállításáért felelős
     * @param player2 erre állítjuk be a player 1 attribútumot
     */
    public void setPlayer2(Ornagutan_FX player2) {
        this.player2 = player2;
    }

    /**
     * Panda tömb beállításáért felelős függvény
     * @param pandas beállítani kívánt grafikus panda tömb
     */
    public void setPandas(ArrayList<Panda_FX> pandas) {
        this.pandas = pandas;
    }

    /**
     * orángután tömb beállításáért felelős függvény
     * beállítani kívánt grafikus orángután tömb
     * @param orangutan
     */
    public void setOrangutan(ArrayList<Ornagutan_FX> orangutan) {
        this.orangutan = orangutan;
    }

    /** Utasítások beállításra hivatott setter függvény
     * @param utasitasok beáálítani kívánt utasítások referencia
     */
    public void setUtasitasok(com.company.utasitasok utasitasok) {
        this.utasitasok = utasitasok;
    }

    /** Label beállításra hivattot setter függvény
     * @param label beállítani kívánt label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Az összefűzott állatok közti vonalak beállításra szolgáló függvény
     * @param lines beállítani kívánt vonalak tömb
     */
    public void setLines(Group lines) {
        this.lines = lines;
    }

    /**
     * Mouse clik eseményre reagálló függvény amelyben
     * a játékosok léptethetik az orángutánokat
     * lehetséges lépés esetén a következő player jön
     * nem lehetséges lépés esetén továbbra is a jelenlegi
     * player következik
     * A lépés végén meghívja a view frissítésére szolgáló metódusait
     * nem lehetséges lépés esetén a std outpura figyelmezettést ír ki
     * @param mit Kattinatott cella indexe
     */
    public void mouse_click(int mit) {
        System.out.println("diddit: " + String.valueOf(mit));
        if (utasitasok.control.End()) {
            return;
        }
        hol = Integer.parseInt(utasitasok.control.orangutans.get((egyes ? 1 : 0)).getIamon().ID);
        if (nei[hol][mit]) {
            System.out.println("didit: " + String.valueOf(mit));
            update_text();
            utasitasok.move(new String[]{"", (!egyes ? "player1" : "player2"), String.valueOf(mit)});
            if (!utasitasok.control.orangutans.get((!egyes ? 1 : 0)).getIamon().ID.equals(String.valueOf(mit))) {

                egyes = !egyes;
                if (!egyes) {
                    utasitasok.control.updateItems();
                    utasitasok.control.movePandas();
                }
            }
            if (utasitasok.control.orangutans.get(1).getIamon() != null && utasitasok.control.orangutans.get(1).getIamon().ID == "fake") {
                utasitasok.control.move(utasitasok.control.orangutans.get(1), Entry.getInstance());
            }
        } else {
            System.out.println("Lépés nem lehetséges, a mezők nem szomszédosak"); //valami alert hogy nem szomszédosat klikkeltél
        }
        update_breakables();
        update_pandas();
        update_text();
        update_orangutans();
        update_lines();
    }

    /**
     *  A labelben lévő szövegdobozok frissítésre szolgáló metódus
     *  kiírja a játékosk jelenlegi pontjait
     *  A játék végén kiírja hogy melyik fél győzőtt
     */
    private void update_text() {
        if (utasitasok.control.End()) {
            if (utasitasok.control.getP1().getPoints() == utasitasok.control.getP2().getPoints()) {
                label.setText("Játék vége! \t Döntetlen.");
            }
            if (utasitasok.control.getP1().getPoints() > utasitasok.control.getP2().getPoints()) {
                label.setText("Játék vége! \t Egyes játékos nyert.");
            }
            if (utasitasok.control.getP1().getPoints() < utasitasok.control.getP2().getPoints()) {
                label.setText("Játék vége! \t Kettes játékos nyert.");
            }
        } else {
            label.setText((!egyes ? "Első játékos jön!" : "Második játékos jön!") + "\tP1: " + (utasitasok.control.getP1() == null ? "0" : utasitasok.control.getP1().getPoints()) + " P2: " + (utasitasok.control.getP2() == null ? "0" : utasitasok.control.getP2().getPoints()));
        }
    }

    /**
     * Orángutánok grafikus frissétésére szolgáló metódus
     * Ha meghal az egyik akkor leveszi a viewről
     */
    private void update_orangutans() {
        if (utasitasok.control.orangutans.get(0).isAlive && utasitasok.control.orangutans.get(1).isAlive) {
            orangutan.get(0).place(start[Integer.parseInt(utasitasok.control.orangutans.get(0).getIamon().ID)]);
            if (utasitasok.control.orangutans.get(1).getIamon().ID != "fake") {
                orangutan.get(1).place(start[Integer.parseInt(utasitasok.control.orangutans.get(1).getIamon().ID)]);
            }
        } else {
            if (!utasitasok.control.orangutans.get(0).isAlive) {
                orangutan.get(0).kill();
            }
            if (!utasitasok.control.orangutans.get(1).isAlive) {
                orangutan.get(1).kill();
            }
        }
    }

    /**
     * Pandák grafikus frissétésére szolgáló metódus
     * Ha meghal az egyik akkor leveszi a viewről
     */
    private void update_pandas() {
        for (Panda p : utasitasok.control.pandas) {
            StringBuilder sb = new StringBuilder(p.ID);
            sb.delete(0, 5);
            if (!p.isAlive) {
                pandas.get(Integer.parseInt(sb.toString())).kill();
            } else {
                pandas.get(Integer.parseInt(sb.toString())).place(start[Integer.parseInt(p.getIamon().ID)]);
            }
        }
    }

    /**
     * Pandák grafikus frissétésére szolgáló metódus.
     * Ha egy összeköttetés megszűnik, akkor leveszi a viewról.
     */
    private void update_lines() {
        lines.getChildren().clear();
        for (int i = 0; i < 2; i++) {
            for (Unit u = utasitasok.control.orangutans.get(i); u.getHand2() != null; u = u.getHand2()) {
                Line line = new Line();
                line.setStartX(start[Integer.parseInt(u.getIamon().ID)].x);
                line.setStartY(start[Integer.parseInt(u.getIamon().ID)].y);
                line.setEndX(start[Integer.parseInt(u.getHand2().getIamon().ID)].x);
                line.setEndY(start[Integer.parseInt(u.getHand2().getIamon().ID)].y);
                line.setStrokeWidth(5);
                line.setStroke(Color.BLACK);
                lines.getChildren().add(line);
            }
        }
    }

    /**
     * Törhető mezők frissítésére szolgáló metódus.
     * Ha a mező eltröik, a körvonala narancssárga lesz.
     */
    public void update_breakables() {
        for (Breakable b : utasitasok.breakable_tiles) {
            if (b.getRemainLifetime() == 0) {
                b.setRemainLifetime(-1);
                for (int i = 0; i < nei[0].length; i++) {
                    nei[Integer.parseInt(b.ID)][i] = false;
                    nei[i][Integer.parseInt(b.ID)] = false;
                }
                start[Integer.parseInt(b.ID)].ki.setStroke(Color.ORANGE);
            }
        }
    }
}
