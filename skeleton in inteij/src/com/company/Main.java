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

    public static void movepanda_test(){}
    static public void moveorangutan_test(){}
    static public void release_test(){}
    static public void end_test(){}
    static public void usewardrobe_test(){}
    static public void orangutanexit_test(){}
    static public void pandadies_test(){}
    static public void furnitur_test(){}
    static public void sitting_test(){}
    static public void scaring_test(){}
    static public void jumping_test(){}
}
