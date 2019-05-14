package com.company;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        primaryStage.setTitle("Pandaplaza Application");
        //Menü innentől
        Scene menu;
        Button button1= new Button("Generate random map");
        Button button2= new Button("Load test map");
        Button button3= new Button("Exit");
        button1.setOnAction(e -> primaryStage.setScene(RandomMap()));
        button2.setOnAction(e -> primaryStage.setScene(TestMap()));
        button3.setOnAction(e -> primaryStage.close());
        VBox layout1 = new VBox(10);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(button1,button2,button3);
        menu = new Scene(layout1, 300, 250);

        //Adding the scene to Stage
        primaryStage.setScene(menu);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    public Scene TestMap()
    {
        int[] points_unformed = {193,310,200,158,296,190,346,272,341,376,277,450,182,480,85,441,500,330,412,197,508,174,604,209,711,338,707,180,804,218,860,299,855,400,792,477,695,504,595,466,493,492,396,457,335,532,432,569,427,671,333,631,237,561,591,630,747,584,741,684,842,658,938,692,850,558,945,593,913,482,1005,516,1106,491,1011,359,926,224,1022,201,965,113,864,139,124,178,160,91,255,100,351,194,327,130,411,133,464,116,566,111,613,118,662,105,763,134,793,106,836,87,885,66,970,49,1042,90,1071,147,1090,224,1147,312,1161,388,1144,427,1178,513,1140,568,1096,564,1058,582,1033,616,1026,672,1021,727,991,762,914,773,873,742,845,731,817,731,772,751,713,762,694,736,644,753,590,769,541,762,491,726,444,742,398,755,378,725,327,708,269,708,250,658,208,638,168,608,149,560,119,518,57,514,15,472,33,385,35,331,51,262,81,206,751,524,796,619,899,629,944,531,854,463,336,437,238,500,278,603,389,618,430,506,863,237};
        int mezok=42;
        koor[] pontoka = new koor[points_unformed.length/2];

        for (int i = 0, j = 0;i<points_unformed.length/2;i++,j+=2)
        {
            pontoka[i]=new koor();
            pontoka[i].x=points_unformed[j];
            pontoka[i].y=points_unformed[j+1];
        }
        Map_FX mf = new Map_FX(pontoka,mezok,1200,850);

        nei = mf.getNei();
        start = mf.getStart();

        SplitPane split_pane = new SplitPane();
        split_pane.setOrientation(Orientation.VERTICAL);
        Pane pane = mf.draw();
        Label label = new Label("Első játékos jön!");
        Pane label_pane = new Pane(label);
        label_pane.setPrefSize(1200,25);
        label_pane.setMaxSize(1200,25);
        label_pane.setMinSize(1200,25);
        Controller.getInstance().setLabel(label);
        //label.setVisible(false);
        Scene scene = new Scene(split_pane);
        //setting color to the scene
        scene.setFill(Color.WHITE);

        split_pane.getItems().add(label_pane);
        split_pane.getItems().add(pane);

        Random random = new Random();

        int[] randompos = new int[42];
        randompos[0] = 32;//törékeny
        randompos[1] = 22;//törékeny
        randompos[2] = 27;//csoki
        randompos[3] = 31;//fotel
        randompos[4] = 12;//játék
        randompos[5] = 25;//szekrény
        randompos[6] = 36;//szekrény
        randompos[7] = 0;//bejárat
        randompos[8] = 37;//kijárat
        randompos[9] = 9;//6 panda
        randompos[10] = 20;
        randompos[11] = 11;
        randompos[12] = 38;
        randompos[13] = 30;
        randompos[14] = 39;

        for (int ixi = 15; ixi<mezok;ixi++)
        {
            randompos[ixi]=ixi;
        }

        Group items = new Group();
        utasitasok utasitasok = new utasitasok();
        Controller.getInstance().setUtasitasok(utasitasok);

        for (int i=9;i<mezok;i++)
        {
            utasitasok.crttile(new String[]{"","tile", String.valueOf(randompos[i])});
        }

        //2 törékeny
        start[randompos[0]].ki.setStroke(Color.BLACK);
        start[randompos[0]].ki.setStrokeWidth(5);
        start[randompos[1]].ki.setStroke(Color.BLACK);
        start[randompos[1]].ki.setStrokeWidth(5);
        utasitasok.crttile(new String[]{"","breakable", String.valueOf(randompos[0])});
        utasitasok.crttile(new String[]{"","breakable", String.valueOf(randompos[1])});


        Ornagutan_FX player1 = new Ornagutan_FX(true);
        Ornagutan_FX player2 = new Ornagutan_FX(false);
        items.getChildren().add(player1.animal);
        items.getChildren().add(player2.animal);

        //6 panda
        ArrayList<Panda_FX> pandas=new ArrayList<>();
        for(int i =9; i<15;i++)
        {
            int r_type = random.nextInt(4);
            pandas.add(new Panda_FX(r_type));
            pandas.get(i-9).place(start[randompos[i]]);
            items.getChildren().add(pandas.get(i-9).animal);

            switch (r_type)
            {
                case 0:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 1:
                    utasitasok.crtunit(new String[]{"","ijedos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 2:
                    utasitasok.crtunit(new String[]{"","ugralos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 3:
                    utasitasok.crtunit(new String[]{"","almos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                default:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
            }
        }
        //1 csoki
        Rectangle csoki = new Rectangle(30,30,Color.BROWN);
        csoki.setX(start[randompos[2]].x-15);
        csoki.setY(start[randompos[2]].y-15);
        items.getChildren().add(csoki);
        utasitasok.crttile(new String[]{"","vendingm", String.valueOf(randompos[2])});

        //1 fotel
        Rectangle fotel = new Rectangle(30,30,Color.WHITE);
        fotel.setX(start[randompos[3]].x-15);
        fotel.setY(start[randompos[3]].y-15);
        items.getChildren().add(fotel);
        utasitasok.crttile(new String[]{"","sofa", String.valueOf(randompos[3])});

        //1 játék
        Rectangle jatek = new Rectangle(30,30,Color.YELLOW);
        jatek.setX(start[randompos[4]].x-15);
        jatek.setY(start[randompos[4]].y-15);
        items.getChildren().add(jatek);
        utasitasok.crttile(new String[]{"","gamem", String.valueOf(randompos[4])});

        //2 szekreny
        Rectangle sz1  = new Rectangle(30,30,Color.GRAY);
        Rectangle sz2  = new Rectangle(30,30,Color.GRAY);
        sz1.setX(start[randompos[5]].x-15);
        sz2.setX(start[randompos[6]].x-15);
        sz1.setY(start[randompos[5]].y-15);
        sz2.setY(start[randompos[6]].y-15);
        items.getChildren().add(sz1);
        items.getChildren().add(sz2);
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(randompos[5])});
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(randompos[6])});

        //bekijárat
        Polygon be = new Polygon(15.0,0.0,0.0,30.0,30.0,30.0);
        Polygon ki = new Polygon(0.0,0.0,30.0,0.0,15.0,30.0);
        be.setFill(Color.GREEN);
        ki.setFill(Color.RED);
        be.relocate(start[randompos[7]].x,start[randompos[7]].y);
        ki.relocate(start[randompos[8]].x,start[randompos[8]].y);
        items.getChildren().add(be);
        items.getChildren().add(ki);
        utasitasok.crttile(new String[]{"","entry", String.valueOf(randompos[7])});
        utasitasok.crttile(new String[]{"","exit", String.valueOf(randompos[8])});

        for(int x= 0; x< mezok;x++)
        {
            for (int y = 0; y<mezok;y++)
            {
                if (x==y){nei[x][y]=false;}
                if (nei[x][y])
                {
                    utasitasok.linkt(new String[]{"",String.valueOf(x), String.valueOf(y)});
                }
            }
        }

        utasitasok.linkw(new String[]{"",String.valueOf(randompos[5]), String.valueOf(randompos[6])});//szekrény kötés azért itt mert kellenek hozzá a szomszédok

        Controller.getInstance().setPandas(pandas);
        Controller.getInstance().setPlayer1(player1);
        Controller.getInstance().setPlayer2(player2);
        Controller.getInstance().setStart(start);
        Controller.getInstance().setNei(nei);
        ArrayList<Ornagutan_FX> orangutans = new ArrayList<>();
        orangutans.add(player1);
        orangutans.add(player2);
        Controller.getInstance().setOrangutan(orangutans);
        Group lines = new Group();
        Controller.getInstance().setLines(lines);

        player1.place(start[randompos[7]]);
        utasitasok.crtunit(new String[] {"","orangutan",String.valueOf(randompos[7]),"player1"});
        utasitasok.crtunit(new String[] {"","orangutan","fake","player2"});

        utasitasok.player_init();

        items.setMouseTransparent(true);
        pane.getChildren().add(lines);
        pane.getChildren().add(items);
        return scene;
    }

    public Scene RandomMap()
    {
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
        Controller.getInstance().setUtasitasok(utasitasok);

        for (int i=9;i<mezok;i++)
        {
            utasitasok.crttile(new String[]{"","tile", String.valueOf(randompos[i])});
        }

        //2 törékeny
        start[randompos[0]].ki.setStroke(Color.BLACK);
        start[randompos[0]].ki.setStrokeWidth(5);
        start[randompos[1]].ki.setStroke(Color.BLACK);
        start[randompos[1]].ki.setStrokeWidth(5);
        utasitasok.crttile(new String[]{"","breakable", String.valueOf(randompos[0])});
        utasitasok.crttile(new String[]{"","breakable", String.valueOf(randompos[1])});


        Ornagutan_FX player1 = new Ornagutan_FX(true);
        Ornagutan_FX player2 = new Ornagutan_FX(false);
        items.getChildren().add(player1.animal);
        items.getChildren().add(player2.animal);

        //6 panda
        ArrayList<Panda_FX> pandas=new ArrayList<>();
        for(int i =9; i<15;i++)
        {
            int r_type = random.nextInt(4);
            pandas.add(new Panda_FX(r_type));
            pandas.get(i-9).place(start[randompos[i]]);
            items.getChildren().add(pandas.get(i-9).animal);

            switch (r_type)
            {
                case 0:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 1:
                    utasitasok.crtunit(new String[]{"","ijedos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 2:
                    utasitasok.crtunit(new String[]{"","ugralos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                case 3:
                    utasitasok.crtunit(new String[]{"","almos", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
                    break;
                default:
                    utasitasok.crtunit(new String[]{"","panda", String.valueOf(randompos[i]),"panda"+String.valueOf(i-9)});
            }
        }
        //1 csoki
        Rectangle csoki = new Rectangle(30,30,Color.BROWN);
        csoki.setX(start[randompos[2]].x-15);
        csoki.setY(start[randompos[2]].y-15);
        items.getChildren().add(csoki);
        utasitasok.crttile(new String[]{"","vendingm", String.valueOf(randompos[2])});

        //1 fotel
        Rectangle fotel = new Rectangle(30,30,Color.WHITE);
        fotel.setX(start[randompos[3]].x-15);
        fotel.setY(start[randompos[3]].y-15);
        items.getChildren().add(fotel);
        utasitasok.crttile(new String[]{"","sofa", String.valueOf(randompos[3])});

        //1 játék
        Rectangle jatek = new Rectangle(30,30,Color.YELLOW);
        jatek.setX(start[randompos[4]].x-15);
        jatek.setY(start[randompos[4]].y-15);
        items.getChildren().add(jatek);
        utasitasok.crttile(new String[]{"","gamem", String.valueOf(randompos[4])});

        //2 szekreny
        Rectangle sz1  = new Rectangle(30,30,Color.GRAY);
        Rectangle sz2  = new Rectangle(30,30,Color.GRAY);
        sz1.setX(start[randompos[5]].x-15);
        sz2.setX(start[randompos[6]].x-15);
        sz1.setY(start[randompos[5]].y-15);
        sz2.setY(start[randompos[6]].y-15);
        items.getChildren().add(sz1);
        items.getChildren().add(sz2);
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(randompos[5])});
        utasitasok.crttile(new String[]{"","wardrobe", String.valueOf(randompos[6])});

        //bekijárat
        Polygon be = new Polygon(15.0,0.0,0.0,30.0,30.0,30.0);
        Polygon ki = new Polygon(0.0,0.0,30.0,0.0,15.0,30.0);
        be.setFill(Color.GREEN);
        ki.setFill(Color.RED);
        be.relocate(start[randompos[7]].x,start[randompos[7]].y);
        ki.relocate(start[randompos[8]].x,start[randompos[8]].y);
        items.getChildren().add(be);
        items.getChildren().add(ki);
        utasitasok.crttile(new String[]{"","entry", String.valueOf(randompos[7])});
        utasitasok.crttile(new String[]{"","exit", String.valueOf(randompos[8])});

        for(int x= 0; x< mezok;x++)
        {
            for (int y = 0; y<mezok;y++)
            {
                if (x==y){nei[x][y]=false;}
                if (nei[x][y])
                {
                    utasitasok.linkt(new String[]{"",String.valueOf(x), String.valueOf(y)});
                }
            }
        }

        utasitasok.linkw(new String[]{"",String.valueOf(randompos[5]), String.valueOf(randompos[6])});//szekrény kötés azért itt mert kellenek hozzá a szomszédok

        Controller.getInstance().setPandas(pandas);
        Controller.getInstance().setPlayer1(player1);
        Controller.getInstance().setPlayer2(player2);
        Controller.getInstance().setStart(start);
        Controller.getInstance().setNei(nei);
        ArrayList<Ornagutan_FX> orangutans = new ArrayList<>();
        orangutans.add(player1);
        orangutans.add(player2);
        Controller.getInstance().setOrangutan(orangutans);
        Group lines = new Group();
        Controller.getInstance().setLines(lines);

        player1.place(start[randompos[7]]);
        utasitasok.crtunit(new String[] {"","orangutan",String.valueOf(randompos[7]),"player1"});
        utasitasok.crtunit(new String[] {"","orangutan","fake","player2"});

        utasitasok.player_init();

        items.setMouseTransparent(true);
        pane.getChildren().add(lines);
        pane.getChildren().add(items);
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
