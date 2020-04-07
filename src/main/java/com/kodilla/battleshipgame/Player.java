package com.kodilla.battleshipgame;

import java.util.Random;
import java.util.Scanner;

public class Player extends Board {

    public int getX() {
        Scanner scanner = new Scanner(System.in);
        boolean allowedShot = false;
        int x = 0;
        while (!allowedShot) {
            System.out.println("Podaj X: ");
            x = scanner.nextInt()-1;
            if (x < 0 || x >10) {
                System.out.println("Nieprawidłowa wartość.");
                continue;
            }
            allowedShot = true;
        }
        return x;
    }

    public int getY() {
        Scanner scanner = new Scanner(System.in);
        boolean allowedShot = false;
        int y = 0;
        while (!allowedShot) {
            System.out.println("Podaj Y: ");
            y = scanner.nextInt() - 1;
            if (y < 0 || y > 10) {
                System.out.println("Nieprawidłowa wartość.");
                continue;
            }
            allowedShot = true;
        }
        return y;
    }

    public void shootingByUser() {
        int x = getX();
        int y = getY();
        int shot = sampleBoard[x][y];
        if (shot > 9) {
            System.out.println("To miejsce było już ostrzelane, co za pech...");
        } else if (shot == 0) {
            System.out.println("Pudło");
            sampleBoard[x][y] = shot + 10;
            showingBoard[x][y] = shot +10;
        } else {
            System.out.println("Trafienie");
            sampleBoard[x][y] = shot + 10;
            showingBoard[x][y] = shot +10;
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
