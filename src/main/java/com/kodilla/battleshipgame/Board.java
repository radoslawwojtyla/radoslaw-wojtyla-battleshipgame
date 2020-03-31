package com.kodilla.battleshipgame;

import java.util.Random;

public class Board {

    int x;
    int y;
    int direction;
    int shipSize;

    int[][] sampleBoard;

    public boolean checkHorizontalBoarders(int x, int shipSize) {
        if (x + shipSize - 1 < 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkVerticalBoarders(int y, int shipSize) {
        if (y + shipSize - 1 < 10) {
            return true;
        } else {
            return false;
        }
    }

    public void checkCollisions(int x, int y, int direction, int size) {

    }

    public void shipDrawing(int[][] sampleBoard, int shipSize) {
        Random random = new Random();
        x = random.nextInt(10);
        y = random.nextInt(10);
        direction = random.nextInt(2);          // 0 -> pion, 1 -> poziom

        if (direction == 0) {
            if (checkHorizontalBoarders(x, shipSize)) {
                for (int i = 0; i < shipSize; i++) {
                    sampleBoard[x + i][y] = shipSize;
                }
            } else {
                for (int i = 0; i < shipSize; i++) {
                    sampleBoard[x - i][y] = shipSize;
                }
            }
        } else if (direction == 1) {
            if (checkVerticalBoarders(y, shipSize)) {
                for (int i = 0; i < shipSize; i++) {
                    sampleBoard[x][y + i] = shipSize;
                }
            } else {
                for (int i = 0; i < shipSize; i++) {
                    sampleBoard[x][y - i] = shipSize;
                }
            }
        }
    }

    public void initBoard(int[][] sampleBoard) {

        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                sampleBoard[i][j] = 0;
            }
        }
        shipDrawing(sampleBoard, 4);
        shipDrawing(sampleBoard, 3);
        shipDrawing(sampleBoard, 3);
        shipDrawing(sampleBoard, 2);
        shipDrawing(sampleBoard, 2);
        shipDrawing(sampleBoard, 2);

    }


    public static void printBoard(int[][] sampleBoard) {
        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                System.out.print(sampleBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}