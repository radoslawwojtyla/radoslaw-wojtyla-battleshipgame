package com.kodilla.battleshipgame;

import com.sun.scenario.effect.Brightpass;
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
    public void testHasPointNeighbour() {
        //Given
        Board board = new Board();
        board.placeShip(10, "down", 0,4);
        board.placeShip(10, "right", 4,0);
        //When
        boolean result1 = board.hasPointNeighbour(1,1);
        boolean result2 = board.hasPointNeighbour(1,2);

        //Then

        Assert.assertEquals(false, result1);
        Assert.assertEquals(true, result2);

    }

//    @Test
//    public void testIsOccupied() {
//        //Given
//        Board board = new Board();
//        board.placeShip(6, "down", 0,4);
//
//        //When
//        boolean result1 = board.isOccupied(0,4);
//        boolean result2 = board.isOccupied(5,4);
//        boolean result3 = board.isOccupied(6,4);
//        //Then
//        Assert.assertEquals(true, result1);
//        Assert.assertEquals(true, result2);
//        Assert.assertEquals(false, result3);
//    }

    @Test
    public void testHasShipNeighbour() {
        Board board = new Board();
        board.placeShip(10, "down", 0, 4);
        board.placeShip(10, "right", 4, 0);

        boolean result1 = board.hasShipNeighbour(1, 1, "right,", 2);
        boolean result2 = board.hasShipNeighbour(1, 2, "right", 2);
        boolean result3 = board.hasShipNeighbour(5, 2, "right", 2);
        boolean result4 = board.hasShipNeighbour(3, 0, "right", 2);

        boolean result5 = board.hasShipNeighbour(1, 1, "down", 2);
        boolean result6 = board.hasShipNeighbour(2, 1, "down", 2);
        boolean result7 = board.hasShipNeighbour(2, 5, "down", 2);
        boolean result8 = board.hasShipNeighbour(0, 3, "down", 2);

        boolean result9 = board.hasShipNeighbour(1, 6, "left", 2);

        Assert.assertEquals(false, result1);
        Assert.assertEquals(true, result2);
        Assert.assertEquals(true, result3);
        Assert.assertEquals(true, result4);

        Assert.assertEquals(false, result5);
        Assert.assertEquals(true, result6);
        Assert.assertEquals(true, result7);
        Assert.assertEquals(true, result8);

        Assert.assertEquals(true, result9);
    }

//        @Test
//        public void testHitAndSink() {
//        Board board = new Board();
//        Player player = new Player();
//        board.placeShip(1,"down",3,3);
//
//
//        boolean result1 = player.hitAndSink(4,4);
//
//        Assert.assertEquals(true, result1);
//    }
}
