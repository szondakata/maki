package com.company;


/**
 * A bejaratot megvalosito osztaly
 */
public class Entry extends Field {

    private  static Entry instance;

    private Entry()
        {
        }

        static {
        instance = new Entry();
        }

    public static Entry getInstance() {
        return instance;
    }
}
