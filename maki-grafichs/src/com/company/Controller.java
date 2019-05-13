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

    public void setOrangutan(ArrayList<Ornagutan_FX> orangutan) {
        this.orangutan = orangutan;
    }

    ArrayList<Ornagutan_FX> orangutan;

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
    int hol;
    public void mouse_click(int mit)
    {
        hol = Integer.parseInt(utasitasok.control.orangutans.get(0).getIamon().ID);
        if(nei[hol][mit]) {
            System.out.println("didit: " + String.valueOf(mit));
            egyes = !egyes;
            label.setText(egyes ? "Első játékos jön!" : "Második játékos jön!");
            utasitasok.move(new String[]{"", "player1", String.valueOf(mit)});
            update_pandas();
            update_orangutans();
        }
        else{
            //valami alert hogy nem szomszédosat klikkeltél
        }
    }

    private  void update_orangutans()
    {
        orangutan.get(0).place(start[Integer.parseInt(utasitasok.control.orangutans.get(0).getIamon().ID)]);
        //orangutan.get(1).place(start[Integer.parseInt(utasitasok.control.orangutans.get(1).getIamon().ID)]);
    }

    private void update_pandas()
    {
        for (Panda p:utasitasok.control.pandas) {
            StringBuilder sb = new StringBuilder(p.ID);
            sb.delete(0,5);
            pandas.get(Integer.parseInt(sb.toString())).place(start[Integer.parseInt(p.getIamon().ID)]);
        }
    }


}
