package com.kodilla.battleshipgame;

public class Board {

    int[][] sampleBoard;

    public static void initBoard(int[][] sampleBoard) {

        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                sampleBoard[i][j] = 0;
            }
        }
    }

    public static void printBoard(int[][] sampleBoard) {
        for (int i = 0; i < sampleBoard.length; i++) {
            for (int j = 0; j < sampleBoard[i].length; j++) {
                System.out.print(sampleBoard[i][j]);
            }
            System.out.println();
        }
    }
}
