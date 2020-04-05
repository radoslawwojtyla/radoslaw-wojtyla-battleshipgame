package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board();

        board.initBoard();
//        board.placeShip(10, "down", 0,4);
//        board.placeShip(10, "right", 4,0);
//        board.placeShip(2, "left", 1,6);
        board.printBoard();
        System.out.println();



    }
}