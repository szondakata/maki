package com.company;

public class Controller {

    public void setNei(boolean[][] nei) {
        this.nei = nei;
    }

    public void setStart(koor[] start) {
        this.start = start;
    }

    boolean[][] nei;
    koor[] start;

    public static Controller getInstance() {
        if (instance==null){instance=new Controller();}
        return instance;
    }

    static Controller instance;

    private Controller() {
        instance = this;
    }

    public void mouse_click(int mit)
    {
        System.out.println("didit: "+String.valueOf(mit));
    }


}
