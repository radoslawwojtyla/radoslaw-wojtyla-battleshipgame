package com.kodilla.battleshipgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameMain extends Application {

    GridPane grid = new GridPane();
    int FIELD_SIZE = 30;
    Board player = new Board();
    Board enemy = new Board();
    boolean gameOver = false;
    boolean playerTurn = true;
    private int offsetX = 11;


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
                grid.add(field2, i + 11, j);
            }
        }
    }

    public void playerTurn() {
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean loop = true;
                int setX = (int) Math.ceil((event.getX() - 30) / FIELD_SIZE) - 1;
                int setY = (int) Math.ceil((event.getY() - 210) / FIELD_SIZE) - 1;
                if (setX >= 0 && setX <= 9 && setY >= 0 && setY <= 9) {
                    player.shootingByUser(setX, setY);
                    int result = player.sampleBoard[setX][setY];
                    if (result == 10) {
                        Rectangle miss = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.BLACK);
                        grid.add(miss, setX, setY);
                        enemyTurn();
                    } else if (result > 10) {
                        Rectangle hit = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.RED);
                        grid.add(hit, setX, setY);
                    }
                }
                if (false) {
                    // user wygrał
                } else {
                    if (false) {
                    }
                }
            }
        });
    }

    public void enemyTurn() {
        boolean loop = true;
        while (loop) {
            enemy.shootingByEnemy();
            int setX = enemy.coordinates[0];
            int setY = enemy.coordinates[1];
            int result = enemy.sampleBoard[setX][setY];
            if (result == 10) {
                Rectangle miss = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.BLACK);
                grid.add(miss, setX + offsetX, setY);
                loop = false;
            } else if (result > 10) {
                Rectangle hit = new Rectangle(FIELD_SIZE, FIELD_SIZE, Color.RED);
                grid.add(hit, setX + offsetX, setY);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setPadding(new Insets(FIELD_SIZE));
        for (int n = 0; n < 20; n++)
            grid.getColumnConstraints().add(new ColumnConstraints(FIELD_SIZE));
        for (int n = 0; n < 10; n++)
            grid.getRowConstraints().add(new RowConstraints(FIELD_SIZE));

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

//        while (!gameOver) {
        playerTurn();
//            enemyTurn();
//            if (player.endGame() || enemy.endGame()) {
//                gameOver = true;
//            }
//        }

//        while (!gameOver) {
//            String winner;
//            playerTurn();
//            enemyTurn();
//            if (player.endGame() || enemy.endGame()) {
//                gameOver = true;
//                winner = player.endGame() == true ? "You are" : "Your opponent";
//                System.out.println("Game Over");
//                System.out.println(winner + " the winner");
//            }
//        }


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
