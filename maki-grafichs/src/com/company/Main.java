package com.company;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    boolean[][] nei;
    koor[] start;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int mezok =42;
        Map_FX mf = new Map_FX(mezok,1200,600);
        nei = mf.getNei();
        start = mf.getStart();

        SplitPane split_pane = new SplitPane();
        split_pane.setOrientation(Orientation.VERTICAL);
        Pane pane = mf.draw();
        Label label = new Label("Első játékos jön!");
        Pane label_pane = new Pane(label);
        label_pane.setPrefSize(1200,25);
        label_pane.setMaxSize(1200,25);
        Controller.getInstance().setLabel(label);
        //label.setVisible(false);
        Scene scene = new Scene(split_pane);
        //setting color to the scene
        scene.setFill(Color.WHITE);

        split_pane.getItems().add(label_pane);
        split_pane.getItems().add(pane);
        //Setting the title to Stage.
        primaryStage.setTitle("Pandaplaza Application");
        int[] randompos = new int[mezok];
        Random random = new Random();
        int actual= -1;
        boolean egyedi=false;
        for (int y = 0; y < mezok; y++) {
            randompos[y]=-1;
        }
        for (int i = 0;i<mezok;i++) {
            do{
                actual = random.nextInt(mezok);
                egyedi=false;
                for (int y = 0; y < mezok; y++) {
                    if (actual==randompos[y]){egyedi=true;}
                }
            } while (egyedi);
            randompos[i]=actual;
        }
        Group items = new Group();
        utasitasok utasitasok = new utasitasok();

        for (int i=0;i<mezok;i++)
        {
            if (i!=randompos[6]&&i!=randompos[7]) {
                utasitasok.crttile(new String[]{"","tile", String.valueOf(i)});
            }
            else
            {
                utasitasok.crttile(new String[]{"","breakable", String.valueOf(i)});
            }
        }

        //2 törékeny
        start[randompos[6]].ki.setStroke(Color.BLACK);
        start[randompos[6]].ki.setStrokeWidth(5);
        start[randompos[7]].ki.setStroke(Color.BLACK);
        start[randompos[7]].ki.setStrokeWidth(5);

        Ornagutan_FX player1 = new Ornagutan_FX(true);
        Ornagutan_FX player2 = new Ornagutan_FX(false);
        items.getChildren().add(player1.animal);
        items.getChildren().add(player2.animal);

        //6 panda
        ArrayList<Panda_FX> pandas=new ArrayList<>();
        for(int i =0; i<6;i++)
        {
            int r_type = random.nextInt(3);
            pandas.add(new Panda_FX(r_type));
            pandas.get(i).place(start[randompos[i]]);
            items.getChildren().add(pandas.get(i).animal);

            switch (r_type)
            {
                case 0:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(i),"panda"+String.valueOf(i)});
                    break;
                case 1:
                    utasitasok.crtunit(new String[]{"","ijedos", String.valueOf(i),"panda"+String.valueOf(i)});
                    break;
                case 2:
                    utasitasok.crtunit(new String[]{"","ugralos", String.valueOf(i),"panda"+String.valueOf(i)});
                    break;
                case 3:
                    utasitasok.crtunit(new String[]{"","almos", String.valueOf(i),"panda"+String.valueOf(i)});
                    break;
                default:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(i),"panda"+String.valueOf(i)});
            }
        }
        //1 csoki
        Rectangle csoki = new Rectangle(30,30,Color.BROWN);
        csoki.setX(start[randompos[8]].x-15);
        csoki.setY(start[randompos[8]].y-15);
        items.getChildren().add(csoki);
        utasitasok.crttile(new String[]{"","vendingm", String.valueOf(8)});

        //1 fotel
        Rectangle fotel = new Rectangle(30,30,Color.WHITE);
        fotel.setX(start[randompos[9]].x-15);
        fotel.setY(start[randompos[9]].y-15);
        items.getChildren().add(fotel);
        utasitasok.crttile(new String[]{"","sofa", String.valueOf(9)});

        //1 játék
        Rectangle jatek = new Rectangle(30,30,Color.YELLOW);
        jatek.setX(start[randompos[10]].x-15);
        jatek.setY(start[randompos[10]].y-15);
        items.getChildren().add(jatek);
        utasitasok.crttile(new String[]{"","gamem", String.valueOf(10)});

        //2 szekreny
        Rectangle sz1  = new Rectangle(30,30,Color.GRAY);
        Rectangle sz2  = new Rectangle(30,30,Color.GRAY);
        sz1.setX(start[randompos[11]].x-15);
        sz2.setX(start[randompos[12]].x-15);
        sz1.setY(start[randompos[11]].y-15);
        sz2.setY(start[randompos[12]].y-15);
        items.getChildren().add(sz1);
        items.getChildren().add(sz2);
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(11)});
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(12)});

        //bekijárat
        Polygon be = new Polygon(15.0,0.0,0.0,30.0,30.0,30.0);
        Polygon ki = new Polygon(0.0,0.0,30.0,0.0,15.0,30.0);
        be.setFill(Color.GREEN);
        ki.setFill(Color.RED);
        be.relocate(start[randompos[13]].x,start[randompos[13]].y);
        ki.relocate(start[randompos[14]].x,start[randompos[14]].y);
        items.getChildren().add(be);
        items.getChildren().add(ki);
        utasitasok.crttile(new String[]{"","entry", String.valueOf(13)});
        utasitasok.crttile(new String[]{"","exit", String.valueOf(14)});

        for(int x= 0; x< mezok;x++)
        {
            for (int y = 0; y<mezok;y++)
            {
                if (x!=y&&nei[x][y])
                {
                    utasitasok.linkt(new String[]{"",String.valueOf(x), String.valueOf(y)});
                }
            }
        }


        items.setMouseTransparent(true);
        pane.getChildren().add(items);
        //Adding the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
