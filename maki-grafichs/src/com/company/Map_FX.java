package com.company;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Random;

public class Map_FX
{
    public boolean[][] getNei() {
        return nei;
    }
    int fake_points;
    koor[] start;

    public koor[] getStart() {
        return start;
    }


    /*public double terulet(koor a1, koor a2,koor a3)
    {
        return a1.x*(a2.y-a3.y)-a1.y*(a2.x-a3.x)+((a2.x*a3.y)-(a2.y*a3.x));
    }*/
    int cells;
    int size_x,size_y;
    public Map_FX(int c,int x,int y) {
        fake_points = -1;
        nei = new boolean[c][c];
        size_x=x;
        size_y = y;
        cells =c;
        start = new koor[cells];//10 csúcs
        Random rand = new Random();
        for (int i = 0; i < cells; i++) {
            start[i] = new koor();
            start[i].x = rand.nextInt(size_x);
            start[i].y = rand.nextInt(size_y);
        }
    }
    public Map_FX(int c) {
        fake_points = -1;
        nei = new boolean[c][c];
        size_x=500;
        size_y = 500;
        cells =c;
        start = new koor[cells];//10 csúcs
        Random rand = new Random();
        for (int i = 0; i < cells; i++) {
            start[i] = new koor();
            start[i].x = rand.nextInt(size_x);
            start[i].y = rand.nextInt(size_y);
        }
    }

    public Map_FX(koor[] pontoka,int c,int x, int y) {
        fake_points = c-1;
        size_x=x;
        size_y = y;
        cells =pontoka.length;
        nei = new boolean[cells][cells];
        start = pontoka;
        Random rand = new Random();
    }
    boolean[][] nei ;
    public ArrayList<ArrayList<koor>> generate(int size_x, int size_y)
    {

        ImageMap I;

        int n = 0;
        Random rand = new Random();
        I = new ImageMap(size_x,size_y);
        koor[][] szele = new koor[cells][size_x*size_y/cells*5];
        int[] szeles = new int[cells];
        for (int a=0;a<cells;a++)
        {
            for (int b=0;b<(size_x*size_y/cells*5);b++){
                szele[a][b]=new koor();
            }
            szeles[a]=0;
        }
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
                szele[n][szeles[n]].x=x;
                szele[n][szeles[n]].y=y;
                szeles[n]++;
            }
        }

        for(int a = 0;a<cells;a++)
        {
            for (int b=0;b<cells;b++)
            {
                nei[a][b]=false;
            }
        }
        for (int a=0;a<cells;a++)
        {
            for (int b=0;b<(size_x*size_y/cells*5);b++) {
                //System.out.println("X: "+szele[a][b].x+";Y: "+szele[a][b].y);
                if (szele[a][b].x != 0 && szele[a][b].y != 0 && szele[a][b].x != size_x-1 && szele[a][b].y != size_y-1 && szele[a][b].x!=-1&&szele[a][b].y!=-1) {

                    int ax = I.getRGB(szele[a][b].x, szele[a][b].y);
                    int fent = I.getRGB(szele[a][b].x + 1, szele[a][b].y);
                    int lent = I.getRGB(szele[a][b].x - 1, szele[a][b].y);
                    int jobbra = I.getRGB(szele[a][b].x, szele[a][b].y+1);
                    int balra = I.getRGB(szele[a][b].x, szele[a][b].y-1);
                    if (ax==fent&&ax==lent&&ax==jobbra&&ax==balra) {
                        szele[a][b].x = (-1);
                        szele[a][b].y = (-1);
                    }
                    else{
                        nei[ax][fent] = ax!=fent?true:nei[ax][fent];
                        nei[ax][lent] = ax!=lent?true:nei[ax][lent];
                        nei[ax][jobbra] = ax!=jobbra?true:nei[ax][jobbra];
                        nei[ax][balra] = ax!=balra?true:nei[ax][balra];
                    }
                }
            }
        }

        ArrayList<ArrayList<koor>> ret = new ArrayList<>();
        ArrayList<koor> ki;
        for (int a=0;a<cells;a++)
        {
            ki = new ArrayList<>();
            for (int b=0;b<(size_x*size_y/cells*5);b++) {
                if(szele[a][b].x!=-1&&szele[a][b].y!=-1){
                    ki.add(szele[a][b]);
                }
            }
            ret.add(ki);
        }

        return ret;
    }


    public Pane draw ()
    {
        Pane pane=new Pane();
        Polygon perm;
        ArrayList<Double> kord = new ArrayList<>();
        ArrayList<ArrayList<koor>> halmaz =generate(size_x,size_y);
        int start_id;
        start_id = 0;


        for (ArrayList<koor> list:halmaz) {
            if (start_id<42) {
                Color csoportszin = Color.color(Math.random(), Math.random(), Math.random());
                int avg_x = 0, avg_y = 0, count = 0;
                for (koor kordinata : list) {
                    avg_x += kordinata.x;
                    avg_y += kordinata.y;
                    count++;
                }


                koor akt = list.get(0);
                while (list.size() != 0) {
                    akt = mintav(akt, list);
                    kord.add(akt.x * 1.0);
                    kord.add(akt.y * 1.0);
                    list.remove(akt);
                }
                start[start_id].x = avg_x / count;
                start[start_id].y = avg_y / count;
                perm = new Polygon();
                perm.setId(String.valueOf(start_id));
                start[start_id].ki = perm;
                perm.getPoints().addAll(kord);
                perm.setFill(csoportszin);
                int finalStart_id = start_id;
                Controller mk = Controller.getInstance();
                perm.setOnMouseClicked(mouseEvent -> mk.mouse_click(finalStart_id));
                kord.clear();
                pane.getChildren().add(perm);
            }
            start_id++;
        }
        return  pane;
    }

    public static double distance(int x1, int x2, int y1, int y2) {
        double d;
        d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return d;
    }

    Circle panda;


    public koor mintav(koor b, ArrayList<koor> list)
    {
        Map_FX mf = new Map_FX(cells);
        koor ret=list.get(0);
        for (koor akt:list) {
            if (mf.distance(b.x,ret.x,b.y,ret.y)>mf.distance(b.x,akt.x,b.y,akt.y))
            {
                ret = akt;
            }
        }
        return ret;
    }
}
