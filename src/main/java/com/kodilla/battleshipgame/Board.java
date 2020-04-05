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
        int counter = 1;
        if (checkHorizontalBoarders(y, shipSize).equals("right") && !hasShipNeighbour(x, y, "right", shipSize)) {
            while (sampleBoard[x][y] == 0 && loop) {
                counter++;
                if (counter == shipSize + 1) {
                    loop = false;
                    result = "right";
                } else {
                    y++;
                }
            }
        } else if (checkHorizontalBoarders(y, shipSize).equals("left") && !hasShipNeighbour(x, y, "left", shipSize)) {
            while (sampleBoard[x][y] == 0 && loop) {
                counter++;

                if (counter == shipSize + 1) {
                    loop = false;
                    result = "left";
                } else {
                    y--;
                }
            }
        }
        return result;
    }


    public String checkVerticalCollisions(int x, int y, int shipSize) {
        String result = "impossible";
        boolean loop = true;
        int counter = 1;
        if (checkVerticalBoarders(x, shipSize).equals("down") && !hasShipNeighbour(x, y, "down", shipSize)) {
            while (sampleBoard[x][y] == 0 && loop) {
                counter++;
                if (counter == shipSize + 1) {
                    loop = false;
                    result = "down";
                } else {
                    x++;
                }
            }
        } else if (checkVerticalBoarders(x, shipSize).equals("up") && !hasShipNeighbour(x, y, "up", shipSize)) {
            while (sampleBoard[x][y] == 0 && loop) {
                counter++;
                if (counter == shipSize + 1) {
                    loop = false;
                    result = "up";
                } else {
                    x--;
                }
            }
        }
        return result;
    }

//    public boolean firstPointCheck(int x, int y) {
////        if (sampleBoard[x][y] == 0) {
////            return true;
////        } else {
////            return false;
////        }
////    }

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
            if (!orientation.equals("impossible")) {
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
        placeShipRandomly(5);
        placeShipRandomly(4);
        placeShipRandomly(3);
        placeShipRandomly(3);
        placeShipRandomly(2);
        placeShipRandomly(2);
        placeShipRandomly(1);
        placeShipRandomly(1);
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

    public boolean hasPointNeighbour(int x, int y) {
        boolean result = false; // pole jest wolne
        for (int xx = (x - 1 >= 0 ? x - 1 : x); xx <= (x + 1 < 10 ? x + 1 : x); xx++) {
            result = result || isOccupied(xx, y - 1 >= 0 ? y - 1 : y) || isOccupied(xx, y) || isOccupied(xx, y + 1 < 10 ? y + 1 : y);
        }
        return result;
    }

    public boolean hasShipNeighbour(int x, int y, String orientation, int shipSize) {
        int dx = (orientation.equals("right") || orientation.equals("left") ? 0 : 1); // jeśli horizontal to dx =1, jeśli pion to dx =0
        int dy = (orientation.equals("down") || orientation.equals("up") ? 0 : 1); // jeśli pion to dy = 1, jeśli poziom to dy =0
        int dxx = (orientation.equals("down") ? 1 : -1);
        int dyy = (orientation.equals("right") ? 1 : -1);
        boolean result = false; // pole jest wolne
        for (int step = 0; step < shipSize; step++) {
            result = result || hasPointNeighbour(x + dx * step * dxx, y + dy * step * dyy);
        }
        return result; // false > wolne pola
    }

    private boolean isOccupied(int x, int y) {
        if (sampleBoard[x][y] != 0) {
            return true; // pole jest zajęte
        } else {
            return false; // pole jest wolne
        }
    }
}
