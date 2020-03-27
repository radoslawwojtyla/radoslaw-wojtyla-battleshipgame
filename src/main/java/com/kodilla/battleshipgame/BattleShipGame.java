package com.kodilla.battleshipgame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class BattleShipGame extends Application {

        private Image imageback = new Image("file:resources/image_ships_1280x720.png");

        public static void main(String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(1360, 900, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1280, 720, Color.BLACK);

//        primaryStage.setResizable(false);
//        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Battle Ship - The Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
