package com.company;


public class Logger {

    private static int depth = -1;


    public static int getDepth() {
        return depth;
    }

    public static void setDepth(int dep) {
        depth = dep;
    }
    public static void depthP()
    {
        depth++;
    }
    public static void depthM()
    {
        depth--;
    }

    public void writeMessage(String message) {
       for(int i =0; i<depth;i++)
           System.out.print("\t");
        System.out.println(message);
    }



}
