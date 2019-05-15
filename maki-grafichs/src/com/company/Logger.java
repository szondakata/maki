package com.company;


public class Logger {

<<<<<<< HEAD
    /**
     * Indentálásért felelős változó
     */
    private static int depth = -1;


    /**
     * A jelenlegi indentálás mélységét adja vissza
     *
     * @return indentálás jelenlegi mélysége
     */
=======

    private static int depth = -1;



>>>>>>> parent of 69c0ab9... Merge branch 'master-baszó' of https://github.com/szondakata/maki into master-baszó
    public static int getDepth() {
        return depth;
    }

    /**
     * A jelenlegi indentálás mélységét állítja be
     *
     * @param dep jelenlegi indentálás mélységét állítja be
     */
    public static void setDepth(int dep) {
        depth = dep;
    }

<<<<<<< HEAD
    /**
     * A jelenlegi indentálás mélységét növeli
     */
    public static void depthP() {
        depth++;
    }

    /**
     * A jelenlegi indentálás mélységét csökkenti
     */
    public static void depthM() {
        depth--;
    }

    /**
     * A paraméterben kapott üzenetet jeleníti meg a std outputon
     *
     * @param message a kiírt üzenet
     */
    public static void writeMessage(String message) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
=======
    public static void depthP()
    {
        depth++;
    }
    public static void depthM()
    {
        depth--;
    }

    public static void writeMessage(String message) {
 
       for(int i =0; i<depth;i++)
           System.out.print("\t");
>>>>>>> parent of 69c0ab9... Merge branch 'master-baszó' of https://github.com/szondakata/maki into master-baszó
        System.out.println(message);

    }



}
