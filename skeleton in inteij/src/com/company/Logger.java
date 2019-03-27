package com.company;


public class Logger {

    private static int depth = 0;


    public static int getDepth() {
        return depth;
    }

    public static void setDepth(int dep) {
        depth = dep;
    }

    public void writeMessage(String message) {
       for(int i =0; i<depth;i++)
           System.out.print("\t");
        System.out.println(message);
    }



}
