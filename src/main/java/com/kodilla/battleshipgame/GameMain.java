package com.kodilla.battleshipgame;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;


public class GameMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        int FIELD_SIZE = 30;

        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setPadding(new Insets(FIELD_SIZE));
        grid.setHgap(2); // odstępy
        grid.setVgap(2); // odstepy

        int SCENE_HEIGHT = 720;
        int SCENE_WIDTH = 1280;
        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("BattleShip Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        Board player = new Board();
        Board enemy = new Board();
        boolean gameOver = false;

        player.initBoard();
        enemy.initBoard();

        // plansza gracza
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Rectangle field = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.GREY);
                grid.add(field, i, j);
                grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int setX = (int) Math.ceil((event.getX() - 30) / 32) - 1;
                        int setY = (int) Math.ceil((event.getY() - 201) / 32) - 1;
                        int result = player.sampleBoard[setX][setY];
                        if (result == 0) {
                            Rectangle miss = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.BLACK);
                            grid.add(miss, setX, setY);
                        } else if (result > 0 && result < 10) {
                            Rectangle hit = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.RED);
                            grid.add(hit, setX, setY);
                        }
                    }
                });
            }
        }

// plansza enemy
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle field2 = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.DARKGRAY);
                grid.add(field2, i + 100, j);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}


//            while (!gameOver) {
//            String winner;
//            player.shootingByUser();
//            enemy.shootingByEnemy();
//
//            if (player.endGame() || enemy.endGame()) {
//                gameOver = true;
//                winner = player.endGame() == true ? "You are" : "Your opponent";
//                System.out.println("Game Over");
//                System.out.println(winner + " the winner");
//            }
//        }