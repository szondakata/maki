package com.company;
import javafx.scene.control.Label;

public class Controller {

    public void setNei(boolean[][] nei) {
        this.nei = nei;
    }

    public void setStart(koor[] start) {
        this.start = start;
    }

    boolean[][] nei;
    koor[] start;

    public void setLabel(Label label) {
        this.label = label;
    }

    Label label;

    public static Controller getInstance() {
        if (instance==null){instance=new Controller();}
        return instance;
    }

    static Controller instance;

    private Controller() {
        instance = this;
    }
    boolean egyes=true;
    public void mouse_click(int mit)
    {
        System.out.println("didit: "+String.valueOf(mit));
        egyes= !egyes;
        label.setText(egyes?"Első játékos jön!":"Második játékos jön!");
    }


}
