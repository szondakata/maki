package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Random;

public class Map_FX
{
    public koor[] getStart() {
        return start;
    }

    koor[] start;


    /*public double terulet(koor a1, koor a2,koor a3)
    {
        return a1.x*(a2.y-a3.y)-a1.y*(a2.x-a3.x)+((a2.x*a3.y)-(a2.y*a3.x));
    }*/

    public Map_FX() {
        start = new koor[10];//10 csúcs
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            start[i] = new koor();
            start[i].x = rand.nextInt(500);
            start[i].y = rand.nextInt(500);
        }
    }

    public Map_FX(koor[] pontoka) {
        start = pontoka;
    }

    public ArrayList<ArrayList<koor>> generate(int size_x, int size_y, int cells)
    {
        ImageMap I;

        int n = 0;
        Random rand = new Random();
        I = new ImageMap(size_x,size_y);
        koor[][] szele = new koor[cells][size_x*size_y/cells*5];
        int[] szeles = new int[cells];
        for (int i = 0; i < cells; i++) {
            start[i].x = rand.nextInt(size_x);
            start[i].y = rand.nextInt(size_y);
        }
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
                System.out.println(n);
                szele[n][szeles[n]].x=x;
                szele[n][szeles[n]].y=y;
                szeles[n]++;
            }
        }

        for (int a=0;a<cells;a++)
        {
            for (int b=0;b<(size_x*size_y/cells*5);b++) {
                System.out.println("X: "+szele[a][b].x+";Y: "+szele[a][b].y);
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


    public Pane draw (int size_x,int size_y,int cells)
    {
        Pane pane=new Pane();
        Polygon perm;
        ArrayList<Double> kord = new ArrayList<>();
        ArrayList<ArrayList<koor>> halmaz =generate(size_x,size_y,cells);
        for (ArrayList<koor> list:halmaz) {
            int i=1;
            Color csoportszin = Color.color(Math.random(), Math.random(), Math.random());
            /*for (int x = 0 ; x< list.size();x++)
            {
                for (int y = x+1 ; y< list.size();y++)
                {
                    for (int z = y+1 ; z< list.size();z++)
                    {
                        if (Math.abs(terulet(list.get(x),list.get(y),list.get(z)))<1)
                        {
                            list.remove(y);
                            System.out.println("Csúcs eltávolítva: "+i);
                            i++;
                        }
                    }
                }
            }
            Circle pont;
            Group pontok = new Group();
            for (koor ponty:list) {
                pont = new Circle(ponty.x,ponty.y,3);
                pont.setFill(csoportszin);
                pont.setStroke(Color.WHITE);
                pont.setStrokeWidth(0.5);
                pontok.getChildren().add(pont);
            }
                */

            koor akt = list.get(0);
            while (list.size()!=0)
            {
                akt = mintav(akt,list);
                kord.add(akt.x*1.0);
                kord.add(akt.y*1.0);
                list.remove(akt);
            }

            perm = new Polygon();
            perm.getPoints().addAll(kord);
            perm.setFill(csoportszin);
            perm.setOnMouseClicked(mouseEvent -> System.out.println("Nyomtad: "+ mouseEvent.getTarget().toString()));
            kord.clear();
            pane.getChildren().add(perm);
            //pane.getChildren().add(pontok);
        }
        return  pane;
    }

    public static double distance(int x1, int x2, int y1, int y2) {
        double d;
        d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); // Euclidian
        //  d = Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan
        //  d = Math.pow(Math.pow(Math.abs(x1 - x2), p) + Math.pow(Math.abs(y1 - y2), p), (1 / p)); // Minkovski
        return d;
    }



    public koor mintav(koor b, ArrayList<koor> list)
    {
        Map_FX mf = new Map_FX();
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
