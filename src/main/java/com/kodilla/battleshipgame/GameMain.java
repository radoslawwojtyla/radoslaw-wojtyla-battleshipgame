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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.sql.SQLOutput;


public class GameMain extends Application {

    GridPane grid = new GridPane();
    int FIELD_SIZE = 30;
    Board player = new Board();
    Board enemy = new Board();
    boolean gameOver = false;
    boolean playerTurn = true;
    private int offsetX  = 11;

    // col row constrains


    public void playerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle field = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.GREY);
                field.setStroke(Color.BLACK);
                grid.add(field, i, j);
            }
        }
    }

    public void enemyBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle field2 = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.DARKGRAY);
                field2.setStroke(Color.BLACK);
                grid.add(field2, i+11, j);
            }
        }
    }

    public void playerTurn() {
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int setX = (int) Math.ceil((event.getX() - 30) / FIELD_SIZE) - 1;
                int setY = (int) Math.ceil((event.getY() - 210) / FIELD_SIZE) - 1;
                if (setX >= 0 && setX <= 9 && setY >= 0 && setY <= 9) {
                    int result = player.sampleBoard[setX][setY];
                    if (result == 0) {
                        Rectangle miss = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.BLACK);
                        grid.add(miss, setX, setY);
                    } else if (result > 0 && result < 10) {
                        Rectangle hit = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.RED);
                        grid.add(hit, setX, setY);
                    }
                    player.shootingByUser(setX, setY);
                    player.printBoardToShow();
                    if (false) {
                        // user wygrał
                    } else {
                        enemyTurn();
                        if (false) {
                            // wygrał PC
                            // zamkniecie okna >>> reset gry ///
                        }
                    }
                }

            }
        });
    }

    public void enemyTurn() {
        enemy.shootingByEnemy();
        int setX = enemy.coordinates[0];
        int setY = enemy.coordinates[1];
        int result = enemy.sampleBoard[setX][setY];
        if (result == 10) {
            Rectangle miss = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.BLACK);
            grid.add(miss, setX + offsetX, setY);
        } else if (result > 10) {
            Rectangle hit = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.RED);
            grid.add(hit, setX + offsetX, setY);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setPadding(new Insets(FIELD_SIZE));
        for (int n=0; n<20; n++)
            grid.getColumnConstraints().add(new ColumnConstraints(FIELD_SIZE));
        for (int n=0; n<10; n++)
            grid.getRowConstraints().add(new RowConstraints(FIELD_SIZE));
//        grid.setHgap(2); // odstępy
//        grid.setVgap(2); // odstepy


        int SCENE_HEIGHT = 720;
        int SCENE_WIDTH = 1280;
        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("BattleShip Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        player.initBoard();
        enemy.initBoard();


        playerBoard();
        enemyBoard();

        playerTurn();
//        player.printBoardToShow();
//        System.out.println();
//        player.printBoard();

//        enemyTurn();
//        System.out.println();
//        enemy.printBoardToShow();


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