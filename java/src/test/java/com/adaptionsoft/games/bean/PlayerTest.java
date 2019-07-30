package com.adaptionsoft.games.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void addCoin() {
        player.addCoin();
        Assert.assertEquals(1, player.getPurse());
    }
}