package com.adaptionsoft.games.bean;

import com.spun.util.StringUtils;

public class Player {
    private String name;

    public Player(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Player must have no null name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
