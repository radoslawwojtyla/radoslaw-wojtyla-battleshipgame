package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board userBoard = new Board();
        Board enemyBoard = new Board();

        userBoard.initBoard();
        enemyBoard.initBoard();

        enemyBoard.shootingByUser();
        enemyBoard.printBoard();

        System.out.println();

        userBoard.shootingByEnemy();
        userBoard.printBoard();


    }
}