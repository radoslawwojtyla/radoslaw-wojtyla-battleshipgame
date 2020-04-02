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

        String result1 = board.checkHorizontalCollisions(1,2,3);

        Assert.assertEquals("impossible", result1);


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
