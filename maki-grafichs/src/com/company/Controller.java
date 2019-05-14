package com.company;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Controller {

    private Controller() {
        instance = this;
    }

    Ornagutan_FX player1;
    Ornagutan_FX player2;
    ArrayList<Panda_FX> pandas;
    boolean[][] nei;
    koor[] start;
    ArrayList<Ornagutan_FX> orangutan;
    utasitasok utasitasok;
    Label label;
    Group lines;
    static Controller instance;
    int hol;
    boolean egyes=false;
    int turnCount = 0;

    public void setNei(boolean[][] nei) {
        this.nei = nei;
    }

    public void setStart(koor[] start) {
        this.start = start;
    }

    public void setPlayer1(Ornagutan_FX player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Ornagutan_FX player2) {
        this.player2 = player2;
    }

    public void setPandas(ArrayList<Panda_FX> pandas) {
        this.pandas = pandas;
    }

    public void setOrangutan(ArrayList<Ornagutan_FX> orangutan) {
        this.orangutan = orangutan;
    }

    public void setUtasitasok(com.company.utasitasok utasitasok) {
        this.utasitasok = utasitasok;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setLines(Group lines) {
        this.lines = lines;
    }

    public static Controller getInstance() {
        if (instance==null){instance=new Controller();}
        return instance;
    }

    public void mouse_click(int mit)
    {
        System.out.println("diddit: "+String.valueOf(mit));
            hol = Integer.parseInt(utasitasok.control.orangutans.get(( egyes ? 1 : 0)).getIamon().ID);
            if (nei[hol][mit]) {
                turnCount++;
                egyes = !egyes;
                System.out.println("didit: " + String.valueOf(mit));
                update_text();
                utasitasok.move(new String[]{"", (egyes ? "player1" : "player2"), String.valueOf(mit)});
                if (turnCount%2==0)
                {
                    utasitasok.control.updateItems();
                    utasitasok.control.movePandas();
                }

            } else {
                //valami alert hogy nem szomszédosat klikkeltél
            }
        if(utasitasok.control.orangutans.get(1).getIamon().ID=="fake")
        {
            utasitasok.control.move(utasitasok.control.orangutans.get(1),Entry.getInstance());
        }
        update_breakables();
        update_pandas();
        update_orangutans();
        update_lines();
        update_text();
    }

    private void update_text ()
    {
        label.setText((!egyes ? "Első játékos jön!" : "Második játékos jön!")+"\tP1: "+(utasitasok.control.getP1()==null?"0":utasitasok.control.getP1().getPoints())+" P2: "+(utasitasok.control.getP2()==null?"0":utasitasok.control.getP2().getPoints()));
    }

    private  void update_orangutans()
    {
        orangutan.get(0).place(start[Integer.parseInt(utasitasok.control.orangutans.get(0).getIamon().ID)]);
        if (utasitasok.control.orangutans.get(1).getIamon().ID!="fake") {
            orangutan.get(1).place(start[Integer.parseInt(utasitasok.control.orangutans.get(1).getIamon().ID)]);
        }//TODO másol
    }

    private void update_pandas()
    {
        for (Panda p:utasitasok.control.pandas) {
            StringBuilder sb = new StringBuilder(p.ID);
            sb.delete(0,5);
            if(!p.isAlive){
                pandas.get(Integer.parseInt(sb.toString())).kill();
            }else {
                pandas.get(Integer.parseInt(sb.toString())).place(start[Integer.parseInt(p.getIamon().ID)]);
            }
        }
    }

    private void update_lines()
    {
        lines.getChildren().clear();
        for (int i = 0;i<2;i++) {
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

    public void update_breakables()
    {
        for (Breakable b:utasitasok.breakable_tiles) {
            if (b.getRemainLifetime()==0)
            {
                b.setRemainLifetime(-1);
                for (int i =0; i<nei[0].length;i++ )
                {
                    nei[Integer.parseInt(b.ID)][i]=false;
                    nei[i][Integer.parseInt(b.ID)]=false;
                }
                start[Integer.parseInt(b.ID)].ki.setStroke(Color.ORANGE);
            }
        }
    }

}
