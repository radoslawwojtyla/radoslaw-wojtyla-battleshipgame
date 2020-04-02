package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board userBoard = new Board();
        Board enemyBoard = new Board();
        Player user = new Player();
        Player enemy = new Player();

//        userBoard.placeShip(6, "down", 0,4);
//
//        userBoard.placeShip(3, "right", 0,2);
        userBoard.initBoard();
        userBoard.printBoard();


    }
}