package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{



        Map_FX mf = new Map_FX();
        mf.getStart();
        Pane pane = mf.draw(500,500,10);
        Scene scene = new Scene(pane);
        //setting color to the scene
        scene.setFill(Color.WHITE);

        //Setting the title to Stage.
        primaryStage.setTitle("Pandaplaza Application");

        //Adding the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
