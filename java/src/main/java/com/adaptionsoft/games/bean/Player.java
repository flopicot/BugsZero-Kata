package com.adaptionsoft.games.bean;

import com.spun.util.StringUtils;

public class Player {
    private String name;
    private int purse = 0;

    public Player(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Player must have no null name");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPurse() {
        return this.purse;
    }

    public void addCoin() {
        this.purse++;
    }
}
