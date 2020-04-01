package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board();

        board.initBoard();

        board.shootingByUser();

        board.shootingByEnemy();

        board.printBoard();
    }
}