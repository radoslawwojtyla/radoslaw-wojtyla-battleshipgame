package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board();
        int[][] sampleBoard = new int[10][10];

        board.initBoard(sampleBoard);
        board.printBoard(sampleBoard);
    }
}