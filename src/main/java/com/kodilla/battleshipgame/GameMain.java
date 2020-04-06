package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Player user = new Player();
        Player enemy = new Player();
        boolean gameOver = false;

        user.initBoard();
        enemy.initBoard();

        while (!gameOver) {
            String winner;
            user.shootingByUser();
            user.printBoard();
            System.out.println();
            enemy.shootingByEnemy();
            enemy.printBoard();
            System.out.println();
            if (user.endGame() || enemy.endGame()) {
                gameOver = true;
                winner = user.endGame() == true ? "You are" : "Your opponent";
                System.out.println("Game Over");
                System.out.println(winner + " the winner");
            }
        }
    }
}