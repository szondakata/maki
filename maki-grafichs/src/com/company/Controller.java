package com.company;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {

    public void setNei(boolean[][] nei) {
        this.nei = nei;
    }

    public void setStart(koor[] start) {
        this.start = start;
    }

    Ornagutan_FX player1;
    Ornagutan_FX player2;
    ArrayList<Panda_FX> pandas;

    public void setPlayer1(Ornagutan_FX player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Ornagutan_FX player2) {
        this.player2 = player2;
    }

    public void setPandas(ArrayList<Panda_FX> pandas) {
        this.pandas = pandas;
    }

    boolean[][] nei;
    koor[] start;

    public void setUtasitasok(com.company.utasitasok utasitasok) {
        this.utasitasok = utasitasok;
    }

    utasitasok utasitasok;
    public void setLabel(Label label) {
        this.label = label;
    }

    Label label;

    public static Controller getInstance() {
        if (instance==null){instance=new Controller();}
        return instance;
    }

    static Controller instance;

    private Controller() {
        instance = this;
    }
    boolean egyes=true;
    public void mouse_click(int mit)
    {
        System.out.println("didit: "+String.valueOf(mit));
        egyes= !egyes;
        label.setText(egyes?"Első játékos jön!":"Második játékos jön!");
        utasitasok.control.pandas.get(0);
        utasitasok.move(new String[]{"","panda1",String.valueOf(mit)});
        update_pandas();
    }

    private void update_pandas()
    {
        int i=0;
        for (Panda p:utasitasok.control.pandas) {
            StringBuilder sb = new StringBuilder(p.ID);
            sb.delete(0,5);
            System.out.println(sb.toString()+"   "+p.getIamon().ID);
            pandas.get(Integer.parseInt(sb.toString())).place(start[Integer.parseInt(p.getIamon().ID)]);
        }
    }


}
