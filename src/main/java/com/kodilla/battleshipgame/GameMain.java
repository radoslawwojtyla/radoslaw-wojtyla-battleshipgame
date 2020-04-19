package com.kodilla.battleshipgame;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
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
                grid.add(field2, i + offsetX, j);
            }
        }
    }

    public void playerTurn() {
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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
                    endingWindow("YOU are");
                    grid.setOnMouseClicked(null);
                } else {
                    if (enemy.endGame()) {
                        endingWindow("YOUR OPPONENT is");
                        grid.setOnMouseClicked(null);
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

    public void endingWindow(String winner) {
        final Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        VBox windowVbox = new VBox(10);
        Label label1 = new Label("Game is over!");
        Label label2 = new Label(winner + " the winner.");
        label1.setFont(new Font("Calibri", 24));
        label2.setFont(new Font("Calibri", 24));
        windowVbox.getChildren().addAll(label1, label2);
        windowVbox.setAlignment(Pos.CENTER);
        Scene windowScene = new Scene(windowVbox, 300, 200);
        window.setScene(windowScene);
        window.show();
    }

    @Override
    public void start(final Stage primaryStage) {
        int SCENE_HEIGHT = 720;
        int SCENE_WIDTH = 1280;
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setPadding(new Insets(FIELD_SIZE));
        for (int n = 0; n < 22; n++)
            grid.getColumnConstraints().add(new ColumnConstraints(FIELD_SIZE));
        for (int n = 0; n < 10; n++)
            grid.getRowConstraints().add(new RowConstraints(FIELD_SIZE));

        final Button endGameBtn = new Button("Koniec gry");
        endGameBtn.setOnAction((event) -> {
            System.exit(0);
        });

        final Button restartGameBtn = new Button("Nowa gra");
        restartGameBtn.setOnAction((event -> {
            primaryStage.close();
            Platform.runLater(() -> new GameMain().start(new Stage()));
        }));

        grid.add(endGameBtn, 30, 3);
        grid.add(restartGameBtn, 30, 5);

        Label label1 = new Label("Your board");
        grid.add(label1, 4,15);

        Label label2 = new Label("Your oponent board");
        grid.add(label2, 15,15);


        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setTitle("BattleShip Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        gamePlay();
    }

    private void gamePlay() {
        player.initBoard();
        enemy.initBoard();
        playerBoard();
        enemyBoard();
        playerTurn();
    }


    public static void main(String[] args) {
        launch(args);
    }

}