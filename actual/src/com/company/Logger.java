package com.company;


/**
 * A loggert megvalosito osztaly kiirja standard inputra a fuggvenyhivasokat
 */
public class Logger {

    /**
     * A behuzasmerete
     */
    private static int depth = -1;

    /**
     * Be van-e kapcsolva
     */
    public static boolean enabled = false;

    /** A fuggveny megmutatja a behuzas merteket
     * @return a behuzas merteke
     */
    public static int getDepth() {
        return depth;
    }

    /** A fuggveny beallitja a behuzas merteket
     * @param dep a behuzas merteke
     */
    public static void setDepth(int dep) {
        depth = dep;
    }

    /**
     * A fuggveny noveli a behuzast
     */
    public static void depthP()
    {
        depth++;
    }

    /**
     * A fuggveny csokkenti a behuzast
     */
    public static void depthM()
    {
        depth--;
    }

    /** A fuggveny a parameterben kapott uzenetet a standard outputra tovabbitja
     * @param message az uzenet
     */
    public static void writeMessage(String message) {
        if(enabled) {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");
            System.out.println(message);
        }
    }



}
