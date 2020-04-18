package com.kodilla.battleshipgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Board {

    int[][] sampleBoard = new int[10][];
    int[][] showingBoard = new int[10][];
    int[] coordinates = new int[2];

    public Board() {
        for (int i = 0; i < 10; i++) {
            sampleBoard[i] = new int[10];
            showingBoard[i] = new int[10];
        }
    }

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

    public int[][] initBoard() {
        placeShipRandomly(5);
        placeShipRandomly(4);
        placeShipRandomly(3);
        placeShipRandomly(3);
        placeShipRandomly(2);
        placeShipRandomly(2);
        placeShipRandomly(1);
        placeShipRandomly(1);
        return sampleBoard;
    }



    public void printBoard() {
        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                System.out.print(sampleBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBoardToShow() {
        for (int i = 0; i < showingBoard.length; i++) {
            for (int j = 0; j < showingBoard[i].length; j++) {
                System.out.print(showingBoard[j][i] + " ");
            }
            System.out.println();
        }
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

    public boolean endGame() {
        int hitPoints = 21;
        boolean endGame = false;
        int hits = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (sampleBoard[i][j] > 10) {
                    hits++;
                }
            }
        }
        if (hits == hitPoints) {
            endGame = true; // koniec gry
        }
        return endGame;
    }

    public void sampleHit(int x, int y) {
        int shot = sampleBoard[x][y];
        sampleBoard[x][y] = shot + 10;
    }


//    public boolean hitAndSink(int x, int y) {
//        boolean result = false; // nie zatopiony
//        int ShipSize = sampleBoard[x][y];
//        int sinkPoints = (ShipSize+10)*ShipSize; //np. 5+10 (trafiony) *5 = 75 => zatopiony 5-masztowiec
//        int temporaryPoints;
//        for (int i =0; i < ShipSize; i++) {
//            temporaryPoints =+ sampleBoard[x][y + 1];
//            if (temporaryPoints == sinkPoints) {
//                result = true; // zatopiony
//            }
//        }
//        for (int i =0; i < ShipSize; i++) {
//            temporaryPoints =+ sampleBoard[x][y - 1];
//            if (temporaryPoints == sinkPoints) {
//                result = true; // zatopiony
//            }
//        }
//        for (int i =0; i < ShipSize; i++) {
//            temporaryPoints =+ sampleBoard[x+1][y];
//            if (temporaryPoints == sinkPoints) {
//                result = true; // zatopiony
//            }
//        }
//        for (int i =0; i < ShipSize; i++) {
//            temporaryPoints =+ sampleBoard[x-1][y];
//            if (temporaryPoints == sinkPoints) {
//                result = true; // zatopiony
//            }
//        }
//        return result;
//    }

    public int[] shootingByEnemy() {
        Random random = new Random();
        boolean loop = true;
        int x =0;
        int y =0;
        while (loop) {
            x = random.nextInt(10);
            y = random.nextInt(10);
            int shot = sampleBoard[x][y];
            if (shot == 0) {
                loop = false;
                sampleBoard[x][y] = shot + 10;
                showingBoard[x][y] = shot + 10;
            } else if (shot > 0 && shot < 9) {
                loop = false;
                sampleBoard[x][y] = shot + 10;
                showingBoard[x][y] = shot +10;
//                if (hitAndSink(x,y)) {
//                }
            }
            coordinates[0] = x;
            coordinates[1] = y;
        }
    return coordinates;
    }

    public void shootingByUser(int setX, int setY) {
        boolean loop = true;
        while (loop) {
            int x = setX;
            int y = setY;
            int shot = sampleBoard[x][y];
            if (shot > 9) {
//                System.out.println("To miejsce było już ostrzelane, co za pech...");
                loop = false;
            } else if (shot == 0) {
//                System.out.println("Pudło");
                sampleBoard[x][y] = shot + 10;
                showingBoard[x][y] = shot + 10;
                loop = false;
            } else {
//                System.out.println("Trafienie");
                sampleBoard[x][y] = shot + 10;
                showingBoard[x][y] = shot + 10;
                loop = false;
//                if (hitAndSink(x,y)) {
////                    System.out.println("Trafiony, zatopiony!");
//                }
            }
        }
    }
}