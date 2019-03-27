package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Mit szeretnél tesztelni?");
        System.out.println("01 - MovePanda");
        System.out.println("02 - MoveOrangutan");
        System.out.println("03 - Release");
        System.out.println("04 - End");
        System.out.println("05 - UseWardrobe");
        System.out.println("06 - Orangutan exit");
        System.out.println("07 - Panda Dies");
        System.out.println("08 - Furniture Interac");
        System.out.println("09 - Sitting");
        System.out.println("10 - Scaring");
        System.out.println("11 - Jumping");

        switch (scanner.nextInt()){
            case 1:
                movepanda_test();
                break;
            case 2:
                moveorangutan_test();
                break;
            case 3:
                release_test();
                break;
            case 4:
                end_test();
                break;
            case 5:
                usewardrobe_test();
                break;
            case 6:
                orangutanexit_test();
                break;
            case 7:
                pandadies_test();
                break;
            case 8:
                furnitur_test();
                break;
            case 9:
                sitting_test();
                break;
            case 10:
                scaring_test();
                break;
            case 11:
                jumping_test();
                break;
                default:
                    System.out.println("Érvénytelen input");
                    break;
        }

    }

    public static void movepanda_test(){
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Exit());
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        p2.setIamon(fields.get(0));
        fields.get(0).setContain(p2);
        p1.setIamon(fields.get(1));
        fields.get(1).setContain(p1);
        p2.setHand1(p1);
        p1.setHand2(p2);
        p1.move(fields.get(2));
        p1.move(fields.get(3));
        System.out.println("Move panda test done!");
    }

    static public void moveorangutan_test(){
        Control control = new Control();
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        control.pandas.add(p1);
        control.pandas.add(p2);
        Orangutan orangutan = new Orangutan();
        Orangutan enemy = new Orangutan();
        control.orangutans.add(orangutan);
        control.orangutans.add(enemy);
        Player player1 = new Player();
        Player player2 = new Player();
        control.setP1(player1);
        control.setP2(player2);
        p1.setIamon(fields.get(0));
        fields.get(0).setContain(p1);
        orangutan.setIamon(fields.get(1));
        fields.get(1).setContain(orangutan);
        p2.setIamon(fields.get(3));
        fields.get(3).setContain(p2);
        enemy.setIamon(fields.get(4));
        fields.get(4).setContain(enemy);
        orangutan.setHand2(p1);
        p1.setHand1(orangutan);
        control.move(control.orangutans.get(0),fields.get(2));
        control.move(control.orangutans.get(0),fields.get(3));
        control.move(control.orangutans.get(0),fields.get(4));
        System.out.println("Move Orangutan test done!");
    }

    static public void release_test(){
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        fields.add(new Field());
        Panda p1 = new Panda();
        Panda p2 = new Panda();
        Panda p3 = new Panda();
        Panda p4 = new Panda();
        Orangutan orangutan = new Orangutan();
        fields.get(0).setContain(p1);
        p1.setIamon(fields.get(0));
        fields.get(1).setContain(p2);
        p2.setIamon(fields.get(1));
        fields.get(2).setContain(p3);
        p3.setIamon(fields.get(2));
        fields.get(3).setContain(p4);
        p4.setIamon(fields.get(3));
        fields.get(4).setContain(orangutan);
        orangutan.setIamon(fields.get(4));
        orangutan.move(fields.get(3));
        orangutan.move(fields.get(2));
        orangutan.move(fields.get(1));
        orangutan.move(fields.get(0));
        p2.release();
        System.out.println("Realease test done!");
    }


    static public void end_test(){

    }


    static public void usewardrobe_test(){}
    static public void orangutanexit_test(){}
    static public void pandadies_test(){}
    static public void furnitur_test(){}
    static public void sitting_test(){}
    static public void scaring_test(){}
    static public void jumping_test(){}
}
