package com.kodilla.battleshipgame;

import javafx.application.Application;
import javafx.application.Platform;
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
                boolean gameOver = false;
                int setX = (int) Math.ceil((event.getX() - 30) / FIELD_SIZE) - 1;
                int setY = (int) Math.ceil((event.getY() - 210) / FIELD_SIZE) - 1;

                if (setX >= 0 && setX <= 9 && setY >= 0 && setY <= 9 && player.fieldWithNoHit(setX, setY)) {
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
                if (player.endGame()) {
                    System.out.println("Wygrałeś");
                    System.exit(0);

                } else {
                    if (enemy.endGame()) {
                        System.out.println("Wygrał przeciwnik");
                        System.exit(0);
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

    public void newGame() {
        player.initBoard();
        enemy.initBoard();
        playerBoard();
        enemyBoard();
    }

    @Override
    public void start(final Stage primaryStage) {
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setPadding(new Insets(FIELD_SIZE));
        for (int n = 0; n < 22; n++)
            grid.getColumnConstraints().add(new ColumnConstraints(FIELD_SIZE));
        for (int n = 0; n < 10; n++)
            grid.getRowConstraints().add(new RowConstraints(FIELD_SIZE));

        int SCENE_HEIGHT = 720;
        int SCENE_WIDTH = 1280;

        Button endGame = new Button("Koniec gry");
        endGame.setOnAction((event) -> {
            System.exit(0);
        });

        final Button restartGame = new Button("Nowa gra");
        restartGame.setOnAction((event -> {
                    primaryStage.close();
                    Platform.runLater(() -> new GameMain().start(new Stage()));
                }));

        grid.add(endGame, 30, 3);
        grid.add(restartGame, 30, 5);

        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("BattleShip Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        newGame();
        playerTurn();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
