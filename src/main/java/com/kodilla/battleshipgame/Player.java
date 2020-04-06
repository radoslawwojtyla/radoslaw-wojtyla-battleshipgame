package com.kodilla.battleshipgame;

import java.util.Random;
import java.util.Scanner;

public class Player extends Board {

    Board board = new Board();

    public void shootingByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj X:");
        int x = scanner.nextInt() - 1;
        System.out.println("Podaj Y:");
        int y = scanner.nextInt() - 1;
        int shot = sampleBoard[x][y];
        if (x < 1 || x > 10 || y < 1 || y > 10) {
        }
        if (shot > 9) {
            System.out.println("To miejsce było już ostrzelane");
        } else if (shot == 0) {
            System.out.println("Pudło");
            sampleBoard[x][y] = shot + 10;
        } else {
            System.out.println("Trafienie");
            sampleBoard[x][y] = shot + 10;
        }
    }

    public void shootingByEnemy() {
        Random random = new Random();
        boolean loop = true;
        while (loop) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            int shot = sampleBoard[x][y];
            if (shot == 0) {
                loop = false;
                System.out.println("Pudło");
                sampleBoard[x][y] = shot + 10;
            } else if (shot > 0 && shot < 9) {
                loop = false;
                System.out.println("Trafienie");
                sampleBoard[x][y] = shot + 10;
            }
        }
    }
}
