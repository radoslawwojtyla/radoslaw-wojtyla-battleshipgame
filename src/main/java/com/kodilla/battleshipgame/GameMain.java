package com.kodilla.battleshipgame;

public class GameMain {
    public static void main(String[] args) {
        Player user = new Player();
        Player enemy = new Player();
        boolean gameOver = false;

        user.initBoard();
        enemy.initBoard();

        while (!gameOver) {
            String winner = "No one is";
            user.shootingByUser();
            enemy.shootingByEnemy();
            user.printBoard();
            System.out.println();
            enemy.printBoard();
            if (user.endGame() || enemy.endGame()) {
                gameOver = true;
                winner = user.endGame() == true ? "You are" : "Your opponent";
                System.out.println("Game Over");
                System.out.println(winner + " the winner");
            }
        }
    }
}