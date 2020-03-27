package com.kodilla.battleshipgame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BattleShipGame extends Application {

        private Image imageback = new Image("file:resources/imageback_1600x900.png");

        public static void main(String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gridPane = new GridPane();
        gridPane.setBackground(background);

        Scene scene = new Scene(gridPane, 1280, 720, Color.BLACK);
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
//        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Battle Ship - The Game");

        primaryStage.show();

    }
}
