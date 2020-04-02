package com.kodilla.battleshipgame;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testCheckHorizontalBoarders() {
        //Given
        Board board = new Board();
        board.placeShip(4,"right",2,2);
        //When
        String result1 = board.checkHorizontalBoarders(7,3);
        String result2 = board.checkHorizontalBoarders(7,4);
        //Then
        Assert.assertEquals("right",result1);
        Assert.assertEquals("left",result2);
    }

    @Test
    public void testCheckVerticalBoarders() {
        //Given
        int[][] sampleBoard = new int[10][];
        for (int i = 0; i < 10; i++) {
            sampleBoard[i] = new int[10];
        }
        Board board = new Board();

        //When
        String result1 = board.checkVerticalBoarders(7,3);
        String result2 = board.checkVerticalBoarders(7,4);
        //Then
        Assert.assertEquals("down",result1);
        Assert.assertEquals("up",result2);
    }

    @Test
    public void testCheckHorizontalCollisions() {
        //Given
        Board board = new Board();

        //When
        String result1 = board.checkHorizontalCollisions(3,0,4);
        //Then
        Assert.assertEquals("right",result1);
    }

    @Test
    public void testCheckVerticalCollisions() {
        //Given

        //When

        //Then

    }

    @Test
    public void testFirstPointCheck() {
        //Given

        //When

        //Then

    }

    @Test
    public void testShipDrawing() {
        //Given

        //When

        //Then

    }
}
