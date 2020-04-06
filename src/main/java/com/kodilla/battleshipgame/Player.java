package com.kodilla.battleshipgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player extends Board {

    ArrayList<String> hitComments = new ArrayList<>();
    List<String> missComments = new ArrayList<>();

    public void commentsToArrays() {
        hitComments.add("Komentarz 1 Niech ta wyrwa w kadłubie ");
        hitComments.add("Komentarz 2");
        hitComments.add("Komentarz 3");
        hitComments.add("Komentarz 4");
        hitComments.add("Komentarz 5");
        hitComments.add("Komentarz 6");
        hitComments.add("Komentarz 7");
        hitComments.add("Komentarz 8");
        hitComments.add("Komentarz 9");
        hitComments.add("Komentarz 10");

        missComments.add("Comment 1 I kolejną kule pochłonęły morskie fale");
        missComments.add("Comment 2");
        missComments.add("Comment 3");
        missComments.add("Comment 4");
        missComments.add("Comment 5");
        missComments.add("Comment 6");
        missComments.add("Comment 7");
        missComments.add("Comment 8");
        missComments.add("Comment 9");
        missComments.add("Comment 10");

    }

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
            System.out.println("To miejsce było już ostrzelane, co za pech...");
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
