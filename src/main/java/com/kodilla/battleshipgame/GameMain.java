package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board();
        int[][] enemyBoard = new int[10][];

        board.initBoard(enemyBoard);
        board.printBoard(enemyBoard);
    }
}