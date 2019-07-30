package com.adaptionsoft.games.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp(){
        player = new Player("Nicolas");
    }

    @Test
    public void getName() {
        Assert.assertEquals("Nicolas", player.getName());
    }

    @Test
    public void getPurse() {
        Assert.assertEquals(0, player.getPurse());
    }

    @Test
    public void getPlace(){
        Assert.assertEquals(0, player.getPlace());
    }

    @Test
    public void addCoin() {
        player.addCoinInPurse();
        Assert.assertEquals(1, player.getPurse());
    }

    @Test
    public void moveNoLap(){
        player.moveForward(3);
        Assert.assertEquals(3, player.getPlace());
    }

    @Test
    public void moveWithLap(){
        player.moveForward(15);
        Assert.assertEquals(3, player.getPlace());
    }

}