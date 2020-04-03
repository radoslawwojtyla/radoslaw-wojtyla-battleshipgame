package com.kodilla.battleshipgame;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testCheckHorizontalBoarders() {
        //Given
        Board board = new Board();
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
        board.placeShip(6, "down", 0,4);
        board.placeShip(6, "down", 0,6);

        String result1 = board.checkHorizontalCollisions(0,2,3);
        String result2 = board.checkHorizontalCollisions(0,1,3);
        String result3 = board.checkHorizontalCollisions(0,8,3);
        String result4 = board.checkHorizontalCollisions(0,9,3);

        Assert.assertEquals("impossible", result1);
        Assert.assertEquals("right", result2);
        Assert.assertEquals("impossible", result3);
        Assert.assertEquals("left", result4);


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
