package com.company;


public class Logger {

    private static int depth = -1;
    private static int depth = 0;
>>>>>>> sárosi


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

    public static void writeMessage(String message) {
        depth++;
       for(int i =0; i<depth;i++)
           System.out.print("\t");
        System.out.println(message);

        depth--;
    }



}
