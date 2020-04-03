package com.kodilla.battleshipgame;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Board4test {

    int[][] sampleBoard = new int[10][];

    public String checkHorizontalBoarders(int y, int shipSize) {
        if (y + shipSize - 1 < 10) {
            return "right";
        } else {
            return "left";
        }
    }


    public void checkHorizontalCollisions(int x, int y, int shipSize) {
        String result = "impossible";
        boolean loop = true;
        int counter = 1;
        if (checkHorizontalBoarders(y, shipSize).equals("right")) {
            while (sampleBoard[x][y] == 0 && loop) {
                counter++;
                if (counter > shipSize) {
                    loop = false;
                    result = "right";
                }
                System.out.println(result);
            }
        }
        System.out.println("FINAL: " + result);
    }


    public void placeShip(int shipSize, String orientation, int x, int y) {
        for (int i = 0; i < shipSize; i++) {
            if (orientation.equals("right"))
                sampleBoard[x][y + i] = shipSize;
            else if (orientation.equals("left"))
                sampleBoard[x][y - i] = shipSize;
            else if (orientation.equals("down"))
                sampleBoard[x + i][y] = shipSize;
            else if (orientation.equals("up"))
                sampleBoard[x - i][y] = shipSize;
        }
    }

    public Board4test() {
        for (int i = 0; i < 10; i++) {
            sampleBoard[i] = new int[10];
        }
    }

    public void initBoard() {
//        placeShipRandomly(6);
//        placeShipRandomly( 5);
//        placeShipRandomly( 5);
//        placeShipRandomly( 4);
//        placeShipRandomly( 4);
//        placeShipRandomly( 3);
//        placeShipRandomly( 3);
//        placeShipRandomly( 2);
//        placeShipRandomly( 2);
    }


    public void printBoard() {
        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                System.out.print(sampleBoard[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void checkEndGame() {
    }
}