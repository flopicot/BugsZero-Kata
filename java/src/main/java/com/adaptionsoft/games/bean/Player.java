package com.adaptionsoft.games.bean;

import com.spun.util.StringUtils;

public class Player {
    private String name;
    private int purse = 0;
    private int place = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Player must have no null name");
        }
        this.name = name;
    }

    public void addCoinInPurse() {
        this.purse++;
    }

    public void moveForward(int roll) {
        this.place += roll;
        if (this.place > 11) {
            this.place = place - 12;
        }
    }

    public void placeInPernaltyBox() {
        this.inPenaltyBox = true;
    }

    public String getName() {
        return this.name;
    }

    public int getPurse() {
        return this.purse;
    }

    public int getPlace() {
        return this.place;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }
}
