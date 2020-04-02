package com.kodilla.battleshipgame;

import java.util.Random;
import java.util.Scanner;

public class Board {

    int[][] sampleBoard = new int[10][];

    public String checkHorizontalBoarders(int y, int shipSize) {
        if (y + shipSize - 1 < 10) {
            return "right";
        } else {
            return "left";
        }
    }

    public String checkVerticalBoarders(int x, int shipSize) {
        if (x + shipSize - 1 < 10) {
            return "down";
        } else {
            return "up";
        }
    }

    public String checkHorizontalCollisions(int x, int y, int shipSize) {
        String result = "impossible";
        boolean loop = true;
        if (checkHorizontalBoarders(y, shipSize) == "right") {
            int counter = 1;
            while (sampleBoard[x][y] == 0 && loop == true) {
                counter++;
                y++;
                if (counter == shipSize) {
                    loop = false;
                    result = "right";
                }
            }
        } else if (checkHorizontalBoarders(y, shipSize) == "left") {
            int counter = 1;
            while (sampleBoard[x][y] == 0 && loop == true) {
                counter++;
                y--;
                if (counter == shipSize) {
                    loop = false;
                    result = "left";
                }
            }
        }
        return result;
    }

    public String checkVerticalCollisions(int x, int y, int shipSize) {
        String result = "impossible";
        boolean loop = true;
        if (checkVerticalBoarders(x, shipSize) == "down") {
            int counter = 1;
            while (sampleBoard[x][y] == 0 && loop == true) {
                counter++;
                x++;
                if (counter == shipSize) {
                    loop = false;
                    result = "down";
                }
            }
        } else if (checkVerticalBoarders(x, shipSize) == "up") {
            int counter = 1;
            while (sampleBoard[x][y] == 0 && loop == true) {
                counter++;
                x--;
                if (counter == shipSize) {
                    loop = false;
                    result = "up";
                }
            }
        }
        return result;
    }

    public boolean firstPointCheck(int x, int y) {
        if (sampleBoard[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void placeShipRandomly(int shipSize) {
        Random random = new Random();

        boolean loop = true;
        int x = 0;
        int y = 0;
        String orientation = "";
        while (loop) {
            int direction = random.nextInt(2);          // 0 -> horizontal, 1 -> vertical
            x = random.nextInt(10);
            y = random.nextInt(10);
            if (direction == 0) {
                orientation = checkHorizontalCollisions(x, y, shipSize);
            } else {
                orientation = checkVerticalCollisions(x, y, shipSize);
            }

            if (firstPointCheck(x, y) && !orientation.equals("impossible")) {
                loop = false;
            }
        }
        placeShip(shipSize, orientation, x, y);
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

    public Board() {
        for (int i = 0; i < 10; i++) {
            sampleBoard[i] = new int[10];
        }
    }

    public void initBoard() {
        placeShipRandomly(6);
        placeShipRandomly( 5);
        placeShipRandomly( 4);
        placeShipRandomly( 3);
        placeShipRandomly( 3);
    }


    public void printBoard() {
        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                System.out.print(sampleBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void shootingByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj X:");
        int x = scanner.nextInt() - 1;
        System.out.println("Podaj Y:");
        int y = scanner.nextInt() - 1;
        int shot = sampleBoard[x][y];
        if (x < 1 || x > 10 || y < 1 || y > 10) {
// OBSLUGA WYJATKU
        }
        if (shot > 9) {
            System.out.println("To miejsce było już ostrzelane");
        } else if (shot == 0) {
            System.out.println("Pudło");
            sampleBoard[x][y] = shot + 10;
        } else if (shot != 0) {
            System.out.println("Trafienie");
            sampleBoard[x][y] = shot + 10;
        }
    }

    public void shootingByEnemy() {
        Random random = new Random();
        boolean loop = true;
        while (loop) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            int shot = sampleBoard[x][y];
            if (shot == 0) {
                loop = false;
                System.out.println("Pudło");
                sampleBoard[x][y] = shot + 10;
            } else if (shot > 0 && shot < 9) {
                loop = false;
                System.out.println("Trafienie");
                sampleBoard[x][y] = shot + 10;
            }
        }
    }

    public void checkEndGame(){}
}