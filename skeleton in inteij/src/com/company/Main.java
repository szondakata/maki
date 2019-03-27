package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Field f1 = new Field();
    static ArrayList<Field> mezok = new ArrayList<Field>();
    static Control control;
    static Player p2;
    static Player p1;


    public static void start() {


        switch (scanner.nextInt()) {
            case 1:
                Orangutan o1 = new Orangutan();
                control.addOrangutan(o1);
                p1.setMyOran(o1);
                f1.setFieldOn(o1);

                lancol(f1);
                break;
            case 2:
                Panda p = new Panda();
                control.addPanda(p);
                f1.setFieldOn(p);
                break;
            case 3:
                Ijedos i = new Ijedos();
                control.addPanda(i);
                f1.setFieldOn(i);
                break;
            case 4:
                Almos a = new Almos();
                control.addPanda(a);
                f1.setFieldOn(a);
                break;
            case 5:
                Ugralos u = new Ugralos();
                control.addPanda(u);
                f1.setFieldOn(u);
                break;
        }
    }

    static Field milyenmezo() {
        Field f1 = null;

        switch (scanner.nextInt()) {
            case 1:
                f1 = new Field();
                break;
            case 2:
                f1 = new Breakable();
                break;
            case 3:
                Entry e = new Entry();
                control.setE(e);
                return e;
            case 4:
                f1 = new Exit();
                break;
            case 5:
                f1 = new Csoki();
                break;
            case 6:
                f1 = new Fotel() ;
                break;
            case 7:
                f1 = new Jatek() ;
                break;
            case 8:
                f1 = new Szekreny() ;
                break;
        }


        return f1;
    }


    public static void main(String[] args) {
        control = new Control();
        p1 = new Player();
        p2 = new Player();
        control.setP1(p1);
        control.setP2(p2);


        start();



    }

    private static void lancol(Field f) {

        if (scanner.nextInt() == 2)
            DoYouWantMove();


        switch (scanner.nextInt()) {
            case 1:
                Field ujf = milyenmezo();

                f.getNei().add(ujf);


                f1.setFieldOn(new Panda());
                break;
            case 2:
                f1.setFieldOn(new Ijedos());
                break;
            case 3:
                f1.setFieldOn(new Almos());
                break;
            case 4:
                f1.setFieldOn(new Ugralos());
                break;
            case 5:
                break;
        }

    }


    private static void DoYouWantMove() {
    }


}
